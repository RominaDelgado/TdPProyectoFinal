package com.example.MovieNote;

import android.widget.Toast;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;


public final class Logica {
    private  static final Logica instancia=new Logica();
    private ArrayList<AnotacionSimple> lista;

    private Logica(){
        lista= new ArrayList<AnotacionSimple>();

    }

    public static  Logica getInstancia(){
        return instancia;
    }

    public void guardar(AnotacionSimple a)
    {
        try {
            lista.add(a);
            // Creo un objeto de tipo archivo para almacenar la lista:
            File fileName = new File("lista.txt"); //FileNotFoundException

             // Abrir un manejador de archivo para solo escritura:
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(fileName));

            // Escribir la lista en el stream de objetos output (IOException):
            output.writeObject(lista);
            // Flush fuerza la escritura de cualquier contenido que haya quedado en el buffer del archivo.
            output.flush();
            // Cierro el archivo.
            output.close();

        }
        catch (IOException e)
           { e.printStackTrace();}

    }

    public void cargar ()
    {
        try
        {
            File fileName = new File("lista.txt"); // Nombre del archivo
            // Abrir un file handle para solo lectura:
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(fileName));
            // Leo la única lista que está en el archivo:
            // Puede producir IOException o ClassNotFoundException:
            lista = (ArrayList<AnotacionSimple>) input.readObject();
            input.close(); // Cierro el archivo
        }

        catch (IOException e)
        { e.printStackTrace(); }
        catch (ClassNotFoundException e)
        { e.printStackTrace();}
    }

    public ArrayList<AnotacionSimple> getLista()
    {return lista;}

}
