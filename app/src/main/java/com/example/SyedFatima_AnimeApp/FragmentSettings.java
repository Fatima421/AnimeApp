package com.example.SyedFatima_AnimeApp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import java.util.Locale;
import java.util.concurrent.Executor;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentSettings#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentSettings extends PreferenceFragmentCompat {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;
    Fragment fragment;

    public FragmentSettings() {
        this.fragment = this;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentSettings.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentSettings newInstance(String param1, String param2) {
        FragmentSettings fragment = new FragmentSettings();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.fragment_settings, rootKey);
        //creating the properties
        ListPreference langPreference = findPreference("language");
        ListPreference themePreference = findPreference("theme");

        //creating the shared preference to save data
        SharedPreferences prefs = getActivity().getSharedPreferences("SharedP", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        //if value is not null then set it to the current language selected
        String language = prefs.getString("lang", null);
        if (language != null) {
            if (langPreference.getValue() != null ) {
                if (language.equals("es")){
                    langPreference.setValue(getString(R.string.spanish));
                }else {
                    langPreference.setValue(getString(R.string.english));
                }
            }
        }

        //if theme is not null then set it to the current theme selected
        if (themePreference.getValue() != null ) {
            if (prefs.getBoolean("nightMode", false)){
                themePreference.setValue(getString(R.string.darkMode));
            }else {
                langPreference.setValue(getString(R.string.lightMode));
            }
        }

        //if language is selected then do this
        langPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                if (newValue.toString().equals(getString(R.string.english))) {
                    //to change the language to english
                    setAppLocale("en");
                    //to save the language choice
                    editor.putString("lang", "en");
                    editor.commit();
                } else if (newValue.toString().equals(getString(R.string.spanish))) {
                    //to change the app language to spanish
                    setAppLocale("es");
                    //to save the language choice
                    editor.putString("lang", "es");
                    editor.commit();
                }
                onCreatePreferences(savedInstanceState, rootKey);
                return true;
            }

        });

        //if theme is selected then do this
        themePreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                if (newValue.toString().equals(getString(R.string.lightMode))) {
                    //to set the light mode
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    //to save the theme in the shared preferences
                    editor.putBoolean("nightMode", false);
                    editor.commit();

                } else if (newValue.toString().equals(getString(R.string.darkMode))) {
                    //to set the dark mode
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    //to save the theme in the shared preferences
                    editor.putBoolean("nightMode", true);
                    editor.commit();
                }
                getActivity().startActivity(getActivity().getIntent());
                getActivity().finish();
                getActivity().overridePendingTransition(0,0);
                return true;
            }

        });

        // This static call will reset default values only on the first ever read
        PreferenceManager.setDefaultValues(getActivity().getBaseContext(), R.xml.fragment_settings, false);

        //properties
        Preference buttonDelete = findPreference("deleteData");

        //when button clicked delete the shared preferences
        buttonDelete.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                biometricPrompt.authenticate(promptInfo);
                return false;
            }
        });

        executor = ContextCompat.getMainExecutor(getContext());
        biometricPrompt = new BiometricPrompt(this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                //Error
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                SharedPreferences prefs = getActivity().getSharedPreferences("SharedP", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.clear().commit();

                //Succeed
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                //Failed
            }
        });
        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle(getString(R.string.biometricTitle))
                .setSubtitle(getString(R.string.biometricMessage))
                .setNegativeButtonText(getString(R.string.close))
                .build();
    }

    //to be able to change application's language
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