package com.example.danie.easypassoportapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.danie.easypassoportapp.adapters.SearchResultAdapter;
import com.example.danie.easypassoportapp.models.Dica;
import com.example.danie.easypassoportapp.models.SearchResultItem;
import com.example.danie.easypassoportapp.models.SearchResultsModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class SearchResultActivity extends AppCompatActivity {
    private ListView searchResultsList = null;
    private SearchResultsModel searchResultsModel = null;
    private Dica[] dicas = null;

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
            String age = b.getString("age");
            String qtSons = b.getString("qtSons");
            String qtTravelsAbroad = b.getString("qtTravelsAbroad");
            String qtPoliceIncomings = b.getString("qtPoliceIncomings");


            // REALIZA A CONSULTA

            InputStream is = SearchResultActivity.this.getApplicationContext().getResources().openRawResource(R.raw.dicas);
            String json = readTextFile(is);;

            try {
                final JSONArray jsonObj = new JSONArray(json);

                this.searchResultsModel = new SearchResultsModel();
                this.dicas = new Dica[jsonObj.length()];
                for (int i = 0; i < jsonObj.length(); i++) {
                    JSONObject item = jsonObj.getJSONObject(i);
                    this.dicas[i] = Dica.fromJSON(item);

                    SearchResultItem dica = new SearchResultItem(i + 1, item.getString("titulo"), item.getString("conteudo"));
                    searchResultsModel.addResultItem(dica);
                }

                // POPULA OS DADOS
                SearchResultAdapter searchResultsAdapter = new SearchResultAdapter(this, searchResultsModel);

                final SearchResultActivity self = this;
                this.searchResultsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent i = new Intent(self, ItemDetailsActivity.class);

                        i.putExtra("title", self.dicas[position].getTitulo());
                        i.putExtra("description", self.dicas[position].getConteudo());
                        startActivity(i);
                    }
                });

                this.searchResultsList.setAdapter(searchResultsAdapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
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
