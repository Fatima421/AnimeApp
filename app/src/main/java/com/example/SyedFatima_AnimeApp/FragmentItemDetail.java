package com.example.SyedFatima_AnimeApp;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.SyedFatima_AnimeApp.DB.AnimeDBHelper;
import com.example.SyedFatima_AnimeApp.Model.Anime;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentItemDetail#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentItemDetail extends Fragment implements AdapterView.OnItemSelectedListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private AnimeDBHelper dbHelper;
    private SQLiteDatabase db;
    String animeGenreFromSpinner = "";

    public FragmentItemDetail() {
        // Required empty public constructor
    }

    public FragmentItemDetail(AnimeDBHelper dbHelper, SQLiteDatabase db) {
        this.dbHelper = dbHelper;
        this.db = db;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentItemDetail.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentItemDetail newInstance(String param1, String param2) {
        FragmentItemDetail fragment = new FragmentItemDetail();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_item_detail, container, false);

        //to push the view up when the fragment shows
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams. SOFT_INPUT_ADJUST_RESIZE);

        //initializing the bundle
        Bundle bundle = getArguments();
        Anime anime = (Anime) bundle.getSerializable("anime");

        //properties
        EditText animeTitle = v.findViewById(R.id.animeTitleTxt);
        EditText animeGenre = v.findViewById(R.id.animeGenereTxt);
        EditText animeRanking = v.findViewById(R.id.animeRankTxt);
        ImageView animeImage = v.findViewById(R.id.animeImage);
        Button buttonEdit = v.findViewById(R.id.buttonEdit);
        Button buttonSave = v.findViewById(R.id.buttonSave);
        Spinner spinnerGenre = (Spinner) v.findViewById(R.id.animeGenreSpinner);

        //an array to be able to show the list in the spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.genres, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGenre.setAdapter(adapter);
        spinnerGenre.setOnItemSelectedListener(this);

        //showing item's info to the screen
        animeTitle.setText(anime.getName());
        animeGenre.setText(anime.getGenre());
        animeRanking.setText(String.valueOf(anime.getRanking()));
        //animeImage.setImageDrawable();

        //disabling all the text fields
        animeTitle.setEnabled(false);
        animeGenre.setEnabled(false);
        animeRanking.setEnabled(false);
        animeGenre.setVisibility(View.VISIBLE);
        spinnerGenre.setVisibility(View.INVISIBLE);

        // When the button edit has been clicked set all text fields to enabled
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animeTitle.setEnabled(true);
                animeGenre.setEnabled(true);
                animeRanking.setEnabled(true);
                animeGenre.setVisibility(View.INVISIBLE);
                spinnerGenre.setVisibility(View.VISIBLE);

            }
        });

        // When the save button has been clicked save the new values in the db
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animeTitle.setEnabled(false);
                animeGenre.setEnabled(false);
                animeRanking.setEnabled(false);
                if (!spinnerGenre.getSelectedItem().equals("genre")){
                    animeGenreFromSpinner = spinnerGenre.getSelectedItem().toString();
                }
                dbHelper.updateData(db, animeTitle.getText().toString(), animeGenreFromSpinner, animeRanking.getText().toString());
            }
        });

        return v;
    }

    // When an item of the spinner has been selected show a toast
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