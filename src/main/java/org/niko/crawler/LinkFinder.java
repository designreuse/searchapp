package org.niko.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.niko.entity.Page;

import java.util.function.Consumer;

public class LinkFinder implements Runnable {

    private Link url;
    private LinkHandler linkHandler;
    private Consumer<Page> pageConsumer;

    public LinkFinder(Link url, LinkHandler handler, Consumer<Page> pageConsumer) {
        this.url = url;
        this.linkHandler = handler;
        this.pageConsumer = pageConsumer;
    }

    @Override
    public void run() {
        getSimpleLinks(url);
    }

    private void getSimpleLinks(Link link) {
        if (linkHandler.visited(link) || linkHandler.getDepth() <= link.getDepth()) return;
        try {
            Document doc = Jsoup.connect(link.getUrl()).get();
            pageConsumer.accept(new Page(link.getUrl(), doc.select("title").text(), doc.text()));
            linkHandler.addVisited(link);

            doc.select("a").stream()
                    .map((e) -> e.attr("abs:href"))
                    .filter((e) -> !e.isEmpty())
                    .map((e) -> new Link(e, link.getDepth() + 1))
                    .filter((e) -> !linkHandler.visited(e))
                    .forEach(linkHandler::queueLink);
        } catch (Exception ignored) {}
    }
}
