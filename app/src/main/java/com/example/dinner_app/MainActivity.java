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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText username = (EditText) findViewById(R.id.username);
        final EditText password = (EditText) findViewById(R.id.password);
        Button login = (Button) findViewById(R.id.login);;
        Button register = (Button) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View focusView) {
                    Intent goToRegister = new Intent(MainActivity.this, RegisterActivity.class);
                    startActivity(goToRegister);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View focusView) {

                boolean cancel = false;
                focusView = null;

                final String usernameRegex = "^[a-zA-Z]{3,20}+$";
                final String passwordRegex = "^[a-zA-Z.!@_]{5,20}+$";
                if(isValid(username.getText().toString(), usernameRegex) == false) {
                    username.setError(getString((R.string.login_username_error)));
                    cancel = true;
                }
                else if (isValid(password.getText().toString(), passwordRegex) == false) {
                    password.setError(getString((R.string.login_password_error)));
                    cancel = true;
                }
                else {
                    String usernameText = username.getText().toString();
                    Toast.makeText(MainActivity.this, "Sveikas prisijunges: " + usernameText, Toast.LENGTH_SHORT).show();
                    Intent activityChangeContentIntent = new Intent(MainActivity.this, SearchActivity.class);
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
