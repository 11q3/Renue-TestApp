package org.example.data;

import java.util.List;

public class SearchResult {
    public String search;
    public List<Short> result;
    public long time;

    public SearchResult(String search, List<Short> result, long time) {
        this.search = search;
        this.result = result;
        this.time = time;
    }
}