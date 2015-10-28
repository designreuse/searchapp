package org.niko.dao;

import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryparser.flexible.core.QueryNodeException;
import org.apache.lucene.queryparser.flexible.standard.StandardQueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.niko.entity.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.niko.util.PageToDocumentConverterUtils.documentToPage;
import static org.niko.util.PageToDocumentConverterUtils.pageToDocument;

@Repository
public class PagesDaoImpl implements PagesDao {

    @Autowired IndexWriter writer;
    @Autowired IndexSearcher searcher;
    @Autowired StandardQueryParser queryParser;

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
            TopDocs topDocs = searcher.search(queryParser.parse(text, "content"), 10);
            List<Page> pages = new ArrayList<>();
            for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
                pages.add(documentToPage(searcher.doc(scoreDoc.doc)));
            }
            return pages;
        } catch (IOException | QueryNodeException e) {
            e.printStackTrace();
        }
        return null;
    }

}
