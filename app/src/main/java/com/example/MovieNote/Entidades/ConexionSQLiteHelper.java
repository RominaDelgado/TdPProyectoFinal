package com.example.MovieNote.Entidades;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.MovieNote.Utilidades.Utilidades;

public class ConexionSQLiteHelper extends SQLiteOpenHelper
{
    //final String CREAR_TABLA_ANOTACION="CREATE TABLE anotaciones(titulo STRING, fecha DATE)";
   // final String CREAR_TABLA_ANOTACION="CREATE TABLE anotaciones(titulo STRING)";

    public ConexionSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(Utilidades.CREAR_TABLA_ANOTACION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva)
    {
        db.execSQL("DROP TABLE IF EXISTS anotaciones");
        onCreate(db);
    }
}
