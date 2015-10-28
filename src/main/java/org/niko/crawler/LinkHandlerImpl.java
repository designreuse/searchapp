package org.niko.crawler;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public class LinkHandlerImpl implements LinkHandler {

    private final Collection<Link> visitedLinks = Collections.synchronizedSet(new HashSet<>());
    private LinkTaskRunner runner;
    private int depth;

    public LinkHandlerImpl(LinkTaskRunner runner, int depth) {
        this.runner = runner;
        this.depth = depth;
    }

    public int getDepth() {
        return depth;
    }

    @Override
    public void queueLink(Link link) {
        runner.startNewTask(link, this);
    }

    @Override
    public void addVisited(Link s) {
        visitedLinks.add(s);
    }

    @Override
    public boolean visited(Link s) {
        return visitedLinks.contains(s);
    }
}
