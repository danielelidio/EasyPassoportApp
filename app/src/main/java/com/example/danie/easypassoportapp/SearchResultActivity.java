package com.example.danie.easypassoportapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.danie.easypassoportapp.adapters.SearchResultAdapter;
import com.example.danie.easypassoportapp.ai.ProfileTips;
import com.example.danie.easypassoportapp.models.Profile;
import com.example.danie.easypassoportapp.models.SearchResultItem;
import com.example.danie.easypassoportapp.models.SearchResultsModel;
import com.example.danie.easypassoportapp.models.Tip;

import org.json.JSONException;

public class SearchResultActivity extends AppCompatActivity {
    private ListView searchResultsList = null;
    private SearchResultsModel searchResultsModel = null;
    private Tip[] tips = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        this.searchResultsList = (ListView)findViewById(R.id.searchResultsList);

        Bundle b = getIntent().getExtras();
        if(b != null) {
            String fromCountry = b.getString("fromCountry");
            String toCountry = b.getString("toCountry");
            String travelReason = b.getString("travelReason");
            int age = b.getInt("age");
            int qtSons = b.getInt("qtSons");
            int qtTravelsAbroad = b.getInt("qtTravelsAbroad");
            int qtPoliceRecords = b.getInt("qtPoliceRecords");


            // REALIZA A CONSULTA
            try {
                ProfileTips profileTips = new ProfileTips(this.getApplicationContext());
                this.tips = profileTips.getProfileTips(fromCountry, toCountry, new Profile(age, qtTravelsAbroad, qtPoliceRecords, qtSons, travelReason));

                //final JSONArray jsonObj = new JSONArray(json);
                this.searchResultsModel = new SearchResultsModel();
                int i = 0;
                for (Tip tip : this.tips) {
                    SearchResultItem resultItem = new SearchResultItem(i + 1, tip.getTitle(), tip.getContent());
                    this.searchResultsModel.addResultItem(resultItem);
                    i++;
                }

                // POPULA OS DADOS
                SearchResultAdapter searchResultsAdapter = new SearchResultAdapter(this, searchResultsModel);

                final SearchResultActivity self = this;
                this.searchResultsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent i = new Intent(self, ItemDetailsActivity.class);

                        i.putExtra("title", self.tips[position].getTitle());
                        i.putExtra("description", self.tips[position].getContent());
                        startActivity(i);
                    }
                });

                this.searchResultsList.setAdapter(searchResultsAdapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
