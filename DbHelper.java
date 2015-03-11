package com.example.bstats;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Diego Maroto Garcia
 * @version 11.03.2015
 */
public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "bstats.sqlite";
    private static final int DB_SCHEME_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_SCHEME_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DataBaseManager.CREATE_TABLE_TEAM);
        db.execSQL(DataBaseManager.CREATE_TABLE_PLAYERS);
        db.execSQL(DataBaseManager.CREATE_TABLE_GAMES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
