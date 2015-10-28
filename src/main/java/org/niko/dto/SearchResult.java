package org.niko.dto;

import org.niko.entity.Page;

import java.util.ArrayList;
import java.util.List;

public class SearchResult {

    private List<Page> pages = new ArrayList<>();
    private int totalCount = 0;
    private int perPage = 10;
    private int skip = 0;

    public SearchResult() {}

    public SearchResult(List<Page> pages, int totalCount, int skip) {
        this.pages = pages;
        this.totalCount = totalCount;
        this.skip = skip;
    }

    public int getSkip() {
        return skip;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }

    public List<Page> getPages() {
        return pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public int getMaxPages() {
        return (int)Math.ceil((double)totalCount / perPage);
    }
}
