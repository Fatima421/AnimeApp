package com.example.SyedFatima_AnimeApp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.SyedFatima_AnimeApp.DB.AnimeDBHelper;
import com.example.SyedFatima_AnimeApp.Model.Anime;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentForm#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentForm extends Fragment implements AdapterView.OnItemSelectedListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Spinner spinner;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private AnimeDBHelper dbHelper;
    private SQLiteDatabase db;

    public FragmentForm() {
        // Required empty public constructor
    }

    public FragmentForm(AnimeDBHelper dbHelper, SQLiteDatabase db) {
        this.dbHelper = dbHelper;
        this.db = db;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentForm.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentForm newInstance(String param1, String param2) {
        FragmentForm fragment = new FragmentForm();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_form, container, false);

        //finding all the properties in the view to program in the code
        spinner = (Spinner) v.findViewById(R.id.spinner);
        FloatingActionButton btnAdd = v.findViewById(R.id.addBtn);
        EditText animeNameTxt = v.findViewById(R.id.txtName);
        EditText animeRankingTxt = v.findViewById(R.id.nbrRating);

        //an array to be able to show the list in the spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.genres, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        //this button allows you to add an anime to the list getting all the values from the different fields.
        btnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String animeName = "";
                String animeGenre = "";
                int animeRanking = 0;
                if (!animeNameTxt.getText().toString().isEmpty()){
                    animeName = animeNameTxt.getText().toString();
                }
                if (!animeRankingTxt.getText().toString().isEmpty()) {
                    animeRanking = Integer.parseInt(animeRankingTxt.getText().toString());
                }
                if (!spinner.getSelectedItem().equals("genre")){
                    animeGenre = spinner.getSelectedItem().toString();
                }
                Anime anime = new Anime(animeName,animeGenre,animeRanking);
                dbHelper.insertAnime(db, anime);
                Toast.makeText(getActivity(), "You have inserted correctly an anime to the list!",
                        Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        if (position > 0) {
            // Notify the selected item text
            Toast.makeText(parent.getContext(), text, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}