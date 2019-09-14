package com.example.MovieNote;

import android.content.Context;

import android.util.Log;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;


import java.io.FileNotFoundException;

import java.io.ObjectInputStream;



public final class Logica  {


    private static Logica instancia;
    private static Context context;
    private static String fileName;
    private List<AnotacionSimple> listaAnotacionSimple;

    private Logica(){
        listaAnotacionSimple= new ArrayList<AnotacionSimple>();

    }


    public static void setContext(Context ctx) {
        context = ctx;
        fileName = context.getFilesDir() + "/lista.data";
    }

    public static synchronized Logica getInstancia() {
        if(context == null) {
            return null;
        }
        if(instancia == null) {
            instancia = new Logica();
        }
        return instancia;
    }

    public List<AnotacionSimple> getLista()
    {
        cargar();
        return listaAnotacionSimple;
    }

    public void añadir(AnotacionSimple e)
    {
        Log.i("LISTA", "antes de agregar: "+ listaAnotacionSimple.size());
        Log.i("LISTA", "agrego: "+e.getTitulo());
        listaAnotacionSimple.add(e);
        Log.i("LISTA", "despues de agregar: "+listaAnotacionSimple.size());
    }

    public void guardar()
    {
        ObjectOutputStream output;
        try{
            Log.i("LISTA", "size: "+listaAnotacionSimple.size());
            // Abrir un manejador de archivo para solo escritura:
            output = new ObjectOutputStream(new FileOutputStream(fileName));

            // Escribir la lista en el stream de objetos output (IOException):
            output.writeObject(listaAnotacionSimple);
            // Flush fuerza la escritura de cualquier contenido que haya quedado en el buffer del archivo.
            output.flush();
            // Cierro el archivo.
            output.close();

        }
        catch (
                IOException e)
        { e.printStackTrace();}
    }




    @SuppressWarnings("unchecked")
    public void cargar()
    {
        ObjectInputStream input;
        try
        {
            FileInputStream fileInput= new FileInputStream(fileName);
            // Abrir un file handle para solo lectura:
            input = new ObjectInputStream(fileInput);
            // Leo la única lista que está en el archivo:
            // Puede producir IOException o ClassNotFoundException:
            listaAnotacionSimple = (ArrayList<AnotacionSimple>) input.readObject();
            Log.i("LISTA", "size: "+listaAnotacionSimple.size());
            for(AnotacionSimple anotacionSimple: listaAnotacionSimple) {
                Log.i("LISTA", "elem al cargar: "+anotacionSimple.getTitulo());
            }
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

    public void eliminar(AnotacionSimple anotacionSimple)
    {
        listaAnotacionSimple.remove(anotacionSimple);
        guardar();
    }

    public void ordenar_fecha()
    {
        Collections.sort(listaAnotacionSimple, new Comparator<AnotacionSimple>() {
            @Override
            public int compare(AnotacionSimple a1, AnotacionSimple a2) {
                return a1.getFecha().compareTo(a2.getFecha());
            }
        });

    }

    public void ordenar_titulo()
    {
        Collections.sort(listaAnotacionSimple, new Comparator<AnotacionSimple>() {
            @Override
            public int compare(AnotacionSimple a1, AnotacionSimple a2) {
                return a1.getTitulo().compareToIgnoreCase(a2.getTitulo());
            }
        });
    }


}
