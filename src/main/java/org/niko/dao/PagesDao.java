package org.niko.dao;

import org.niko.entity.Page;

import java.util.List;

public interface PagesDao {

    void create(Page page);
    List<Page> search(String text);

}
