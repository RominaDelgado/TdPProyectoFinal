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


public final class Logica {
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

}
