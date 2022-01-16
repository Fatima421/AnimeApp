package com.example.SyedFatima_AnimeApp.DB;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.SyedFatima_AnimeApp.DB.AnimeDBCommands.*;
import com.example.SyedFatima_AnimeApp.Model.Anime;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class AnimeDBHelper extends SQLiteOpenHelper {
    //properties
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "anime.db";
    int id;

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + AnimeEntry.TABLE_NAME + "(" + AnimeEntry.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + AnimeEntry.COLUMN_NAME_TITLE + " TEXT, " + AnimeEntry.COLUMN_GENRE_TITLE + " TEXT, " + AnimeEntry.COLUMN_RANKING_TITLE + " INT)";

    public AnimeDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.i("sqlite", SQL_CREATE_ENTRIES);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertAnime(SQLiteDatabase db, Anime a){
        //Check the bd is open
        if (db.isOpen()){
            //Creation of the register for insert object with the content values
            ContentValues values = new ContentValues();

            //Insert the incidence getting all values
            values.put(AnimeEntry.COLUMN_NAME_TITLE, a.getName());
            values.put(AnimeEntry.COLUMN_GENRE_TITLE, a.getGenre());
            values.put(AnimeEntry.COLUMN_RANKING_TITLE, a.getRanking());

            db.insert(AnimeEntry.TABLE_NAME, null, values);
        }else{
            Log.i("sql","Database is closed");
        }
    }

    //This function selects all the data from the anime table and creates a new anime object with the values
    public ArrayList<Anime> getAllData(SQLiteDatabase db){
        ArrayList<Anime> arrayAnime = new ArrayList<Anime>();

        //cursor to be able to add all the anime from the db to the array list
        Cursor resultSet = db.rawQuery("Select * from anime",null);
        if (resultSet.moveToFirst()) {
            do {
                id = resultSet.getInt(0);
                String name = resultSet.getString(1);
                String genre = resultSet.getString(2);
                int ranking = resultSet.getInt(3);
                arrayAnime.add(new Anime(name, genre, ranking));
            } while (resultSet.moveToNext());
        }
        resultSet.close();

        return arrayAnime;
    }

    //This function deletes all data from an anime table
    public void deleteAllData(SQLiteDatabase db) {
        db.execSQL("delete from "+ AnimeEntry.TABLE_NAME);
    }

    public void updateData(SQLiteDatabase db, String newAnimeTitle, String newGenre, String newRanking) {
        Cursor resultSet = db.rawQuery("Select * from anime",null);
        if (resultSet.moveToFirst()) {
            do {
                id = resultSet.getInt(0);
            } while (resultSet.moveToNext());
        }
        resultSet.close();
        ContentValues values =  new ContentValues();
        values.put(AnimeEntry.COLUMN_NAME_TITLE, newAnimeTitle);
        values.put(AnimeEntry.COLUMN_GENRE_TITLE, newGenre);
        values.put(AnimeEntry.COLUMN_RANKING_TITLE, newRanking);

        db.update(AnimeEntry.TABLE_NAME, values, "ID=" + id, null);
    }
}
