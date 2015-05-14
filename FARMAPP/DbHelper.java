package com.example.diego.medicos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Diego on 12/05/2015.
 */
public class DbHelper extends SQLiteOpenHelper{
    private static final String DB_NAME = "farmapp.sqlite";
    private static final int DB_SCHEME_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_SCHEME_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DataBaseManager.CREATE_TABLE_AREA);
        db.execSQL(DataBaseManager.CREATE_TABLE_HOSPITAL);
        db.execSQL(DataBaseManager.CREATE_TABLE_MEDICO);
        db.execSQL(DataBaseManager.CREATE_TABLE_VISITA);
        db.execSQL(DataBaseManager.CREATE_TABLE_GASTO);
        db.execSQL(DataBaseManager.CREATE_TABLE_MATERIAL);
        db.execSQL(DataBaseManager.CREATE_TABLE_MATERIAL_MOSTRADO);
        db.execSQL(DataBaseManager.CREATE_TABLE_PRODUCTO);
        db.execSQL(DataBaseManager.CREATE_TABLE_PRODUCTO_MOSTRADO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
