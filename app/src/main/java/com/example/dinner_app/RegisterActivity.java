package com.example.dinner_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    private static final String DB_URL = "https://dinnerappkitm.000webhostapp.com/reg.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle(getString(R.string.register));
        final EditText username = (EditText) findViewById(R.id.username);
        final EditText password = (EditText) findViewById(R.id.password);
        final EditText conf_password = (EditText) findViewById(R.id.conf_password);
        final EditText email = (EditText) findViewById(R.id.email);
        Button register = (Button) findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View focusView) {
                boolean cancel = false;
                focusView = null;

                final String usernameRegex = "^[a-zA-Z]{3,20}+$";
                final String passwordRegex = "^[a-zA-Z.!@_]{5,20}+$";
                final String emailRegex = "^[a-zA-Z.!@-]{10,50}+$";
                if(isValid(username.getText().toString(), usernameRegex) == false) {
                    username.setError(getString((R.string.register_username_error)));
                    cancel = true;
                }
                else if (isValid(password.getText().toString(), passwordRegex) == false) {
                    password.setError(getString((R.string.register_password_error)));
                    cancel = true;
                }
                else if (!password.getText().toString().equals(conf_password.getText().toString())) {
                    conf_password.setError(getString((R.string.register_passwordMatch_error)));
                    cancel = true;
                }
                else if (isValid(email.getText().toString(), emailRegex) == false) {
                    email.setError(getString((R.string.register_email_error)));
                    cancel = true;
                }
                else {
                    String usernameText = username.getText().toString();
                    String paswText = password.getText().toString();
                    String emailText = email.getText().toString();
                    User user = new User(
                            usernameText,
                            paswText,
                            emailText
                    );
                    insertToDB(user);
                }

            }
        });
    }
    private boolean isValid(String credentials,String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(credentials);
        return matcher.matches();
    }

    private void insertToDB(User user)
    {
        class NewEntry extends AsyncTask<String, Void, String> {

            ProgressDialog loading;
            DB db = new DB();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(RegisterActivity.this, getResources().getString(R.string.new_user_database_info), null, true, true);
            }

            @Override
            protected String doInBackground(String... strings) {
                HashMap<String, String> user = new HashMap<String, String>();
                user.put("username", strings[0]);
                user.put("password", strings[1]);
                user.put("email", strings[2]);
                user.put("action", "insert");

                String result = db.sendPostRequest(DB_URL, user);
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Toast.makeText(RegisterActivity.this, "Sėkmingai užsiregistruota", Toast.LENGTH_SHORT).show();
                Intent redirect = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(redirect);
            }

        }
        NewEntry newEntry = new NewEntry();
        newEntry.execute(
                user.getUsernameRegistration(),
                user.getPasswordForRegistration(),
                user.getEmail()
        );

    }

}
