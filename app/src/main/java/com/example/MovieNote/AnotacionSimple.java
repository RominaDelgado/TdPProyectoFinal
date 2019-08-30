package com.example.MovieNote;

import java.io.Serializable;

public class AnotacionSimple implements  Anotacion,Serializable {



    private String titulo;
    private String fecha;

    public Anotacion(String titulo, String fecha){
        this.titulo=titulo;
        this.fecha=fecha;
    }

    public void setTitulo(String titulo){
        this.titulo=titulo;
    }

    public void setFecha(String fecha){
        this.fecha=fecha;
    }

    public String getTitulo(){
        return titulo;
    }

    public String getFecha(){
        return fecha;
    }
}

}
