package com.example.danie.easypassoportapp.models;

/**
 * Created by danie on 15/06/2016.
 */
public class SearchResultItem {
    public int nr;
    public String title;
    public String description;

    public SearchResultItem(int nr, String title, String description) {
        this.nr = nr;
        this.title = title;
        this.description = description;
    }

    public int getNr() {
        return nr;
    }

    public String getNrFormatted() {
        return new Integer(this.getNr()).toString() + "º";
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public String getShortDescription() { return description.substring(0, 50) + "..."; }

    public void setDescription(String description) {
        this.description = description;
    }
}
