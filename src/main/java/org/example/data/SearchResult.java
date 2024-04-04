package org.example.data;

import java.util.Collection;
import java.util.List;

public class SearchResult {
    public String search;
    public Collection<? extends List<Integer>> result;
    public long time;

    public SearchResult(String search, Collection<? extends List<Integer>> result, long time) {
        this.search = search;
        this.result = result;
        this.time = time;
    }
}