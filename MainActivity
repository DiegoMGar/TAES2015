package com.example.bstats;

import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;


public class MainActivity extends ActionBarActivity {
    private DataBaseManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = new DataBaseManager(this);
        manager.openWritable(); //Lectura y escritura
        //usar manager.openReadable(); para abrir la DB en solo lectura

        Cursor cursor = manager.readAll("equipos","nombre,localidad");

    }
}
