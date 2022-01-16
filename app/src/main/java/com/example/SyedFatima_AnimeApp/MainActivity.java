package com.example.SyedFatima_AnimeApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Context context = this;
        SharedPreferences sharedPref = context.getSharedPreferences(
                "SharedP", Context.MODE_PRIVATE);
        String language = sharedPref.getString("lang", null);
        if (language != null) {
            setAppLocale(language);
        }

        //loading the saved theme
        Boolean isThemeDark = sharedPref.getBoolean("nightMode", false);
        if (isThemeDark) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        //sleep per poder mostrar el splash screen
        SystemClock.sleep(500);
        setTheme(R.style.Theme_Anime_app);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //properties that associate the components in the view with the code
        Button btnLogin = findViewById(R.id.btnSignin);
        EditText txtUsername = findViewById(R.id.txtUsername);
        EditText txtPassword = findViewById(R.id.txtPassword);
        SharedPreferences prefs= getSharedPreferences("SharedP", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        Intent goToPrincipalScreen = new Intent(this, PrincipalScreen.class);

        if(prefs.getBoolean("login", false)) {
            startActivity(goToPrincipalScreen);
            overridePendingTransition(0,0);
        }

        //When the login button is clicked it sends a message indicating if the login was succesful or not.
        //The input manager hides the keyboard when the login button is clicked
        //If the correct password is inputted the button will take you to another scren
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (txtUsername.getText().toString().equals("admin") && txtPassword.getText().toString().equals("admin")){
                    editor.putBoolean("login", true);
                    editor.commit();
                    startActivity(goToPrincipalScreen);
                } else {
                }
            }
        });
    }
    private void setAppLocale(String localeCode){
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration config = res.getConfiguration();

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN_MR1){
            config.setLocale(new Locale(localeCode.toLowerCase()));
        } else {
            config.locale = new Locale(localeCode.toLowerCase());
        }
        res.updateConfiguration(config, dm);
    }
}