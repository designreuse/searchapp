package org.niko.repository;

import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryparser.flexible.core.QueryNodeException;
import org.apache.lucene.queryparser.flexible.standard.StandardQueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.niko.entity.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.niko.util.PageToDocumentConverterUtils.documentToPage;
import static org.niko.util.PageToDocumentConverterUtils.pageToDocument;

@Repository
public class PagesRepositoryImpl implements PagesRepository {

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
        return Stream.of(getTopDocs(text)).map(this::toPage).filter(Objects::nonNull).collect(toList());
    }

    private ScoreDoc[] getTopDocs(String text) {
        try {
            return searcher.search(queryParser.parse(text, "content"), 10).scoreDocs;
        } catch (IOException | QueryNodeException e) {return new ScoreDoc[0];}
    }

    private Page toPage(ScoreDoc scoreDoc) {
        try {
            return documentToPage(searcher.doc(scoreDoc.doc));
        } catch (IOException e) {return null;}
    }

}
