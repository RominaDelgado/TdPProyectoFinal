package com.example.MovieNote;

import android.content.Intent;
import android.os.Bundle;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import android.widget.ArrayAdapter;

public class MainActivity extends AppCompatActivity {

    Logica logica=Logica.getInstancia();
    ArrayList<AnotacionSimple> l;
    private ListView listaViewAnotaciones;
    //private Adaptador<AnotacionSimple> adapter;
private ArrayAdapter<AnotacionSimple> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        l=new ArrayList<AnotacionSimple>();

        listaViewAnotaciones=(ListView) findViewById(R.id.listViewNotas);

        logica.cargar();
        l=logica.getLista();
        adapter= new ArrayAdapter<AnotacionSimple>(this,android.R.layout.simple_list_item_1,l);


        adapter.notifyDataSetChanged();

        // Icono de action Bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);



        listaViewAnotaciones.setAdapter(adapter);

        //listaViewAnotaciones.setOnClickListener();


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,"", Snackbar.LENGTH_LONG)
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
                // Vinculo el
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




}
