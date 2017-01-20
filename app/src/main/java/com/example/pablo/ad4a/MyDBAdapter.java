package com.example.pablo.ad4a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Pablo on 16/01/2017.
 */

public class MyDBAdapter {
    private static final String DATABASE_NAME = "colegio.db";
    private static final String DATABASE_TABLE = "estudiantes";
    private static final String DATABASE_TABLEP = "profesores";
    private static final int DATABASE_VERSION = 1;

    private static final String ENOMBRE = "nombre";
    private static final String EEDAD = "edad";
    private static final String ECICLO = "ciclo";
    private static final String ECURSO = "curso";

    private static final String PNOMBRE = "nombre";
    private static final String PEDAD = "edad";
    private static final String PCICLO = "ciclo";
    private static final String PCURSO = "curso";
    private static final String DESPACHO = "despacho";


    private static final String DATABASE_CREATE = "CREATE TABLE "+DATABASE_TABLE+" (_id integer primary key autoincrement, nombre text, edad integer, curso integer, ciclo text);";
    private static final String DATABASE_DROP = "DROP TABLE IF EXISTS "+DATABASE_TABLE+";";
    private static final String DATABASE_CREATEP = "CREATE TABLE "+DATABASE_TABLEP+" (_id integer primary key autoincrement, nombre text, edad integer, curso integer, ciclo text, despacho text);";
    private static final String DATABASE_DROPP = "DROP TABLE IF EXISTS "+DATABASE_TABLEP+";";

    private final Context context;
    private MyDbHelper dbHelper;
    private SQLiteDatabase db;

    public MyDBAdapter (Context c){
        context = c;
        dbHelper = new MyDbHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        open();
    }
    public void open(){

        try{
            db = dbHelper.getWritableDatabase();
        }catch(SQLiteException e){
            db = dbHelper.getReadableDatabase();
        }

    }
    public void insertarEstudiante(String n, int e, int c , String cic ){
        ContentValues newValues = new ContentValues();
        newValues.put(ENOMBRE,n);
        newValues.put(EEDAD,e);
        newValues.put(ECURSO,c);
        newValues.put(ECICLO,cic);
        db.insert(DATABASE_TABLE,null,newValues);
        Toast toast = Toast.makeText(MyDBAdapter.this.context, "Estudiante insertado.",
                Toast.LENGTH_SHORT);
        toast.show();

    }
    public void insertarProfesores(String n, int e, int c , String cic, String d) {
        ContentValues newValues = new ContentValues();
        newValues.put(PNOMBRE, n);
        newValues.put(PEDAD, e);
        newValues.put(PCURSO, c);
        newValues.put(PCICLO, cic);
        newValues.put(DESPACHO, d);
        db.insert(DATABASE_TABLEP, null, newValues);
        Toast toast = Toast.makeText(MyDBAdapter.this.context, "Profesor insertado.",
                Toast.LENGTH_SHORT);
        toast.show();
    }
    public boolean eliminarDatabase(){
        return context.deleteDatabase(DATABASE_NAME);
    }
    public boolean eliminarProfesor(int id)
    {
        return db.delete(DATABASE_TABLEP, "_id =" + id, null) > 0;
    }
    public boolean eliminarEstudiante(int id)
    {
        return db.delete(DATABASE_TABLE, "_id =" + id, null) > 0;
    }
    public ArrayList<String> recuperarEstudiantesCyC(String ciclo, int curso){
        ArrayList<String> estudiantes = new ArrayList<String>();
        //Recuperamos en un cursor la consulta realizada
        Cursor cursor = db.query(DATABASE_TABLE,null,null,null,null,null,null);
        //Recorremos el cursor
        if (cursor != null && cursor.moveToFirst()){
            do{
                if ((Integer.parseInt(cursor.getString(3))==curso)&&(cursor.getString(4).equals(ciclo))) {
                    estudiantes.add("Id: " + cursor.getString(0) + " Nombre:" + cursor.getString(1));
                }
            }while (cursor.moveToNext());
        }
        return estudiantes;
    }
    public ArrayList<String> recuperarProfesoresCyC(String ciclo, int curso){
        ArrayList<String> profesores = new ArrayList<String>();
        //Recuperamos en un cursor la consulta realizada
        Cursor cursor = db.query(DATABASE_TABLEP,null,null,null,null,null,null);
        //Recorremos el cursor
        if (cursor != null && cursor.moveToFirst()){
            do{
                if ((Integer.parseInt(cursor.getString(3))==curso)&&(cursor.getString(4).equals(ciclo)))
                {
                    profesores.add("Id: " + cursor.getString(0) + " Nombre:" + cursor.getString(1));
                }
            }while (cursor.moveToNext());
        }
        return profesores;
    }
    public ArrayList<String> recuperarEstudiantes(){
        ArrayList<String> estudiantes = new ArrayList<String>();
        //Recuperamos en un cursor la consulta realizada
        Cursor cursor = db.query(DATABASE_TABLE,null,null,null,null,null,null);
        //Recorremos el cursor
        if (cursor != null && cursor.moveToFirst()){
            do{
                    estudiantes.add("Id: " + cursor.getString(0) + " Nombre:" + cursor.getString(1));
            }while (cursor.moveToNext());
        }
        return estudiantes;
    }
    public ArrayList<String> recuperarProfesores(){
        ArrayList<String> profesores = new ArrayList<String>();
        //Recuperamos en un cursor la consulta realizada
        Cursor cursor = db.query(DATABASE_TABLEP,null,null,null,null,null,null);
        //Recorremos el cursor
        if (cursor != null && cursor.moveToFirst()){
            do{
                profesores.add("Id: " + cursor.getString(0) + " Nombre:" + cursor.getString(1));

            }while (cursor.moveToNext());
        }
        return profesores;
    }


    private static class MyDbHelper extends SQLiteOpenHelper {

        public MyDbHelper (Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
            super(context,name,factory,version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
            db.execSQL(DATABASE_CREATEP);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DATABASE_DROP);
            db.execSQL(DATABASE_DROPP);
            onCreate(db);
        }

    }
}
