package com.example.MovieNote;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;

import android.content.Intent;


import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.io.File;
import java.util.Date;

public class NuevaAnotacion extends AppCompatActivity
{
    private Logica logica= Logica.getInstancia();

    private EditText campoTitulo;
    private EditText campoFecha;
    private File archivo;
    private Button botonFecha;

    private Date fecha=new Date();

    private  int dia,mes, anio;

    private static final int tipo_dialogo=0;
    private Calendar calendario;
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_anotacion);

        campoTitulo = (EditText) findViewById(R.id.campoTitulo);
        campoFecha = (EditText) findViewById(R.id.campoFecha);
        botonFecha = (Button) findViewById(R.id.button_fecha);


        botonFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendario = Calendar.getInstance();
                dia = calendario.get(Calendar.DAY_OF_MONTH);
                mes = calendario.get(Calendar.MONTH);
                anio = calendario.get(Calendar.YEAR);

                datePickerDialog=new DatePickerDialog(NuevaAnotacion.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        campoFecha.setText(i2 + "/" +( i1 +1)+ "/"+i);
                        dia = i2;
                        mes = i1;
                        anio = i;
                        fecha.setDate(dia);
                        fecha.setMonth(mes);
                        fecha.setYear(anio);
                    }
                },anio,mes,dia);

            datePickerDialog.show();
            }
        });

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
    public void onBackPressed(){
        super.onBackPressed();
        Intent miIntentAgregarNota = new Intent(NuevaAnotacion.this, MainActivity.class);
        startActivity(miIntentAgregarNota);

        finish();
    }
}
