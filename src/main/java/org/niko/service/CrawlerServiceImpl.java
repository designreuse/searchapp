package org.niko.service;

import org.niko.crawler.*;
import org.niko.repository.PagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class CrawlerServiceImpl implements CrawlerService, LinkTaskRunner {

    private ExecutorService execService = Executors.newFixedThreadPool(64);

    @Autowired
    PagesRepository pagesRepository;

    @Override
    public void load(String url, Integer depth) {
        startNewTask(new Link(url, 1), new LinkHandlerImpl(this, depth));
    }

    @Override
    public void load(String url) {
        load(url, CrawlerService.DEFAULT_DEPTH);
    }

    public void startNewTask(Link link, LinkHandler linkHandler) {
        execService.execute(new LinkFinder(link, linkHandler, pagesRepository::create));
    }

}
