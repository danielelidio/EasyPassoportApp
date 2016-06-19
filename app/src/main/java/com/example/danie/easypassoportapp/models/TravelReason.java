package com.example.danie.easypassoportapp.models;

/**
 * Created by danie on 17/06/2016.
 */
public class TravelReason {
    private String name;
    private String alias;

    public TravelReason(String name, String alias) {
        this.name = name;
        this.alias = alias;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
