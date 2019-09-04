package com.example.MovieNote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.content.Intent;

import com.example.MovieNote.Entidades.ConexionSQLiteHelper;
import com.example.MovieNote.Utilidades.Utilidades;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class NuevaAnotacion extends AppCompatActivity
{
    //Logica logica=Logica.getInstancia();
    EditText campoTitulo;
    EditText campoFecha;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_anotacion);

        campoTitulo= (EditText) findViewById(R.id.campoTitulo);
        campoFecha=(EditText) findViewById(R.id.campoFecha);


        Button button_registrar =findViewById(R.id.button_registrar);
        button_registrar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
               if (   ((EditText) findViewById(R.id.campoTitulo)).getText().toString() ==""
                        &&
                       ((EditText) findViewById(R.id.campoFecha)).getText().toString()==""
               )
                    Toast.makeText(getApplicationContext(),"No completo los campos correspondientes.",Toast.LENGTH_SHORT).show();
                else
                    registrarAnotacion();
            }
        });

    }

    private void registrarAnotacion()
    {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "db anotaciones",null, 1);
        SQLiteDatabase db=conn.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_TITULO,campoTitulo.getText().toString());
        values.put(Utilidades.CAMPO_FECHA,campoFecha.getText().toString());

    }

}
