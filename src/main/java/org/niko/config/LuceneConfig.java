package org.niko.config;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.flexible.standard.StandardQueryParser;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Paths;

@Configuration
public class LuceneConfig {

    @Bean StandardAnalyzer analyzer() {
        return new StandardAnalyzer();
    }

    @Bean FSDirectory luceneDirectory(@Value("${lucene.index}") String indexFolder) throws IOException {
        return FSDirectory.open(Paths.get(System.getProperty("java.io.tmpdir") + indexFolder));
    }

    @Bean IndexWriter indexWriter() throws IOException {
        return new IndexWriter(luceneDirectory(null), indexWriterConfig());
    }

    @Bean IndexWriterConfig indexWriterConfig() {
        return new IndexWriterConfig(analyzer());
    }

    @Bean StandardQueryParser queryParser() {
        return new StandardQueryParser(analyzer());
    }

}
