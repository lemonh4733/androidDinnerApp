package com.example.dinner_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OfferActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.add_new));
        setContentView(R.layout.activity_offer);

        final EditText title = (EditText) findViewById(R.id.title);

        final RadioGroup deliver = (RadioGroup) findViewById(R.id.radiogroup_deliver);
        final RadioButton[] chose = new RadioButton[1];

        final CheckBox main = (CheckBox) findViewById(R.id.checkbox_main);
        final CheckBox salad = (CheckBox) findViewById(R.id.checkbox_salad);
        final CheckBox soup = (CheckBox) findViewById(R.id.checkbox_soup);
        Button submit = (Button) findViewById(R.id.submit);

        final Spinner payment = (Spinner) findViewById(R.id.payment);
        List<String> listPayment = new ArrayList<String>();
        listPayment.add("Gryni pinigai");
        listPayment.add("Kortelė");
        listPayment.add("Gryni arba kortelė");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listPayment);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        payment.setAdapter(dataAdapter);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        int radioId = deliver.getCheckedRadioButtonId();
                    chose[0] = (RadioButton) findViewById(radioId);

                    StringBuilder dinnerType = new StringBuilder();
                    if (main.isChecked()) {
                        dinnerType.append("Pagrindinis ");
                    }
                    if (salad.isChecked()) {
                        dinnerType.append("Salotos ");
                    }
                    if (soup.isChecked()) {
                        dinnerType.append("Sriuba ");
                    }
                    if(title == null | title.length() == 0) {
                        title.setError(getString((R.string.empty_title)));
                    } else {
                        Toast.makeText(OfferActivity.this, "Pavadinimas: "+ title.getText() +"\nPietų tipas: " + dinnerType.toString() + "\nPristatymas: " + chose[0].getText().toString() + "\nApmokėjimas: " + payment.getSelectedItem(), Toast.LENGTH_SHORT).show();
                    }
            }
        });

    }



}
