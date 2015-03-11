package com.example.bstats;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * @author Diego Maroto Garcia
 * @version 11.03.2015
 */
public class DataBaseManager {
    private DbHelper helper;
    private SQLiteDatabase db;
    public DataBaseManager(Context context){
        helper = new DbHelper(context);
    }
    public void openWritable(){
        db = helper.getWritableDatabase();
    }
    public void openReadable(){
        db = helper.getReadableDatabase();
    }
    public static final String TABLE_TEAM = "equipos";
    public static final String TABLE_PLAYERS = "jugadores";
    public static final String TABLE_GAMES = "partidos";

    public static final String ALL_ID = "_id";

    public static final String TT_nombre = "nombre";
    public static final String TT_localidad = "localidad";
    public static final String TT_categoria = "categoria";
    public static final String TT_imagen = "imagen";

    public static final String TP_nombre = "nombre";
    public static final String TP_equipo = "id_equipo";

    public static final String TG_equipo = "id_equipo";
    public static final String TG_fecha = "fecha";
    public static final String TG_lugar = "lugar";

    public static final String CREATE_TABLE_TEAM = "create table " +TABLE_TEAM+
            "("+ALL_ID+" integer primary key autoincrement ," +
            TT_nombre+" text not null," +
            TT_localidad+" text," +
            TT_categoria+" text," +
            TT_imagen+" text" +
            ");";
    public static final String CREATE_TABLE_PLAYERS = "create table " +TABLE_PLAYERS+
            "("+ALL_ID+" integer primary key autoincrement ," +
            TP_nombre+" text not null," +
            TP_equipo+" int not null," +
            "foreign key("+TP_equipo+") references "+TABLE_TEAM+"("+ALL_ID+")" +
            ");";
    public static final String CREATE_TABLE_GAMES = "create table " +TABLE_GAMES+
            "("+ALL_ID+" integer primary key autoincrement ," +
            TG_equipo+" int not null," +
            TG_fecha+" datetime default current_timestamp,"+ //ISO8601 ("YYYY-MM-DD HH:MM:SS.SSS")
            TG_lugar+" text," +
            "foreign key("+TG_equipo+") references "+TABLE_TEAM+"("+ALL_ID+")" +
            ");";

    public Cursor readAll(String table,String selects){
        String[] select = selects.split(",");
        return db.query(table,select,null,null,null,null,null);
    }
    public Cursor readForOID(String table,String selects,Integer id){
        String[] select = selects.split(",");
        return db.query(table,select,ALL_ID+"=?",new String[]{id.toString()},null,null,null);
    }
    public boolean insert(String tabla,String mapaTextual) {
        if (mapaTextual.length() == 0)
            return false;
        long resultado = db.insert(tabla, null, generateContentValues(mapaTextual));
        return resultado > -1;
    }
    public boolean updateForOID(String tabla,String mapaTextual,Integer id){
        int resultado = db.update(tabla,generateContentValues(mapaTextual),ALL_ID+"=?",new String[]{id.toString()});
        return resultado>0; // Si zero, no ha afectado a ninguna columna
    }
    public boolean updateALL(String tabla,String mapaTextual){
        int resultado = db.update(tabla,generateContentValues(mapaTextual),null,null);
        return resultado>0; // Si zero, no ha afectado a ninguna columna
    }
    public boolean deleteForOID(String tabla,Integer id){
        int resultado = db.delete(tabla,ALL_ID+"=?",new String[]{id.toString()});
        return resultado>0; // Si zero, no ha eliminado nada
    }
    public boolean deleteALL(String tabla){
        int resultado = db.delete(tabla,"1",null);
        return resultado>0; // Si zero, no ha eliminado nada
    }
    private ContentValues generateContentValues(String mapaTextual){
        //"key1-value1,key2-value2,key3-value3..."
        if(mapaTextual==null || mapaTextual.length()==0)
            return null;
        ContentValues valores = new ContentValues();
        String[] filas = mapaTextual.split(",");
        for(String fila : filas){
            String[] llave_valor = fila.split("-");
            valores.put(llave_valor[0],llave_valor[1]);
        }
        return valores;
    }
    /* FALTA VER DE SOLUCIONAR EL ANR TRABAJANDO EN SEGUNDO PLANO */

}
