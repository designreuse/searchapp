package org.niko.repository;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.flexible.standard.StandardQueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.FSDirectory;
import org.niko.dto.SearchResult;
import org.niko.entity.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
            writer.updateDocument(new Term("url", page.getUrl()), pageToDocument(page));
            writer.commit();
        } catch (IOException ignored) {}
    }

    public SearchResult search(String text, Integer skip, String sortType) {
        int skipRecords = (skip == null) ? 0 : skip;
        int lastRecord = skipRecords + 10;
        try {
            Query query = queryParser.parse(text, "content");
            TopDocs search = new IndexSearcher(DirectoryReader.open(fsDirectory)).search(query, lastRecord, SortingTypes.valueOf(sortType).sort);
            ScoreDoc[] scoreDocs = Arrays.copyOfRange(search.scoreDocs, skipRecords, lastRecord);
            List<Page> pages = new ArrayList<>();
            for (ScoreDoc scoreDoc : scoreDocs) {
                if(scoreDoc == null) continue;
                pages.add(getHighlightedPage(query, scoreDoc));
            }
            return new SearchResult(pages, search.totalHits, skipRecords);
        } catch (Exception e) {
            return new SearchResult();
        }
    }

    private Page getHighlightedPage(Query query, ScoreDoc scoreDoc) throws IOException, InvalidTokenOffsetsException {
        SimpleHTMLFormatter htmlFormatter = new SimpleHTMLFormatter();
        Highlighter highlighter = new Highlighter(htmlFormatter, new QueryScorer(query));
        IndexSearcher indexSearcher = new IndexSearcher(DirectoryReader.open(fsDirectory));
        Page page = documentToPage(indexSearcher.doc(scoreDoc.doc));
        page.setHighlight(highlighter.getBestFragment(analyzer, "content", page.getContent()));
        return page;
    }

}
