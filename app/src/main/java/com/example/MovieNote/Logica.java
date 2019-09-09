package com.example.MovieNote;

import android.os.Environment;
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



import java.io.FileNotFoundException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;


public final class Logica  {
    private  static final Logica instancia=new Logica();
    private  ArrayList<AnotacionSimple> lista;
    private  File fileName=new File("lista.data");

    private Logica(){
        lista= new ArrayList<AnotacionSimple>();

    }

    public static  Logica getInstancia(){
        return instancia;
    }




    public  File getFileName()
    {
        return fileName;
    }

    public ArrayList<AnotacionSimple> getLista()
    {
        return lista;
    }

    public void añadir(AnotacionSimple e)
    {
        lista.add(e);
    }
    public void setLista(ArrayList<AnotacionSimple> l)
    {
        lista=l;
    }



    public void guardar()
    {

        ObjectOutputStream output;
        try{
            // Abrir un manejador de archivo para solo escritura:
            output = new ObjectOutputStream(new FileOutputStream(fileName));

            // Escribir la lista en el stream de objetos output (IOException):
            output.writeObject(lista);
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
            //File fileName = new File("lista.data"); // Nombre del archivo
            FileInputStream fileInput= new FileInputStream(fileName.getPath() + File.separator + "lista.data");
            // Abrir un file handle para solo lectura:
            input = new ObjectInputStream(fileInput);
            // Leo la única lista que está en el archivo:
            // Puede producir IOException o ClassNotFoundException:
            lista = (ArrayList<AnotacionSimple>) input.readObject();
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

    public void eliminar(AnotacionSimple a)
    {
        lista.remove(a);
        guardar();

    }


}
