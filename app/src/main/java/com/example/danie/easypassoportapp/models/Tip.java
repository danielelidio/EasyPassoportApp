/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.danie.easypassoportapp.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Daniel
 */
public class Tip {
    private String country;
    private String tipType;
    private String title;
    private String content;
    private String recommendedProfile;

    public Tip(String country, String tipType, String title, String content, String recommendedProfile) {
        this.country = country;
        this.tipType = tipType;
        this.title = title;
        this.content = content;
        this.recommendedProfile = recommendedProfile;
    }

    public static Tip fromJSON(JSONObject json) throws JSONException {
        Tip tip = new Tip(json.getString("country"), json.getString("tipType"), json.getString("title"), json.getString("content"), json.getString("recommendedProfile"));
        return tip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTipType() {
        return tipType;
    }

    public void setTipType(String tipType) {
        this.tipType = tipType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRecommendedProfile() {
        return recommendedProfile;
    }

    public void setRecommendedProfile(String recommendedProfile) {
        this.recommendedProfile = recommendedProfile;
    }
}
