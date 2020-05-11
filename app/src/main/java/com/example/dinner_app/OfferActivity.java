package com.example.dinner_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class OfferActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.add_new));
        setContentView(R.layout.activity_offer);

        paymentSpinner();

    }
    private void paymentSpinner() {
        final Spinner payment = (Spinner) findViewById(R.id.payment);
        List<String> listPayment = new ArrayList<String>();
        listPayment.add("Gryni pinigai");
        listPayment.add("Kortelė");
        listPayment.add("Gryni arba kortelė");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listPayment);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        payment.setAdapter(dataAdapter);
    }
}
