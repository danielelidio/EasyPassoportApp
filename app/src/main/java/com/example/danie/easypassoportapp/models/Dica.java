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
public class Dica {
    private String pais;
    private String tipoDica;
    private String titulo;
    private String conteudo;
    private String perfilIndicado;

    public Dica(String pais, String tipoDica, String titulo, String conteudo, String perfilIndicado) {
        this.pais = pais;
        this.tipoDica = tipoDica;
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.perfilIndicado = perfilIndicado;
    }

    public static Dica fromJSON(JSONObject json) throws JSONException {
        Dica dica = new Dica(json.getString("pais"), json.getString("tipoDica"), json.getString("titulo"), json.getString("conteudo"), json.getString("perfilIndicado"));
        return dica;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getTipoDica() {
        return tipoDica;
    }

    public void setTipoDica(String tipoDica) {
        this.tipoDica = tipoDica;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getPerfilIndicado() {
        return perfilIndicado;
    }

    public void setPerfilIndicado(String perfilIndicado) {
        this.perfilIndicado = perfilIndicado;
    }
    
    
}
