package org.niko.crawler;

public interface LinkTaskRunner {
    void startNewTask(Link link, LinkHandler linkHandler);
}
