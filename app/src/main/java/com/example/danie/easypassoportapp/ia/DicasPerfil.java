package com.example.danie.easypassoportapp.ia;

import com.example.danie.easypassoportapp.models.Dica;
import com.example.danie.easypassoportapp.models.Perfil;
import com.example.danie.easypassoportapp.utils.IO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Daniel
 */
public class DicasPerfil {

    private static String caminhoArquivoDicas = "/src/main/java/com/example/danie/easypassoportapp/dicas/dicas.json";
    private static String caminhoPerfilUsuario = "/src/main/java/com/example/danie/easypassoportapp/perfil/perfil.json";

    private static enum EnumOperators {

        EQUALS {
                    public String toString() {
                        return "=";
                    }
                },
        GREATER_THAN {
                    public String toString() {
                        return ">";
                    }
                },
        LOWER_THAN {
                    public String toString() {
                        return "<";
                    }
                },
        GREATER_THAN_EQUALS {
                    public String toString() {
                        return ">=";
                    }
                },
        LOWER_THAN_EQUALS {
                    public String toString() {
                        return "<=";
                    }
                }
    };

    private static class OperatorValue {

        public EnumOperators operator;
        public String value;
    };

    public static Dica[] getDicaPaisOrigem(String paisOrigem) throws JSONException {

        IO io = new IO(caminhoArquivoDicas, true);

        StringBuilder dicasArquivo = new StringBuilder();
        for (int i = 1; i <= io.getNumberOfLines(); i++) {
            try {
                dicasArquivo.append(io.getLine(i) + "\n");
            } catch (Exception ex) {
                Logger.getLogger(DicasPerfil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        JSONArray json = new JSONArray(dicasArquivo.toString());

        LinkedList<Dica> dicasSairPais = new LinkedList<>();
        for (int i = 0; i < json.length(); i++) {
            JSONObject item = json.getJSONObject(i);

            if (item.get("tipoDica").toString().compareTo("SAIR_PAIS") == 0 && item.get("pais").toString().compareTo(paisOrigem) == 0) {
                Dica dica = new Dica(item.getString("pais"), item.getString("tipoDica"), item.getString("titulo"), item.getString("conteudo"), item.get("perfilIndicado").toString());

                dicasSairPais.add(dica);
            }
        }

        Dica[] result = dicasSairPais.toArray(new Dica[dicasSairPais.size()]);
        result = filtraDicasParaPerfil(result);
        return result;
    }

    public static Dica[] getDicaPaisDestino(String paisDestino) throws JSONException {
        IO io = new IO(caminhoArquivoDicas, true);

        StringBuilder dicasArquivo = new StringBuilder();
        for (int i = 1; i <= io.getNumberOfLines(); i++) {
            try {
                dicasArquivo.append(io.getLine(i) + "\n");
            } catch (Exception ex) {
                Logger.getLogger(DicasPerfil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        JSONArray json = new JSONArray(dicasArquivo.toString());

        LinkedList<Dica> dicasEntrarPais = new LinkedList<>();
        for (int i = 0; i < json.length(); i++) {
            JSONObject item = json.getJSONObject(i);

            if (item.get("tipoDica").toString().compareTo("ENTRAR_PAIS") == 0 && item.get("pais").toString().compareTo(paisDestino) == 0) {
                Dica dica = new Dica(item.getString("pais"), item.getString("tipoDica"), item.getString("titulo"), item.getString("conteudo"), item.get("perfilIndicado").toString());

                dicasEntrarPais.add(dica);
            }
        }

        Dica[] result = dicasEntrarPais.toArray(new Dica[dicasEntrarPais.size()]);
        result = filtraDicasParaPerfil(result);

        return result;
    }
    
    private static Dica[] filtraDicasParaPerfil(Dica[] dicas) throws JSONException {
        IO io = new IO(caminhoPerfilUsuario, true);
        
        StringBuilder perfilArquivo = new StringBuilder();
        for (int i = 1; i <= io.getNumberOfLines(); i++) {
            try {
                perfilArquivo.append(io.getLine(i) + "\n");
            } catch (Exception ex) {
                Logger.getLogger(DicasPerfil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return filtraDicasParaPerfil(dicas, (new Perfil(new JSONObject(perfilArquivo.toString().trim()))));
    }
    
    private static Dica[] filtraDicasParaPerfil(Dica[] dicas, Perfil perfilJSON) throws JSONException {
        LinkedList<Dica> dicasFiltradas = new LinkedList<>();
        
        for (Dica dica : dicas) {
            JSONObject jsonArquivoPerfil = new JSONObject(perfilJSON.toJSON());
            Iterator<String> itensPerfil = jsonArquivoPerfil.keys();

            boolean dicaRecomendada = true;
            while (itensPerfil.hasNext()) {
                String itemPerfil = itensPerfil.next();

                JSONObject jsonPerfilIndicado = new JSONObject(dica.getPerfilIndicado());

                if (jsonPerfilIndicado.has(itemPerfil)) {
                    System.out.println("Item Perfil: " + jsonArquivoPerfil.getString(itemPerfil));
                    System.out.println("Restrição: " + jsonPerfilIndicado.getString(itemPerfil));

                    OperatorValue opValue = getOperatorAndValueFromString(jsonPerfilIndicado.getString(itemPerfil));

                    System.out.println("Valor: " + opValue.value);
                    System.out.println("Operador: " + opValue.operator.toString());

                    if (opValue.operator == EnumOperators.EQUALS) {
                        if (!(jsonArquivoPerfil.getString(itemPerfil).compareTo(opValue.value) == 0)) {
                            dicaRecomendada = false;
                            System.out.println("\tNão é =");
                        }
                    } else if (opValue.operator == EnumOperators.GREATER_THAN) {
                        if (!(new Integer(jsonArquivoPerfil.getString(itemPerfil)) > new Integer(opValue.value))) {
                            dicaRecomendada = false;
                            System.out.println("\tNão é >");
                        }
                    } else if (opValue.operator == EnumOperators.GREATER_THAN_EQUALS) {
                        if (!(new Integer(jsonArquivoPerfil.getString(itemPerfil)) >= new Integer(opValue.value))) {
                            dicaRecomendada = false;
                            System.out.println("\tNão é >=");
                        }
                    } else if (opValue.operator == EnumOperators.LOWER_THAN) {
                        if (!(new Integer(jsonArquivoPerfil.getString(itemPerfil)) < new Integer(opValue.value))) {
                            dicaRecomendada = false;
                            System.out.println("\tNão é <");
                        }
                    } else if (opValue.operator == EnumOperators.LOWER_THAN_EQUALS) {
                        if (!(new Integer(jsonArquivoPerfil.getString(itemPerfil)) <= new Integer(opValue.value))) {
                            dicaRecomendada = false;
                            System.out.println("\tNão é <=");
                        }
                    }
                }
            }

            if (dicaRecomendada) {
                dicasFiltradas.add(dica);
            }
        }

        return dicasFiltradas.toArray(new Dica[dicasFiltradas.size()]);
    }

    private static OperatorValue getOperatorAndValueFromString(String valor) {
        EnumOperators operator = null;
        int tamOperador = 0;

        if (valor.length() > 2 && valor.substring(0, 2).compareTo(">=") == 0) {
            tamOperador = 2;
            operator = EnumOperators.GREATER_THAN_EQUALS;
        } else if (valor.length() > 2 && valor.substring(0, 2).compareTo("<=") == 0) {
            tamOperador = 2;
            operator = EnumOperators.LOWER_THAN_EQUALS;
        } else if (valor.length() > 1 && valor.substring(0, 1).compareTo(">") == 0) {
            tamOperador = 1;
            operator = EnumOperators.GREATER_THAN;
        } else if (valor.length() > 1 && valor.substring(0, 1).compareTo("<") == 0) {
            tamOperador = 1;
            operator = EnumOperators.LOWER_THAN;
        } else if (valor.length() > 1 && valor.substring(0, 1).compareTo("=") == 0) {
            tamOperador = 1;
            operator = EnumOperators.EQUALS;
        } else {
            operator = EnumOperators.EQUALS;
        }

        valor = valor.substring(tamOperador, valor.length());

        OperatorValue opValue = new OperatorValue();
        opValue.operator = operator;
        opValue.value = valor.trim();

        return opValue;
    }

    public static Perfil getPerfilUsuario() throws JSONException {
        IO io = new IO(caminhoPerfilUsuario, true);

        StringBuilder perfilArquivo = new StringBuilder();
        for (int i = 1; i <= io.getNumberOfLines(); i++) {
            try {
                perfilArquivo.append(io.getLine(i) + "\n");
            } catch (Exception ex) {
                Logger.getLogger(DicasPerfil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        JSONObject jsonArquivoPerfil = new JSONObject(perfilArquivo.toString());
        Iterator<String> itensPerfil = jsonArquivoPerfil.keys();
        
        Perfil perfil = new Perfil();
        
        while(itensPerfil.hasNext()) {
            String chave = itensPerfil.next();
            String valor = jsonArquivoPerfil.getString(chave).trim();
            perfil.set(chave, valor);
        }
        
        return perfil;
    }
}
