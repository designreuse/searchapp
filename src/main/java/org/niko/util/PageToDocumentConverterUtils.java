package org.niko.util;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.niko.entity.Page;

public class PageToDocumentConverterUtils {

    public static Document pageToDocument(Page page) {
        Document doc = new Document();
        doc.add(new TextField("title", page.getTitle(), Field.Store.YES));
        doc.add(new TextField("url", page.getUrl(), Field.Store.YES));
        doc.add(new TextField("content", page.getContent(), Field.Store.YES));
        return doc;
    }

    public static Page documentToPage(Document doc) {
        Page page = new Page();
        page.setTitle(doc.get("title"));
        page.setUrl(doc.get("url"));
        page.setContent(doc.get("content"));
        return page;
    }

}
