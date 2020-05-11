package com.example.dinner_app;

import android.content.Context;
import android.content.SharedPreferences;

public class User {
    private String usernameLogin, usernameRegister, password, email;
    private static final String PREFERENCES_FILE_NAME = "com.example.dinner_app";
    private static final String USERNAME_KEY = "username";
    private static final String PASSWORD_KEY = "password";
    private static final String REMEMBER_ME_KEY = "rememberMe";
    private SharedPreferences sharedPrefs;

    //REG
    public User(String usernameRegister, String password, String email) {
        this.usernameRegister = usernameRegister;
        this.password = password;
        this.email = email;
    }
    //LOGIN
    public User(Context context) {
        this.sharedPrefs = context.getSharedPreferences(User.PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
    }

    //Username get/set login'ui
    public String getUsernameForLogin() {
        return this.sharedPrefs.getString(USERNAME_KEY, "");
    }
    public void setUsernameForLogin(String username) {
        this.sharedPrefs.edit().putString(USERNAME_KEY, username).commit();
    }

    //Password get/set login'ui
    public String getPasswordForLogin() {
        return this.sharedPrefs.getString(PASSWORD_KEY, "");
    }
    public void setPasswordForLogin(String password) {
        this.sharedPrefs.edit().putString(PASSWORD_KEY, password).commit();
    }

    //Username get/set registracijai
    public String getUsernameRegistration() {
        return this.usernameRegister;
    }
    public void setUsernameForRegistration(String usernameRegister) {
        this.usernameRegister = usernameRegister;
    }

    //Password get/set registracijai
    public String getPasswordForRegistration() {
        return this.password;
    }
    public void setPasswordForRegistration(String password) {
        this.password = password;
    }

    //Email get/set registracijai
    public String getEmail() {
        return this.email;
    }
    public void setEmail() {
        this.email = email;
    }

    public boolean isRemembered() {
        return this.sharedPrefs.getBoolean(REMEMBER_ME_KEY, false);
    }

    public void setRemembered(boolean remembered) {
        this.sharedPrefs.edit().putBoolean(REMEMBER_ME_KEY, remembered).commit();
    }

}
