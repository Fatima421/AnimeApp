package com.example.SyedFatima_AnimeApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.SyedFatima_AnimeApp.DB.AnimeDBHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.Normalizer;

public class PrincipalScreen extends AppCompatActivity {

    private AnimeDBHelper dbHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_screen);

        //Creation of the dbHelper
        dbHelper = new AnimeDBHelper(this);
        db = dbHelper.getWritableDatabase();

        //Creation of the properties
        BottomNavigationView bottomNav = findViewById(R.id.main_menu);

        //Starting the Principal Screen initializing the FragmentHome
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentHome()).commit();

        //Depending on which Fragment is selected create a new Fragment of that type
        bottomNav.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            switch (item.getItemId()){
                case R.id.nav_home:
                    selectedFragment = new FragmentHome(dbHelper, db);
                    break;

                case R.id.nav_list:
                    selectedFragment = new FragmentList(dbHelper, db);
                    break;

                case R.id.nav_add:
                    selectedFragment = new FragmentForm(dbHelper, db);
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

            return true;
        });
    }

    //destroying the db
    @Override
    protected void onDestroy() {
        dbHelper.close();
        db.close();
        super.onDestroy();
    }

}