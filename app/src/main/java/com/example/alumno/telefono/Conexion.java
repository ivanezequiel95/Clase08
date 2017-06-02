package com.example.alumno.telefono;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


/**
 * Created by alumno on 11/05/2017.
 */

public class Conexion implements Runnable{

    private Handler handler;
    private String tipo;
    private String strUrl;

    private byte[] byteArray;

    public Conexion (Handler handler, String strUrl, String tipo)
    {
        this.handler = handler;
        this.tipo = tipo;
        this.strUrl = strUrl;
    }

    @Override
    public void run() {

        try {
            this.byteArray = HttpConexion.getBytesDataByGET(strUrl);

            Message message = new Message();

            if (this.tipo.equals("Imagen"))
            {
                message.arg1 = 1;
                //message.obj = getBytesDataByGET("http://imagenpng.com/wp-content/uploads/2015/03/Imagenes-Mario-Bros-PNG-1.png");
                message.obj = this.byteArray;
            }
            if (this.tipo.equals("String"))
            {
                message.arg1 = 2;
                message.obj = XmlParser.obtenerPersonas(new String(this.byteArray));
            }

            this.handler.sendMessage(message);
        } catch (Exception e)
        {
            //Log.d("Null", "Error");
            e.printStackTrace();
        }


    }
}
