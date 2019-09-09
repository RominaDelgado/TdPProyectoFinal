package com.example.MovieNote;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.VoiceInteractor;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.content.Intent;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.io.File;
import java.util.Date;

public class NuevaAnotacion extends AppCompatActivity
{
    private Logica logica=Logica.getInstancia();

    private EditText campoTitulo;
    private EditText campoFecha;
    private File archivo;
    private Button botonFecha;

    private Date fecha;

    private  int dia,mes, anio;
    private static  DatePickerDialog.OnDateSetListener oyenteFecha;
    private static final int tipo_dialogo=0;

    CalendarView c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_anotacion);

        campoTitulo = (EditText) findViewById(R.id.campoTitulo);
        campoFecha = (EditText) findViewById(R.id.campoFecha);
        botonFecha = (Button) findViewById(R.id.button_fecha);

        final Calendar calendario = Calendar.getInstance();

        dia = calendario.get(Calendar.DAY_OF_MONTH);
        mes = calendario.get(Calendar.MONTH);
        anio = calendario.get(Calendar.YEAR);

        oyenteFecha = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                dia = i2;
                mes = i1 + 1;
                anio = i;
                fecha=new Date(anio,mes,dia);
                campoFecha.setText(dia + "/" + mes + "/"+anio);
            }
        };

        Button button_registrar =findViewById(R.id.button_guardar);
        button_registrar.setOnClickListener(new View.OnClickListener()
        {
            // Al hacer click en el botón Guardar:
            // -Si los campos estan vacios NO guardara nada y mostrara mensaje.
            // -Si los campos estan completos, se registrara la anotación y se volvera a
            //   pantalla principal donde esta la lista de anotaciones.
            @Override
            public void onClick(View view) {

                if(   (campoTitulo.getText().toString().isEmpty()) ||
                        (campoFecha.getText().toString().isEmpty())
                    )
                {

                    Toast.makeText(getApplicationContext(),"No completo los campos correspondientes.",Toast.LENGTH_SHORT).show();
                }
                else
                    {
                        String titulo=""+campoTitulo.getText();
                        AnotacionSimple anotacion =new AnotacionSimple(titulo,fecha);
                        logica.añadir(anotacion);

                        logica.guardar();
                        Toast.makeText(getApplicationContext(),"Se guardo correctamente.",Toast.LENGTH_SHORT).show();

                        campoFecha.setText("");

                        // Vinculo el botón con la actividad NuevaAnotacion
                        Intent miIntentVolverPrincipal = new Intent(NuevaAnotacion.this, MainActivity.class);
                        startActivity(miIntentVolverPrincipal);
                        finish();
                    }
            }
        });}


        @Override
        protected Dialog onCreateDialog(int i)
        {
            switch (i){
                case 0: return new DatePickerDialog(this,oyenteFecha,dia,mes,anio);
            }
            return null;
        }

        public void mostrarCalendario(View v)
        {
            showDialog(tipo_dialogo);
        }


/*
        public void guardar()
        {

            ArrayList<AnotacionSimple> l =logica.getLista();
             archivo= logica.getFileName();

            if(archivo==null)
                archivo = getExternalFilesDir(null);

            ObjectOutputStream output;
            try{
                // Abrir un manejador de archivo para solo escritura:
                output = new ObjectOutputStream(new FileOutputStream(archivo));

                // Escribir la lista en el stream de objetos output (IOException):
                output.writeObject(l);
                // Flush fuerza la escritura de cualquier contenido que haya quedado en el buffer del archivo.
                output.flush();
                // Cierro el archivo.
                output.close();

            }
            catch (
                    IOException e)
            { e.printStackTrace();}
        }

**/


    @Override
    public void onBackPressed(){
        super.onBackPressed();
          Intent miIntentAgregarNota = new Intent(NuevaAnotacion.this, MainActivity.class);
        startActivity(miIntentAgregarNota);

        finish();
    }

}
