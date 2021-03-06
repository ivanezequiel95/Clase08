package com.example.alumno.telefono;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.widget.ImageView;

import java.util.List;

public class Hola extends AppCompatActivity implements Handler.Callback{

    private ImageView img;
    private List<Persona> personas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hola);
//        ActionBar actionBar = this.getSupportActionBar();
//        actionBar.hide();

        Handler handler = new Handler(this);

        Thread thread1 = new Thread(new Conexion(handler, "http://192.168.2.117:8080/listaPersonas.xml", "String"));

        thread1.start();

        img = (ImageView) super.findViewById(R.id.imagen1);
    }

    @Override
    public boolean handleMessage(Message msg) {
        Log.d("Respuesta", "Llego respuesta de hilo");

        if (msg.arg1 == 1)
        {

            byte[] bytes = (byte[]) msg.obj;


            Log.d("Lenght", String.valueOf(bytes.length));
            try {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                img.setImageBitmap(bitmap);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if (msg.arg1 == 2)
        {
            personas = (List<Persona>) msg.obj;
            for (Persona p:personas)
            {
                Log.d("Persona 1: ", p.getNombre() );
                Log.d("Persona 1: ", Integer.toString(p.getEdad()) );
            }
        }

        return true;
    }
}
