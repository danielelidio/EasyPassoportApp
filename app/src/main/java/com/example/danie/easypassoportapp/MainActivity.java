package com.example.danie.easypassoportapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Spinner spinnerCowntryFrom = null;
    Spinner spinnerCowntryTo = null;
    Spinner spinnerTravelReason = null;
    TextView textAge = null;
    TextView textQtSons = null;
    TextView textQtTravelsAbroad = null;
    TextView textQtPoliceIncomings = null;
    Button btnSearchTips = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.spinnerCowntryFrom = (Spinner) findViewById(R.id.spinnerCowntryFrom);
        this.spinnerCowntryTo = (Spinner) findViewById(R.id.spinnerCowntryTo);
        this.spinnerTravelReason = (Spinner) findViewById(R.id.spinnerTravelReason);
        this.btnSearchTips = (Button) findViewById(R.id.btnSearchTips);
        this.textAge = (TextView)findViewById(R.id.textAge);
        this.textQtSons = (TextView)findViewById(R.id.textQtSons);
        this.textQtTravelsAbroad = (TextView)findViewById(R.id.textQtTravelsAbroad);
        this.textQtPoliceIncomings = (TextView)findViewById(R.id.textQtPoliceIncomings);

        String[] countriesItems = new String[] { "Argentina", "Bélgica", "Brazil", "Croácia", "Dinamarca" };
        String[] travelReasonItems = new String[] { "Turismo", "Estudos", "Trabalho", "Missão Diplomática" };
        
        ArrayAdapter<String> cowntriesAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, countriesItems);
        cowntriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> travelReasonAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, travelReasonItems);
        travelReasonAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        spinnerCowntryFrom.setAdapter(cowntriesAdapter);

        spinnerCowntryFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerCowntryTo.setAdapter(cowntriesAdapter);

        spinnerCowntryTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerTravelReason.setAdapter(travelReasonAdapter);

        spinnerTravelReason.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final MainActivity self = this;
        this.btnSearchTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(self, SearchResultActivity.class);
                i.putExtra("fromCountry", ((String) self.spinnerCowntryFrom.getSelectedItem()));
                i.putExtra("toCountry", ((String) self.spinnerCowntryTo.getSelectedItem()));
                i.putExtra("travelReason", ((String) self.spinnerTravelReason.getSelectedItem()));
                i.putExtra("age", self.textAge.getText());
                i.putExtra("qtSons", self.textQtSons.getText());
                i.putExtra("qtTravelsAbroad", self.textQtTravelsAbroad.getText());
                i.putExtra("qtPoliceIncomings", self.textQtPoliceIncomings.getText());

                startActivity(i);
//                try {
//                    Dica[] dicasSairPais = DicasPerfil.getDicaPaisOrigem("BRAZIL");
//                    Dica[] dicasEntrarPais = DicasPerfil.getDicaPaisDestino("EUA");
//
//                    Log.d("", "Dicas para Sair do Brazil:");
//                    for (Dica dica : dicasSairPais) {
//                        Log.d("", "\t" + dica.getTitulo());
//                    }
//
//                    Log.d("", "\nDicas para Entrar nos EUA:");
//                    for (Dica dica : dicasEntrarPais) {
//                        Log.d("", "\t" + dica.getTitulo());
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
            }
        });
    }
}
