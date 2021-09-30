package com.example.SyedFatima_AnimeApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //properties that associate the components in the view with the code
        Button btnLogin = findViewById(R.id.btnSignin);
        EditText txtUsername = findViewById(R.id.txtUsername);
        EditText txtPassword = findViewById(R.id.txtPassword);
        TextView lblLoginResult = findViewById(R.id.lblLoginResult);

        //When the login button is clicked it sends a message indicating if the login was succesful or not.
        //The input manager hides the keyboard when the login button is clicked
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                if (txtUsername.getText().toString().equals("admin") && txtPassword.getText().toString().equals("admin")){
                    lblLoginResult.setText("Login correcte");
                } else {
                    lblLoginResult.setText("Login incorrecte");
                }
            }
        });
    }
}