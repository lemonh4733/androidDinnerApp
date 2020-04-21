package com.example.dinner_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
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
                    Intent activityChangeContentIntent = new Intent(RegisterActivity.this, MainActivity.class);
                    Toast.makeText(RegisterActivity.this, "Vartotojo vardas: " + usernameText + "\n" + "Slaptažodis: " + paswText, Toast.LENGTH_SHORT).show();
                    startActivity(activityChangeContentIntent);
                }

            }
        });
    }
    private boolean isValid(String credentials,String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(credentials);
        return matcher.matches();
    }
}
