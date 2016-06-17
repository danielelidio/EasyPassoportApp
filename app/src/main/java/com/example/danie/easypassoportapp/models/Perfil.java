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
public class Perfil {
//    private int idade;
//    private int qtViagensExterior;
//    private int qtPassagensPolicia;
//    private int qtFilhos;
//    private String motivoViagem;
    
    private JSONObject json = null;
    
    public Perfil() {
        this.json = new JSONObject();
    }
    public Perfil(JSONObject perfilJSON) {
        this.json = perfilJSON;
    }

    public Perfil(int idade, int qtViagensExterior, int qtPassagensPolicia, int qtFilhos, String motivoViagem) throws JSONException {
        this.json = new JSONObject();
        this.json.put("idade", idade);
        this.json.put("qtViagensExterior", qtViagensExterior);
        this.json.put("qtPassagensPolicia", qtPassagensPolicia);
        this.json.put("qtFilhos", qtFilhos);
        this.json.put("motivoViagem", motivoViagem);
    }
    
    public void set(String chave, String valor) throws JSONException {
        this.json.put(chave, valor);
    }

    public String getMotivoViagem() throws JSONException {
        return this.json.getString("motivoViagem");
    }

    public void setMotivoViagem(String motivoViagem) throws JSONException {
        this.json.put("motivoViagem", motivoViagem);
    }
    
    public int getIdade() throws JSONException {
        return new Integer(this.json.getString("idade"));
    }

    public void setIdade(int idade) throws JSONException {
        this.json.put("idade", idade);
    }

    public int getQtViagensExterior() throws JSONException {
        return new Integer(this.json.getString("qtViagensExterior"));
    }

    public void setQtViagensExterior(int qtViagensExterior) throws JSONException {
        this.json.put("qtViagensExterior", qtViagensExterior);
    }

    public int getQtPassagensPolicia() throws JSONException {
        return new Integer(this.json.getString("qtPassagensPolicia"));
    }

    public void setQtPassagensPolicia(int qtPassagensPolicia) throws JSONException {
        this.json.put("qtPassagensPolicia", qtPassagensPolicia);
    }

    public int getQtFilhos() throws JSONException {
        return new Integer(this.json.getString("qtFilhos"));
    }

    public void setQtFilhos(int qtFilhos) throws JSONException {
        this.json.put("qtFilhos", qtFilhos);
    }
    
    public String toJSON() throws JSONException {
        return this.json.toString();
    }
    
}
