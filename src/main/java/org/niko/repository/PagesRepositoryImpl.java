package org.niko.repository;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryparser.flexible.standard.StandardQueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.FSDirectory;
import org.niko.entity.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.niko.util.PageToDocumentConverterUtils.documentToPage;
import static org.niko.util.PageToDocumentConverterUtils.pageToDocument;

@Repository
public class PagesRepositoryImpl implements PagesRepository {

    @Autowired FSDirectory fsDirectory;
    @Autowired IndexWriter writer;
    @Autowired StandardQueryParser queryParser;
    @Autowired StandardAnalyzer analyzer;

    @Override
    public void create(Page page) {
        try {
            writer.addDocument(pageToDocument(page));
            writer.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Page> search(String text) {
        try {
            Query query = queryParser.parse(text, "content");
            ScoreDoc[] scoreDocs = new IndexSearcher(DirectoryReader.open(fsDirectory)).search(query, 10).scoreDocs;
            List<Page> pages = new ArrayList<>();
            for (ScoreDoc scoreDoc : scoreDocs) {
                SimpleHTMLFormatter htmlFormatter = new SimpleHTMLFormatter();
                Highlighter highlighter = new Highlighter(htmlFormatter, new QueryScorer(query));
                IndexSearcher indexSearcher = new IndexSearcher(DirectoryReader.open(fsDirectory));
                Page page = documentToPage(indexSearcher.doc(scoreDoc.doc));
                page.setHighlight(highlighter.getBestFragment(analyzer, "content", page.getContent()));
                pages.add(page);
            }
            return pages;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

}
