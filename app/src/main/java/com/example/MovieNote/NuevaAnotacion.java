package com.example.MovieNote;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.content.Intent;

import com.example.MovieNote.Entidades.ConexionSQLiteHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;
import android.widget.EditText;



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

        FloatingActionButton floatingActionButton_guardar = findViewById(R.id.floatingActionButton_guardar);
        floatingActionButton_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    //AnotacionSimple anotacion = new AnotacionSimple(getText(R.id.editText_tituloPelicula).toString());
                    //logica.guardar(anotacion);

                    //floating action button
                    Snackbar.make(view, "", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    registrarAnotacion();

                    // Vinculo el botón con la actividad NuevaAnotacion
                    Intent miIntentButtonGuardar = new Intent(NuevaAnotacion.this, MainActivity.class);
                    startActivity(miIntentButtonGuardar);
                    finish();




            }
        });
    }

    private void registrarAnotacion()
    {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "db anotaciones",null, 1);
        SQLiteDatabase db=conn.getWritableDatabase();

    }

}
