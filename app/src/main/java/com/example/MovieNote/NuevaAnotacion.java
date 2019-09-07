package com.example.MovieNote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.content.Intent;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class NuevaAnotacion extends AppCompatActivity
{
    Logica logica=Logica.getInstancia();
    EditText campoTitulo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_anotacion);

        campoTitulo= (EditText) findViewById(R.id.campoTitulo);




        Button button_registrar =findViewById(R.id.button_guardar);
        button_registrar.setOnClickListener(new View.OnClickListener()
        {
            // Al hacer click en el botón Guardar:
            // -Si los campos estan vacios NO guardara nada y mostrara mensaje.
            // -Si los campos estan completos, se registrara la anotación y se volvera a
            //   pantalla principal donde esta la lista de anotaciones.
            @Override
            public void onClick(View view) {

                if(   campoTitulo.getText().toString().isEmpty())
                {

                    Toast.makeText(getApplicationContext(),"No completo los campos correspondientes.",Toast.LENGTH_SHORT).show();
                }
                else
                    {
                        AnotacionSimple anotacion =new AnotacionSimple(campoTitulo.getText().toString());
                        logica.guardar(anotacion);
                        Toast.makeText(getApplicationContext(),"Se guardo correctamente.",Toast.LENGTH_SHORT).show();



                        // Vinculo el botón con la actividad NuevaAnotacion
                        Intent miIntentVolverPrincipal = new Intent(NuevaAnotacion.this, MainActivity.class);
                        startActivity(miIntentVolverPrincipal);
                        finish();
                    }
            }
        });

    }


    @Override
    public void onBackPressed(){
        super.onBackPressed();
          Intent miIntentAgregarNota = new Intent(NuevaAnotacion.this, MainActivity.class);
        startActivity(miIntentAgregarNota);

        finish();
    }

}
