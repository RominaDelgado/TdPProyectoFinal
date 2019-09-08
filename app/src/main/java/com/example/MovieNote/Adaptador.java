package com.example.MovieNote;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.LinearLayout;
import android.view.LayoutInflater;
import android.widget.TextView;


import android.graphics.Typeface;
import android.util.TypedValue;

import java.util.ArrayList;

public class Adaptador<AnotacionSimple> extends ArrayAdapter<AnotacionSimple>
{
    private ArrayList<AnotacionSimple> lista;
    private AnotacionSimple anotacion;

    int resource;
    private Context context;

    public Adaptador(Context context, int resource, ArrayList<AnotacionSimple> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        lista = objects;
    }

    @Override
    public View getView(final int position,final View convertView, ViewGroup parent) {

        final LinearLayout listItem;
        anotacion =  getItem(position);

        if (convertView == null)
        {
            listItem = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater inflater1;
            inflater1 = (LayoutInflater) getContext().getSystemService(inflater);
            inflater1.inflate(resource, listItem, true);

        }
        else
            {
            listItem = (LinearLayout) convertView;
        }


        //listItem = (LinearLayout) convertView;
        //TextView tituloAnotacion =  listItem.findViewById(R.id.listViewNotas);
        //tituloAnotacion.setTypeface(null, Typeface.BOLD);
       // tituloAnotacion.setTextSize(TypedValue.COMPLEX_UNIT_PX, 18);

        // tituloAnotacion.setText(anotacion.toString());
        return super.getView(position, convertView, parent);
    }
}
