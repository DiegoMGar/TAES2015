package com.example.bstats;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

/**
 * Created by Diego
 */
public class DataBaseManager {
    private DbHelper helper;
    private SQLiteDatabase db;
    private Context context;
    /* CONSTRUCTOR Y CONEXION A LA BASE DE DATOS **************************************************/
    public DataBaseManager(Context context){
        this.context=context;
        helper = new DbHelper(context);
    }
    public void openWritable(){
        db = helper.getWritableDatabase();
        if(db.isOpen()) {
            imprimeToast("Base de datos de escritura: abierta.");
        }
        else{
            imprimeToast("Base de datos ya abierta, petición openW duplicada.");
        }
    }
    public void openReadable(){
        db = helper.getReadableDatabase();
        if(db.isOpen()) {
            imprimeToast("Base de datos de lectura: abierta.");
        }
        else{
            imprimeToast("Base de datos ya abierta, petición openR duplicada.");
        }
    }
    public void close(){ if(db.isOpen()) db.close();}
    /* FUNCIONES CRUD, INSERTAR,LEER,MODIFICAR Y ELIMINAR EN DB ***********************************/

    // READ ALL ------------------------------------------------------------------------------------
    public Cursor readAll(String tabla,String selects){
        if(tabla == null || selects == null)
            return null;
        String[] select = selects.split(",");
        if (select.length<2)
            return null;
        return db.query(tabla,select,null,null,null,null,null);
    }
    public Cursor readAllTeams(String selects){
        if(selects == null)
            return null;
        String[] select = selects.split(",");
        if (select.length<2)
            return null;
        return db.query(TABLE_TEAM,select,null,null,null,null,null);
    }
    public Cursor readAllPlayers(String selects){
        if(selects == null)
            return null;
        String[] select = selects.split(",");
        if (select.length<2)
            return null;
        return db.query(TABLE_PLAYERS,select,null,null,null,null,null);
    }
    public Cursor readAllGames(String selects){
        if(selects == null)
            return null;
        String[] select = selects.split(",");
        if (select.length<2)
            return null;
        return db.query(TABLE_GAMES,select,null,null,null,null,null);
    }
    //READ FOR OID ---------------------------------------------------------------------------------
    public Cursor readForOID(String tabla,String selects,Integer id){
        if(tabla == null || selects == null || id == null)
            return null;
        String[] select = selects.split(",");
        if(select.length<2)
            return null;
        return db.query(tabla,select,ALL_ID+" = "+id.toString(),null,null,null,null);
    }
    public Cursor readForOIDTeam(String selects,Integer id){
        if(selects == null || id == null)
            return null;
        String[] select = selects.split(",");
        if(select.length<2)
            return null;
        return db.query(TABLE_TEAM,select,ALL_ID+" = "+id.toString(),null,null,null,null);
    }
    public Cursor readForOIDPlayer(String selects,Integer id){
        if(selects == null || id == null)
            return null;
        String[] select = selects.split(",");
        if(select.length<2)
            return null;
        return db.query(TABLE_PLAYERS,select,ALL_ID+" = "+id.toString(),null,null,null,null);
    }
    public Cursor readForOIDGame(String selects,Integer id){
        if(selects == null || id == null)
            return null;
        String[] select = selects.split(",");
        if(select.length<2)
            return null;
        return db.query(TABLE_GAMES,select,ALL_ID+" = "+id.toString(),null,null,null,null);
    }
    // INSERT --------------------------------------------------------------------------------------
    public boolean insert(String tabla,String mapaTextual) {
        if (mapaTextual == null || tabla == null)
            return false;
        if (mapaTextual.length() == 0)
            return false;
        long resultado = db.insert(tabla, null, generateContentValues(mapaTextual));
        return resultado > -1;
    }
    public boolean insertTeam(String mapaTextual) {
        if (mapaTextual == null)
            return false;
        if (mapaTextual.length() == 0)
            return false;
        long resultado = db.insert(TABLE_TEAM, null, generateContentValues(mapaTextual));
        return resultado > -1;
    }
    public boolean insertPlayer(String mapaTextual) {
        if (mapaTextual == null)
            return false;
        if (mapaTextual.length() == 0)
            return false;
        long resultado = db.insert(TABLE_PLAYERS, null, generateContentValues(mapaTextual));
        return resultado > -1;
    }
    public boolean insertGame(String mapaTextual) {
        if (mapaTextual == null)
            return false;
        if (mapaTextual.length() == 0)
            return false;
        long resultado = db.insert(TABLE_GAMES, null, generateContentValues(mapaTextual));
        return resultado > -1;
    }
    // UPDATE FOR OID-------------------------------------------------------------------------------
    public boolean updateForOID(String tabla,String mapaTextual,Integer id){
        if(tabla == null || mapaTextual == null || id == null)
            return false;
        int resultado = db.update(tabla,generateContentValues(mapaTextual),
                ALL_ID+" = "+id.toString(),null);
        return resultado>0; // Si zero, no ha afectado a ninguna columna
    }
    public boolean updateForOIDTeam(String mapaTextual,Integer id){
        if(mapaTextual == null || id == null)
            return false;
        int resultado = db.update(TABLE_TEAM,generateContentValues(mapaTextual),
                ALL_ID+" = "+id.toString(),null);
        return resultado>0; // Si zero, no ha afectado a ninguna columna
    }
    public boolean updateForOIDPlayer(String mapaTextual,Integer id){
        if(mapaTextual == null || id == null)
            return false;
        int resultado = db.update(TABLE_PLAYERS,generateContentValues(mapaTextual),
                ALL_ID+" = "+id.toString(),null);
        return resultado>0; // Si zero, no ha afectado a ninguna columna
    }
    public boolean updateForOIDGame(String mapaTextual,Integer id){
        if(mapaTextual == null || id == null)
            return false;
        int resultado = db.update(TABLE_GAMES,generateContentValues(mapaTextual),
                ALL_ID+" = "+id.toString(),null);
        return resultado>0; // Si zero, no ha afectado a ninguna columna
    }
    // UPDATE ALL ----------------------------------------------------------------------------------
    public boolean updateALL(String tabla,String mapaTextual){
        if(tabla == null || mapaTextual == null)
            return false;
        int resultado = db.update(tabla,generateContentValues(mapaTextual),null,null);
        return resultado>0; // Si zero, no ha afectado a ninguna columna
    }
    public boolean updateALLTeams(String mapaTextual){
        if(mapaTextual == null)
            return false;
        int resultado = db.update(TABLE_TEAM,generateContentValues(mapaTextual),null,null);
        return resultado>0; // Si zero, no ha afectado a ninguna columna
    }
    public boolean updateALLPlayers(String mapaTextual){
        if(mapaTextual == null)
            return false;
        int resultado = db.update(TABLE_PLAYERS,generateContentValues(mapaTextual),null,null);
        return resultado>0; // Si zero, no ha afectado a ninguna columna
    }
    public boolean updateALLGames(String mapaTextual){
        if(mapaTextual == null)
            return false;
        int resultado = db.update(TABLE_GAMES,generateContentValues(mapaTextual),null,null);
        return resultado>0; // Si zero, no ha afectado a ninguna columna
    }
    // DELETE FOR OID ------------------------------------------------------------------------------
    public boolean deleteForOID(String tabla,Integer id){
        int resultado = db.delete(tabla,ALL_ID+" = "+id.toString(),null);
        return resultado>0; // Si zero, no ha eliminado nada
    }
    public boolean deleteForOIDTeam(Integer id){
        int resultado = db.delete(TABLE_TEAM,ALL_ID+" = "+id.toString(),null);
        return resultado>0; // Si zero, no ha eliminado nada
    }
    public boolean deleteForOIDPlayer(Integer id){
        int resultado = db.delete(TABLE_PLAYERS,ALL_ID+" = "+id.toString(),null);
        return resultado>0; // Si zero, no ha eliminado nada
    }
    public boolean deleteForOIDGame(Integer id){
        int resultado = db.delete(TABLE_GAMES,ALL_ID+" = "+id.toString(),null);
        return resultado>0; // Si zero, no ha eliminado nada
    }
    // DELTE ALL -----------------------------------------------------------------------------------
    public boolean deleteALL(String tabla){
        int resultado = db.delete(tabla,"1",null);
        return resultado>0; // Si zero, no ha eliminado nada
    }
    public boolean deleteALLTeams( ){
        int resultado = db.delete(TABLE_TEAM,"1",null);
        return resultado>0; // Si zero, no ha eliminado nada
    }
    public boolean deleteALLPlayers( ){
        int resultado = db.delete(TABLE_PLAYERS,"1",null);
        return resultado>0; // Si zero, no ha eliminado nada
    }
    public boolean deleteALLGames( ){
        int resultado = db.delete(TABLE_GAMES,"1",null);
        return resultado>0; // Si zero, no ha eliminado nada
    }
    // DROP TABLE ----------------------------------------------------------------------------------
    public void dropTable(String tabla){
        db.execSQL("drop table "+tabla);
    }

