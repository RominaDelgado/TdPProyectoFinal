package com.example.MovieNote;
import java.io.Serializable;
import java.util.List;

public class ListadoAnotaciones implements Serializable {

    private List<AnotacionSimple> lista;

    public ListadoAnotaciones (List<AnotacionSimple> lista)
    {
        this.lista=lista;
    }

    public List<AnotacionSimple> getLista(){return lista;}
    public void setLista (List<AnotacionSimple> lista) {this.lista=lista;}
    public int size(){return lista.size();}
}
