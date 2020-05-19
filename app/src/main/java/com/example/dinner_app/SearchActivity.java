package com.example.dinner_app;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;



public class SearchActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        addNew();
    }

    private void addNew() {
        Button add = (Button) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View focusView) {
                boolean cancel = false;
                focusView = null;
                Intent activityChangeContentIntent = new Intent(SearchActivity.this, OfferActivity.class);
                Toast.makeText(SearchActivity.this, "Pridėti naują įrašą", Toast.LENGTH_SHORT).show();
                startActivity(activityChangeContentIntent);
            }
        });
    }
}
