package com.example.MovieNote;

import java.io.*;
import java.util.LinkedList;
import java.util.List;


public final class Logica {
    private  static final Logica instancia=new Logica();
    private List<AnotacionSimple> lista;

    private Logica(){
        lista= new LinkedList<AnotacionSimple>();

    }

    public static  Logica getInstancia(){
        return instancia;
    }

    public void guardar(AnotacionSimple a)
    {
        try {
            lista.add(a);
            // Creo un objeto de tipo archivo para almacenar la lista:
            File fileName = new File("lista.data"); //FileNotFoundException
            // Abrir un manejador de archivo para solo escritura:
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(fileName));
            // Escribir la lista en el stream de objetos output (IOException):
            output.writeObject(lista);
            // Flush fuerza la escritura de cualquier contenido que haya quedado en el buffer del archivo.
            output.flush();
            // Cierro el archivo.
            output.close();

        }
        catch (FileNotFoundException e)
           {e.printStackTrace();}
        catch (IOException e)
           { e.printStackTrace(); }

    }

    public void cargar ()
    {
        try
        {
            File fileName = new File("lista.data"); // Nombre del archivo
            // Abrir un file handle para solo lectura:
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(fileName));
            // Leo la única lista que está en el archivo:
            // Puede producir IOException o ClassNotFoundException:
            lista = (List<AnotacionSimple>) input.readObject();
            input.close(); // Cierro el archivo
        }

        catch (IOException e)
        { e.printStackTrace(); }
        catch (ClassNotFoundException e)
        { e.printStackTrace();}
    }




}
