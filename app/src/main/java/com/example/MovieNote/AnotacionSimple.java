package com.example.MovieNote;

import java.io.Serializable;
import java.util.Date;
import java.text.SimpleDateFormat;

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
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM");
        String strDate= formatter.format(fecha);
       // return  ""+fecha.getDate()+"/"+(fecha.getMonth()+1)+"/"+fecha.getYear()+ "     " +titulo;
       return strDate+"-"+fecha.getYear()+ "    "+titulo;
    }

}

