package com.example.bstats;

import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.diego.taes_bstats.R;


public class MainActivity extends ActionBarActivity {
    private DataBaseManager manager;
    private ListView lista;
    private SimpleCursorAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = new DataBaseManager(this);
        manager.openWritable();
/*
        manager.insert(manager.TABLE_TEAM,"nombre-carolinas,categoria-una_cualquiera");
        manager.insert(manager.TABLE_TEAM,"nombre-caraperros,categoria-grrrGuau!");
        manager.insert(manager.TABLE_TEAM,
            manager.getStringMapa(manager.TT_nombre,"carolinas",manager.TT_categoria,"categoria Cualquiera"));
        manager.updateForOIDTeam("nombre-recien Cambiado",1);
        manager.updateALLTeams(manager.getStringMapa("nombre","Todos Editados"));
        manager.deleteALLTeams();
*/
        String[] from = new String[]{manager.TT_nombre,manager.TT_categoria};
        int[] to = new int[]{android.R.id.text1,android.R.id.text2};

        Cursor cursor = manager.readAll(manager.TABLE_TEAM,manager.getStringSelects(manager.ALL_ID,manager.TT_nombre,manager.TT_categoria));
        lista = (ListView) findViewById(R.id.listView);
        adaptador = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item,cursor,from,to,0);
        lista.setAdapter(adaptador);
        Log.i("Ejemplo de etiqueta","Ejemplo de mensaje en esa etiqueta para mostrar en el logcat");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
