package org.niko.repository;

import org.niko.dto.SearchResult;
import org.niko.entity.Page;

public interface PagesRepository {

    void create(Page page);
    SearchResult search(String text, Integer skip, String sortType);

}
