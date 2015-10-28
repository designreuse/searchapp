package org.niko.entity;

public class Page {

    private String title;
    private String url;
    private String highlight;
    private String content;

    public Page() {
    }

    public Page(String url, String title, String content) {
        this.url = url;
        this.title = title;
        this.content = content;
    }

    public void setHighlight(String highlight) {
        this.highlight = highlight;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHighlight() {
        return highlight;
    }

    @Override
    public String toString() {
        return "Page{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
