package com.example.MovieNote.Entidades;
import java.util.Date;


public class Anotacion
{
    private String titulo;
    private String fecha;
    //private Date fecha;

    //public Anotacion(String titulo, Date fecha)
    public Anotacion(String titulo)
    {
        this.titulo = titulo;
     //   this.fecha = fecha;
    }

    public String getTitulo()
    {
        return titulo;
    }

    public void setTitulo(String titulo)
    {
        this.titulo = titulo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }


    /*
    public Date getFecha()
    {
        return fecha;
    }

    public void setFecha(Date fecha)
    {
        this.fecha = fecha;
    }
*/
}
