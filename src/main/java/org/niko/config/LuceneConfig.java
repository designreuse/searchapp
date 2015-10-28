package org.niko.config;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.flexible.standard.StandardQueryParser;
import org.apache.lucene.store.FSDirectory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Paths;

@Configuration
public class LuceneConfig {

    public static final String INDEX_FOLDER = System.getProperty("java.io.tmpdir") + "/lucene/index";

    @Bean StandardAnalyzer analyzer() {
        return new StandardAnalyzer();
    }

    @Bean FSDirectory luceneDirectory() throws IOException {
        return FSDirectory.open(Paths.get(INDEX_FOLDER));
    }

    @Bean IndexWriter indexWriter() throws IOException {
        return new IndexWriter(luceneDirectory(), indexWriterConfig());
    }

    @Bean IndexWriterConfig indexWriterConfig() {
        return new IndexWriterConfig(analyzer());
    }

    @Bean StandardQueryParser queryParser() {
        return new StandardQueryParser(analyzer());
    }

}
