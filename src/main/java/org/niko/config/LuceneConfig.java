package org.niko.config;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.flexible.standard.StandardQueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.FSDirectory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.io.IOException;
import java.nio.file.Paths;

@Configuration
public class LuceneConfig {

    public static final String INDEX_FOLDER = System.getProperty("java.io.tmpdir") + "/lucene/index";

    @Bean
    public StandardAnalyzer analyzer() {
        return new StandardAnalyzer();
    }

    @Bean
    public FSDirectory luceneDirectory() throws IOException {
        return FSDirectory.open(Paths.get(INDEX_FOLDER));
    }

    @Bean
    public IndexWriter indexWriter() throws IOException {
        return new IndexWriter(luceneDirectory(), new IndexWriterConfig(analyzer()));
    }

    @Bean
    @DependsOn("indexWriter")
    public IndexReader indexReader() throws IOException {
        return DirectoryReader.open(luceneDirectory());
    }

    @Bean
    @DependsOn("indexWriter")
    public IndexSearcher indexSearcher() throws IOException {
        return new IndexSearcher(indexReader());
    }

    @Bean
    public StandardQueryParser queryParser() {
        return new StandardQueryParser(analyzer());
    }

}
