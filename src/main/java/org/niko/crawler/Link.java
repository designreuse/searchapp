package org.niko.crawler;

public class Link {

    private String url;
    private int depth;

    public Link(String url, int depth) {
        this.url = url;
        this.depth = depth;
    }

    public String getUrl() {
        return url;
    }

    public int getDepth() {
        return depth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Link)) return false;

        Link link = (Link) o;

        if (!url.equals(link.url)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return url.hashCode();
    }

}
