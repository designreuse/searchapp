package org.niko.repository;

import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;

public enum SortingTypes {

    Alphabetical(new Sort(new SortField("title", SortField.Type.STRING, false)))
    ,Relevance(new Sort());

    Sort sort;

    SortingTypes(Sort sort) {
        this.sort = sort;
    }
}
