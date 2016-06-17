package com.example.danie.easypassoportapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ItemDetailsActivity extends AppCompatActivity {
    TextView textTitle = null;
    TextView textDescription = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        this.textTitle = (TextView)findViewById(R.id.textTitle);
        this.textDescription = (TextView)findViewById(R.id.textDescription);

        Bundle b = getIntent().getExtras();
        if(b != null) {
            String title = b.getString("title");
            String description = b.getString("description");

            this.textTitle.setText(title);
            this.textDescription.setText(description);
        }
    }
}
