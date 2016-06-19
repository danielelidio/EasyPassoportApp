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
public class Profile {

    private JSONObject json = null;
    
    public Profile() {
        this.json = new JSONObject();
    }
    public Profile(JSONObject perfilJSON) {
        this.json = perfilJSON;
    }

    public Profile(int age, int qtTravelsAbroad, int qtPolicerecords, int qtSons, String travelReason) throws JSONException {
        this.json = new JSONObject();
        this.json.put("age", age);
        this.json.put("qtTravelsAbroad", qtTravelsAbroad);
        this.json.put("qtPolicerecords", qtPolicerecords);
        this.json.put("qtSons", qtSons);
        this.json.put("travelReason", travelReason);
    }
    
    public void set(String key, String value) throws JSONException {
        this.json.put(key, value);
    }

    public String getTravelReason() throws JSONException {
        return this.json.getString("travelReason");
    }

    public void setTravelReason(String travelReason) throws JSONException {
        this.json.put("travelReason", travelReason);
    }
    
    public int getAge() throws JSONException {
        return new Integer(this.json.getString("age"));
    }

    public void setAge(int age) throws JSONException {
        this.json.put("age", age);
    }

    public int getQtTravelsAbroad() throws JSONException {
        return new Integer(this.json.getString("qtTravelsAbroad"));
    }

    public void setQtTravelsAbroad(int qtTravelsAbroad) throws JSONException {
        this.json.put("qtTravelsAbroad", qtTravelsAbroad);
    }

    public int getQtPoliceRecords() throws JSONException {
        return new Integer(this.json.getString("qtPoliceRecords"));
    }

    public void setQtPoliceRecords(int qtPoliceRecords) throws JSONException {
        this.json.put("qtPoliceRecords", qtPoliceRecords);
    }

    public int getQtSons() throws JSONException {
        return new Integer(this.json.getString("qtSons"));
    }

    public void setQtSons(int qtSons) throws JSONException {
        this.json.put("qtSons", qtSons);
    }
    
    public String toJSON() throws JSONException {
        return this.json.toString();
    }
    
}
