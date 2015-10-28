package org.niko.repository;

import org.niko.entity.Page;

import java.util.List;

public interface PagesRepository {

    void create(Page page);
    List<Page> search(String text);

}
