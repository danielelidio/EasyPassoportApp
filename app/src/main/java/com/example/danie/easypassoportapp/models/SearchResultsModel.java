package com.example.danie.easypassoportapp.models;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by danie on 15/06/2016.
 */
public class SearchResultsModel extends Model {
    private List<SearchResultItem> results = null;

    public SearchResultsModel() {
        this.results = new LinkedList<SearchResultItem>();
    }

    public SearchResultsModel(List<SearchResultItem> results) {
        this.results = results;
    }

    public void addResultItem(SearchResultItem result) {
        this.results.add(result);
    }

    public SearchResultItem getResult(int position) {
        return this.results.get(position);
    }

    public int getSize() {
        return this.results.size();
    }
}
