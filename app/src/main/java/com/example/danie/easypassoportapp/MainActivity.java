package com.example.danie.easypassoportapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.danie.easypassoportapp.adapters.CountriesAdapter;
import com.example.danie.easypassoportapp.adapters.TravelReasonsAdapter;
import com.example.danie.easypassoportapp.models.Country;
import com.example.danie.easypassoportapp.models.TravelReason;

public class MainActivity extends AppCompatActivity {
    Spinner spinnerCowntryFrom = null;
    Spinner spinnerCowntryTo = null;
    Spinner spinnerTravelReason = null;
    TextView textAge = null;
    TextView textQtSons = null;
    TextView textQtTravelsAbroad = null;
    TextView textQtPoliceRecords = null;
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
        this.textQtPoliceRecords = (TextView)findViewById(R.id.textQtPoliceRecords);

        final Country[] countries = new Country[] {
                new Country("Argentina", "ARGENTINA"),
                new Country("Bélgica", "BELGIUM"),
                new Country("Brazil", "BRAZIL"),
                new Country("Croácia", "CROATIA"),
                new Country("Dinamarca", "DENMARK"),
                new Country("Estados Unidos", "UNITED_STATES")
        };

        final TravelReason[] travelReasons = new TravelReason[] {
                new TravelReason("Turismo", "TOURISM"),
                new TravelReason("Estudos", "STUDIES"),
                new TravelReason("Trabalho", "JOB"),
                new TravelReason("Missao Diplomática", "DIPLOMATIC_MISSION"),
        };

        CountriesAdapter cowntriesAdapter = new CountriesAdapter(this, android.R.layout.simple_spinner_dropdown_item, countries);

        TravelReasonsAdapter travelReasonAdapter = new TravelReasonsAdapter(this,
                android.R.layout.simple_spinner_dropdown_item, travelReasons);
        
        spinnerCowntryFrom.setAdapter(cowntriesAdapter);
        spinnerCowntryTo.setAdapter(cowntriesAdapter);
        spinnerTravelReason.setAdapter(travelReasonAdapter);

        final MainActivity self = this;
        this.btnSearchTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(self, SearchResultActivity.class);
                i.putExtra("fromCountry", countries[self.spinnerCowntryFrom.getSelectedItemPosition()].getAlias());
                i.putExtra("toCountry", countries[self.spinnerCowntryTo.getSelectedItemPosition()].getAlias());
                i.putExtra("travelReason", travelReasons[self.spinnerTravelReason.getSelectedItemPosition()].getAlias());
                i.putExtra("age", Integer.parseInt(self.textAge.getText().toString()));
                i.putExtra("qtSons", Integer.parseInt(self.textQtSons.getText().toString()));
                i.putExtra("qtTravelsAbroad", Integer.parseInt(self.textQtTravelsAbroad.getText().toString()));
                i.putExtra("qtPoliceRecords", Integer.parseInt(self.textQtPoliceRecords.getText().toString()));

                startActivity(i);
            }
        });
    }
}
