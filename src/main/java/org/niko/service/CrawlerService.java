package org.niko.service;

public interface CrawlerService {

    public static final int DEFAULT_DEPTH = 3;

    void load(String url, Integer depth);
    void load(String url);

}
