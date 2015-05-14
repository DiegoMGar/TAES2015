package com.example.diego.medicos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

/**
 * Created by Diego on 12/05/2015.
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
    public boolean openWritable(){
        db = helper.getWritableDatabase();
        if(db.isOpen()) {
            return true;
        }
        else{
            return false;
        }
    }
    public boolean openReadable(){
        db = helper.getReadableDatabase();
        if(db.isOpen()) {
            return true;
        }
        else{
            return false;
        }
    }
    public boolean close(){
       if(db.isOpen()) {
           db.close();
           return true;
       }
        return false;
    }
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

    public Cursor readAllAreas(String selects){
        if(selects == null)
            return null;
        String[] select = selects.split(",");
        if (select.length<2)
            return null;
        return db.query(TABLE_AREA,select,null,null,null,null,null);
    }
    public Cursor readAllHospitales(String selects){
        if(selects == null)
            return null;
        String[] select = selects.split(",");
        if (select.length<2)
            return null;
        return db.query(TABLE_HOSPITAL,select,null,null,null,null,null);
    }
    public Cursor readAllMedicos(String selects){
        if(selects == null)
            return null;
        String[] select = selects.split(",");
        if (select.length<2)
            return null;
        return db.query(TABLE_MEDICO,select,null,null,null,null,null);
    }
    public Cursor readAllVisitas(String selects){
        if(selects == null)
            return null;
        String[] select = selects.split(",");
        if (select.length<2)
            return null;
        return db.query(TABLE_VISITA,select,null,null,null,null,null);
    }
    public Cursor readAllGastos(String selects){
        if(selects == null)
            return null;
        String[] select = selects.split(",");
        if (select.length<2)
            return null;
        return db.query(TABLE_GASTO,select,null,null,null,null,null);
    }
    public Cursor readAllMateriales(String selects){
        if(selects == null)
            return null;
        String[] select = selects.split(",");
        if (select.length<2)
            return null;
        return db.query(TABLE_MATERIAL,select,null,null,null,null,null);
    }
    public Cursor readAllMaterialesMostrados(String selects){
        if(selects == null)
            return null;
        String[] select = selects.split(",");
        if (select.length<2)
            return null;
        return db.query(TABLE_MATERIAL_MOSTRADO,select,null,null,null,null,null);
    }
    public Cursor readAllProductos(String selects){
        if(selects == null)
            return null;
        String[] select = selects.split(",");
        if (select.length<2)
            return null;
        return db.query(TABLE_PRODUCTO,select,null,null,null,null,null);
    }
    public Cursor readAllProductosMostrados(String selects){
        if(selects == null)
            return null;
        String[] select = selects.split(",");
        if (select.length<2)
            return null;
        return db.query(TABLE_PRODUCTO_MOSTRADO,select,null,null,null,null,null);
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
    public Cursor readForOIDAreas(String selects,Integer id){
        if(selects == null || id == null)
            return null;
        String[] select = selects.split(",");
        if(select.length<2)
            return null;
        return db.query(TABLE_AREA,select,ALL_ID+" = "+id.toString(),null,null,null,null);
    }
    public Cursor readForOIDHospitales(String selects,Integer id){
        if(selects == null || id == null)
            return null;
        String[] select = selects.split(",");
        if(select.length<2)
            return null;
        return db.query(TABLE_HOSPITAL,select,ALL_ID+" = "+id.toString(),null,null,null,null);
    }
    public Cursor readForOIDMedicos(String selects,Integer id){
        if(selects == null || id == null)
            return null;
        String[] select = selects.split(",");
        if(select.length<2)
            return null;
        return db.query(TABLE_MEDICO,select,ALL_ID+" = "+id.toString(),null,null,null,null);
    }
    public Cursor readForOIDVisitas(String selects,Integer id){
        if(selects == null || id == null)
            return null;
        String[] select = selects.split(",");
        if(select.length<2)
            return null;
        return db.query(TABLE_VISITA,select,ALL_ID+" = "+id.toString(),null,null,null,null);
    }
    public Cursor readForOIDGastos(String selects,Integer id){
        if(selects == null || id == null)
            return null;
        String[] select = selects.split(",");
        if(select.length<2)
            return null;
        return db.query(TABLE_GASTO,select,ALL_ID+" = "+id.toString(),null,null,null,null);
    }
    public Cursor readForOIDMateriales(String selects,Integer id){
        if(selects == null || id == null)
            return null;
        String[] select = selects.split(",");
        if(select.length<2)
            return null;
        return db.query(TABLE_MATERIAL,select,ALL_ID+" = "+id.toString(),null,null,null,null);
    }
    public Cursor readForOIDMaterialesMostrados(String selects,Integer id){
        if(selects == null || id == null)
            return null;
        String[] select = selects.split(",");
        if(select.length<2)
            return null;
        return db.query(TABLE_MATERIAL_MOSTRADO,select,ALL_ID+" = "+id.toString(),null,null,null,null);
    }
    public Cursor readForOIDProductos(String selects,Integer id){
        if(selects == null || id == null)
            return null;
        String[] select = selects.split(",");
        if(select.length<2)
            return null;
        return db.query(TABLE_PRODUCTO,select,ALL_ID+" = "+id.toString(),null,null,null,null);
    }
    public Cursor readForOIDProductosMostrados(String selects,Integer id){
        if(selects == null || id == null)
            return null;
        String[] select = selects.split(",");
        if(select.length<2)
            return null;
        return db.query(TABLE_PRODUCTO_MOSTRADO,select,ALL_ID+" = "+id.toString(),null,null,null,null);
    }
    //READ FOR CUSTOM FILTER -----------------------------------------------------------------------
    public Cursor readForCustomFilter(String tabla,String selects,String column,String filter, String value){
        if(tabla == null || selects == null || id == null)
            return null;
        String[] select = selects.split(",");
        if(select.length<2)
            return null;
        return db.query(tabla,select,column+" "+filter+" "+value,null,null,null,null);
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
    public static final String TABLE_AREA = "areas";
    public static final String TABLE_HOSPITAL = "hospitales";
    public static final String TABLE_MEDICO = "medicos";
    public static final String TABLE_VISITA = "visitas";
    public static final String TABLE_GASTO = "gastos";
    public static final String TABLE_MATERIAL = "materiales";
    public static final String TABLE_MATERIAL_MOSTRADO = "materiales_mostrados";
    public static final String TABLE_PRODUCTO = "productos";
    public static final String TABLE_PRODUCTO_MOSTRADO = "productos_mostrados";

    public static final String ALL_ID = "_id";

    public static final String Area_nombre = "nombre";
    public static final String Area_descripcion = "descripcion";
    public static final String CREATE_TABLE_AREA = "create table " +TABLE_AREA+
            "("+ALL_ID+" integer primary key autoincrement," +
            Area_nombre+" text not null," +
            Area_descripcion+" text" +
            ");";

    public static final String Hospital_nombre = "nombre";
    public static final String Hospital_area = "area_FK";
    public static final String CREATE_TABLE_HOSPITAL = "create table " +TABLE_HOSPITAL+
            "("+ALL_ID+" integer primary key autoincrement," +
            Hospital_nombre+" text not null," +
            Hospital_area+" int not null," +
            "foreign key("+Hospital_area+") references "+TABLE_AREA+"("+ALL_ID+")" +
            ");";

    public static final String Medico_nombre = "nombre";
    public static final String Medico_colegiado = "num_colegiado";
    public static final String Medico_especialidad = "especialidad";
    public static final String Medico_hostpial = "hospital_FK";
    public static final String Medico_nivel = "nivel";
    public static final String Medico_horarios = "horarios";
    public static final String Medico_hobbies = "hobbies";
    public static final String Medico_cumpleanyos = "cumpleanyos";
    public static final String Medico_telefono = "telefono";
    public static final String Medico_email = "email";
    public static final String Medico_comentarios = "comentarios";
    public static final String CREATE_TABLE_MEDICO = "create table " +TABLE_MEDICO+
            "("+ALL_ID+" integer primary key autoincrement," +
            Medico_nombre+" text not null," +
            Medico_colegiado+" text not null," +
            Medico_especialidad+" text," +
            Medico_hostpial+" int not null," +
            Medico_nivel+" text," +
            Medico_horarios+" text," +
            Medico_hobbies+" text," +
            Medico_cumpleanyos+" datetime,"+ //ISO8601 ("YYYY-MM-DD HH:MM:SS.SSS")
            Medico_telefono+" text," +
            Medico_email+" text," +
            Medico_comentarios+" text," +
            "foreign key ("+Medico_hostpial+") references "+TABLE_HOSPITAL+"("+ALL_ID+")"+
            ");";

    public static final String Visita_medico = "medico_FK";
    public static final String Visita_fecha = "fecha";
    public static final String Visita_acompanyado = "acompanyado";
    public static final String Visita_comentarios = "comentarios";
    public static final String CREATE_TABLE_VISITA = "create table " +TABLE_VISITA+
            "("+ALL_ID+" integer primary key autoincrement ," +
            Visita_medico+" id not null," +
            Visita_fecha+" datetime not null," + //ISO8601 ("YYYY-MM-DD HH:MM:SS.SSS")
            Visita_acompanyado+" int default 0," +
            Visita_comentarios+" text," +
            "foreign key ("+Visita_medico+") references "+TABLE_MEDICO+"("+ALL_ID+")"+
            ");";

    public static final String Gasto_fecha = "fecha";
    public static final String Gasto_tipo = "tipo";
    public static final String Gasto_cantidad = "cantidad";
    public static final String CREATE_TABLE_GASTO = "create table " +TABLE_GASTO+
            "("+ALL_ID+" integer primary key autoincrement ," +
            Gasto_fecha+" datetime not null," + //ISO8601 ("YYYY-MM-DD HH:MM:SS.SSS")
            Gasto_tipo+" text not null," +
            Gasto_cantidad+" int default 0" +
            ");";

    public static final String Material_nombre = "nombre";
    public static final String Material_descripcion = "descripcion";
    public static final String Material_codigo = "codigo";
    public static final String CREATE_TABLE_MATERIAL = "create table " +TABLE_MATERIAL+
            "("+ALL_ID+" integer primary key autoincrement," +
            Material_nombre+" text not null," +
            Material_descripcion+" text," +
            Material_codigo+" text" +
            ");";

    public static final String Material_mostrado_material = "materialFK";
    public static final String Material_mostrado_visita = "visitaFK";
    public static final String CREATE_TABLE_MATERIAL_MOSTRADO = "create table " +TABLE_MATERIAL_MOSTRADO+
            "("+ALL_ID+" integer primary key autoincrement," +
            Material_mostrado_material+" int not null," +
            Material_mostrado_visita+" int not null," +
            "foreign key ("+Material_mostrado_material+") references "+TABLE_MATERIAL+"("+ALL_ID+"),"+
            "foreign key ("+Material_mostrado_visita+") references "+TABLE_VISITA+"("+ALL_ID+")"+
            ");";

    public static final String Producto_nombre = "nombre";
    public static final String Producto_descripcion = "descripcion";
    public static final String Producto_codigo = "codigo";
    public static final String CREATE_TABLE_PRODUCTO = "create table " +TABLE_PRODUCTO+
            "("+ALL_ID+" integer primary key autoincrement," +
            Producto_nombre+" text not null," +
            Producto_descripcion+" text," +
            Producto_codigo+" text" +
            ");";

    public static final String Producto_mostrado_producto = "productoFK";
    public static final String Producto_mostrado_visita = "visitaFK";
    public static final String CREATE_TABLE_PRODUCTO_MOSTRADO = "create table " +TABLE_PRODUCTO_MOSTRADO+
            "("+ALL_ID+" integer primary key autoincrement," +
            Producto_mostrado_producto+" int not null," +
            Producto_mostrado_visita+" int not null," +
            "foreign key ("+Producto_mostrado_producto+") references "+TABLE_PRODUCTO+"("+ALL_ID+"),"+
            "foreign key ("+Producto_mostrado_visita+") references "+TABLE_VISITA+"("+ALL_ID+")"+
            ");";
}
