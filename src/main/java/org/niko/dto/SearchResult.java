package org.niko.dto;

import org.niko.entity.Page;

import java.util.ArrayList;
import java.util.List;

public class SearchResult {

    private List<Page> pages = new ArrayList<>();
    private int totalCount = 0;
    private int perPage = 10;

    public SearchResult() {}

    public SearchResult(List<Page> pages, int totalCount) {
        this.pages = pages;
        this.totalCount = totalCount;
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
