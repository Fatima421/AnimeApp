package com.example.SyedFatima_AnimeApp.DB;

import android.provider.BaseColumns;

//A class that containts the table_name, the id and the columns for an anime table in the database.
public class AnimeDBCommands {
    private AnimeDBCommands(){}
    public static class AnimeEntry implements BaseColumns {
        public static final String TABLE_NAME ="anime";
        public static final String ID = "id";
        public static final String COLUMN_NAME_TITLE = "name";
        public static final String COLUMN_GENRE_TITLE = "genre";
        public static final String COLUMN_RANKING_TITLE = "ranking";
    }
}
