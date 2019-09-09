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

    private Logica logica = Logica.getInstancia();
    private ArrayList<AnotacionSimple> l;
    private ListView listaViewAnotaciones;
    //private Adaptador<AnotacionSimple> adapter;
    private ArrayAdapter<AnotacionSimple> adapter;


    ////////
    private String arch = "lista";
    private String carpeta = "/tdp.movieNote/";
    private String file_path;
    private File file;
    private String name = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        l = new ArrayList<AnotacionSimple>();
        l = logica.getLista();

        contenedorArchivo();

        adapter = new ArrayAdapter<AnotacionSimple>(this, android.R.layout.simple_list_item_1, l);
        adapter.notifyDataSetChanged();

        listaViewAnotaciones = (ListView) findViewById(R.id.listViewNotas);
        listaViewAnotaciones.setAdapter(adapter);

        listaViewAnotaciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AnotacionSimple a= logica.getLista().get(i);

            }
        });



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
/*
    @SuppressWarnings("unchecked")
    public void cargar()
    {
        File archivo=logica.getFileName();
        if(archivo==null)
            archivo = getExternalFilesDir(null);
        ObjectInputStream input;
        try
        {
            //File fileName = new File("lista.data"); // Nombre del archivo
            FileInputStream fileInput= new FileInputStream(archivo.getPath() + File.separator + "lista.data");
            // Abrir un file handle para solo lectura:
            input = new ObjectInputStream(fileInput);
            // Leo la única lista que está en el archivo:
            // Puede producir IOException o ClassNotFoundException:
            l = (ArrayList<AnotacionSimple>) input.readObject();
            logica.setLista(l);
            input.close(); // Cierro el archivo
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(java.lang.NullPointerException e){
            e.printStackTrace();
        }catch(java.io.IOException e){
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    **/


    @Override
    public void onBackPressed(){
        super.onBackPressed();
     closeOptionsMenu();
        closeContextMenu();
    }




    public void contenedorArchivo()
    {
        this.file_path=(Environment.getExternalStorageDirectory()+this.carpeta);
        File localFile= new File(this.file_path);
        if (!localFile.exists())
        {
            localFile.mkdirs();
        }
        this.name=(this.arch+".data");

        this.file= new File(localFile,this.name);
        try
        {
            this.file.createNewFile();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        if(file.exists())
        {
            logica.cargar();
        }
    }

}
