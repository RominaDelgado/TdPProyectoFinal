package com.example.MovieNote;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import android.widget.ArrayAdapter;
import android.view.ViewGroup;

import android.content.DialogInterface;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

public class MainActivity extends AppCompatActivity {


    private Logica logica;
    private ListView listaViewAnotaciones;
    private ArrayAdapter<AnotacionSimple> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Importante
        Logica.setContext(this);
        logica = Logica.getInstancia();
        List<AnotacionSimple> listaAnotacionSimple = logica.getLista();

        adapter = new ArrayAdapter<AnotacionSimple>(this, android.R.layout.simple_list_item_1, listaAnotacionSimple);
        adapter.notifyDataSetChanged();

        listaViewAnotaciones = (ListView) findViewById(R.id.listaNotasPeliculas);

        listaViewAnotaciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final AnotacionSimple anotacionSimple= logica.getLista().get(i);

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                                                {
                                                                    logica.eliminar(anotacionSimple);
                                                                    logica.cargar();
                                                                     adapter.notifyDataSetChanged();
                                                                    Toast.makeText(MainActivity.this,"Nota eliminada exitosamente",Toast.LENGTH_SHORT).show();

                                                                     break;
                                                                }
                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("¿Quiere eliminar la nota?").setPositiveButton("SI", dialogClickListener).setNegativeButton("NO", dialogClickListener).show();




            }
        });

        listaViewAnotaciones.setAdapter(adapter);

        // Icono de action Bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                // Vinculo el botón con la actividad NuevaAnotacion
                Intent miIntentAgregarNota = new Intent(MainActivity.this, NuevaAnotacion.class);
                startActivity(miIntentAgregarNota);
                finish();
            }
        });
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
        switch (item.getItemId()){
            case R.id.action_about:

                Intent miIntentAbout = new Intent(MainActivity.this,Informacion.class);
                startActivity(miIntentAbout);
                return true;
            case R.id.action_salir:
                finish();
                return true;
            default:
                return false;
        }
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        closeOptionsMenu();
        closeContextMenu();
    }


}
