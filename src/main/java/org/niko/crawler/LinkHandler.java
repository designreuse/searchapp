package org.niko.crawler;

public interface LinkHandler {

    void queueLink(Link link);

    boolean visited(Link link);

    void addVisited(Link link);

    int getDepth();

}