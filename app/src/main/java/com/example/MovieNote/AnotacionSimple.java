package com.example.MovieNote;

import java.io.Serializable;
import java.util.Date;

public class AnotacionSimple implements  Serializable
{

    private String titulo;
    private Date fecha;

    public AnotacionSimple(String titulo, Date fecha)
    {
        this.titulo=titulo;
        this.fecha = fecha;
    }

    public void setTitulo(String titulo)
    {
        this.titulo=titulo;
    }

    public String getTitulo()
    {
        return titulo;
    }

    public Date getFecha()
    {
        return fecha;
    }

    public void setFecha(Date fecha)
    {
        this.fecha = fecha;
    }

    @Override
    public String toString()
    {
        return fecha.getDay()+"/"+fecha.getMonth()+"/"+fecha.getYear()+"     " + titulo;
    }

}

