package com.example.MovieNote;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;

import android.os.Bundle;

import android.content.Intent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import java.io.FileNotFoundException;
import java.io.IOException;


public class agregar_nota extends AppCompatActivity {
    Logica logica=Logica.getInstancia();


    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_nota);

        FloatingActionButton floatingActionButton_guardar = findViewById(R.id.floatingActionButton_guardar);
        floatingActionButton_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    AnotacionSimple anotacion = new AnotacionSimple(getText(R.id.editText_tituloPelicula).toString());
                    logica.guardar(anotacion);


                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    // Vinculo el botón con la actividad agregar_nota
                    Intent miIntentButtonGuardar = new Intent(agregar_nota.this, MainActivity.class);
                    startActivity(miIntentButtonGuardar);
                    finish();

            }
        });
    }



}