    // GENERADORES DE STRING Y MAPAS, HELPERS ------------------------------------------------------
    private ContentValues generateContentValues(String mapaTextual){
        //"key1-value1,key2-value2,key3-value3..."
        if(mapaTextual==null || mapaTextual.length()==0)
            return null;
        ContentValues valores = new ContentValues();
        String[] filas = mapaTextual.split(",");
        for(String fila : filas){
            String[] llave_valor = fila.split("-");
            if(llave_valor.length!=2){
                imprimeToast("Error en el tratamiento textual: generateContentValues");
            }
            valores.put(llave_valor[0],llave_valor[1]);
        }
        return valores;
    }
    public String getStringSelects(String... valores){
        String selects = "";
        int contador=0;
        for(String s : valores){
            if(contador>0) {
                selects += ",";
            }
            else {
                contador++;
            }
            selects+=s;
        }
        return selects;
    }
    public String getStringMapa(String... valores){
        String selects = "";
        int contador=0;
        if(valores.length%2!=0)
            throw new RuntimeException("Parametros impares en getStringMapa");
        for(String s : valores){
            if(contador>0) {
                if(contador%2==0){
                    selects += ",";
                }else{
                    selects += "-";
                }
            }
            contador++;
            selects+=s;
        }
        return selects;
    }
    private void imprimeToast(String texto){
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context,texto,duration);
        toast.show();
    }

    /* PARAMETROS STATIC, NOMBRES DE TABLAS Y COLUMNAS DE TABLAS **********************************/
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

    /* FALTA VER DE SOLUCIONAR EL ANR TRABAJANDO EN SEGUNDO PLANO *********************************/

}
