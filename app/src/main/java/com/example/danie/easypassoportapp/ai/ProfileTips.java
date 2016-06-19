package com.example.danie.easypassoportapp.ai;

import android.content.Context;

import com.example.danie.easypassoportapp.R;
import com.example.danie.easypassoportapp.models.Profile;
import com.example.danie.easypassoportapp.models.Tip;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by danie on 17/06/2016.
 */
public class ProfileTips {
    private Context context = null;

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

    public ProfileTips(Context context) {
        this.context = context;
    }

    public Tip[] getProfileTips(String fromCountry, String toCountry, Profile profile) throws JSONException {
        InputStream is = this.context.getResources().openRawResource(R.raw.tips);
        JSONArray json = new JSONArray(this.readTextFile(is));

        LinkedList<Tip> tips = new LinkedList<>();

        for (int i = 0; i < json.length(); i++) {
            JSONObject item = json.getJSONObject(i);

            String tipType = item.getString("tipType");
            String country = item.getString("country");
            System.out.println("tipType: " + tipType);
            System.out.println("fromCountry: " + fromCountry);

            if ((tipType.compareTo("LEAVING_THE_COUNTRY") == 0 && country.compareTo(fromCountry) == 0) || (tipType.compareTo("ENTERING_IN_COUNTRY") == 0 && country.compareTo(toCountry) == 0)) {
                Tip tip = new Tip(item.getString("country"), item.getString("country"), item.getString("title"), item.getString("content"), item.get("recommendedProfile").toString());
                tips.add(tip);
            }
        }

        Tip[] result = tips.toArray(new Tip[tips.size()]);
        System.out.println("Tamanho: " + result.length);
        result = this.filterTipsForProfile(result, profile);
        return result;
    }

    private Tip[] filterTipsForProfile(Tip[] tips, Profile profileJSON) throws JSONException {
        LinkedList<Tip> filteredTips = new LinkedList<>();

        for (Tip tip : tips) {
            JSONObject jsonProfile = new JSONObject(profileJSON.toJSON());
            Iterator<String> profileItems = jsonProfile.keys();
            JSONObject jsonRecommendedProfile = new JSONObject(tip.getRecommendedProfile());
            Iterator<String> recommendedProfileItems = jsonRecommendedProfile.keys();

            boolean isRecommendedTip = true;
            while(recommendedProfileItems.hasNext()) {
                String recommendedProfileItem = recommendedProfileItems.next();

                if(jsonProfile.has(recommendedProfileItem)) {
                    String profileItem = recommendedProfileItem;

                    OperatorValue opValue = getOperatorAndValueFromString(jsonRecommendedProfile.getString(profileItem));

                    if (opValue.operator == EnumOperators.EQUALS) {
                        if (!(jsonProfile.getString(profileItem).compareTo(opValue.value) == 0)) {
                            isRecommendedTip = false;
                        }
                    } else if (opValue.operator == EnumOperators.GREATER_THAN) {
                        if (!(new Integer(jsonProfile.getString(profileItem)) > new Integer(opValue.value))) {
                            isRecommendedTip = false;
                        }
                    } else if (opValue.operator == EnumOperators.GREATER_THAN_EQUALS) {
                        if (!(new Integer(jsonProfile.getString(profileItem)) >= new Integer(opValue.value))) {
                            isRecommendedTip = false;
                        }
                    } else if (opValue.operator == EnumOperators.LOWER_THAN) {
                        if (!(new Integer(jsonProfile.getString(profileItem)) < new Integer(opValue.value))) {
                            isRecommendedTip = false;
                        }
                    } else if (opValue.operator == EnumOperators.LOWER_THAN_EQUALS) {
                        if (!(new Integer(jsonProfile.getString(profileItem)) <= new Integer(opValue.value))) {
                            isRecommendedTip = false;
                        }
                    }
                }
            }

            if (isRecommendedTip) {
                filteredTips.add(tip);
            }
        }

        return filteredTips.toArray(new Tip[filteredTips.size()]);
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

    public String readTextFile(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte buf[] = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {

        }
        return outputStream.toString();
    }
}
