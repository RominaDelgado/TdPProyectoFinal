package com.example.MovieNote;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class Adaptador<AnotacionSimple> extends ArrayAdapter<AnotacionSimple>
{
    private ArrayList<AnotacionSimple> lista;
    private AnotacionSimple anotacionSimple;

    public Adaptador(Context context, int resource, ArrayList<AnotacionSimple> objects) {
        super(context, resource, objects);
        lista=objects;
    }




}
