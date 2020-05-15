package com.example.dinner_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
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
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OfferActivity extends AppCompatActivity {
    private static final String DB_URL = "https://dinnerappkitm.000webhostapp.com/post.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.add_new));
        setContentView(R.layout.activity_offer);

        final EditText title = (EditText) findViewById(R.id.title);
        final EditText price = (EditText) findViewById(R.id.price);

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
                    }
                    else if(price == null | price.length() == 0){
                        price.setError(getString((R.string.empty_price)));
                    }else {
                        Offer dinner = new Offer(
                                                title.getText().toString(),
                                                dinnerType.toString(),
                                                chose[0].getText().toString(),
                                                Double.parseDouble(price.getText().toString()),
                                                payment.getSelectedItem().toString()
                                        );
                        insertToDB(dinner);
                    }
            }
        });

    }
    private void insertToDB(Offer dinner)
    {
        class NewEntry extends AsyncTask<String, Void, String> {

            ProgressDialog loading;
            DB db = new DB();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(OfferActivity.this, getResources().getString(R.string.new_offer_database_info), null, true, true);
            }

            @Override
            protected String doInBackground(String... strings) {
                HashMap<String, String> pietus = new HashMap<String, String>();
                pietus.put("title", strings[0]);
                pietus.put("dinnerType", strings[1]);
                pietus.put("deliver", strings[2]);
                pietus.put("price", strings[3]);
                pietus.put("payment", strings[4]);
                pietus.put("action", "insert");

                String result = db.sendPostRequest(DB_URL, pietus);
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Toast.makeText(OfferActivity.this, "Įrašas sėkmingai įkeltas", Toast.LENGTH_SHORT).show();
                Intent redirect = new Intent(OfferActivity.this, SearchActivity.class);
                startActivity(redirect);
            }

        }
        NewEntry newEntry = new NewEntry();
        newEntry.execute(
                dinner.getTitle(),
                dinner.getDinnerType(),
                dinner.getDeliver(),
                Double.toString(dinner.getPrice()),
                dinner.getPayment()
        );

    }


}
