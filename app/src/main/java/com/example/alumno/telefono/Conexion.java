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



/**
 * Created by alumno on 11/05/2017.
 */

public class Conexion implements Runnable{

    private Handler handler;

    public Conexion (Handler handler)
    {
        this.handler = handler;
    }



    public byte[] getBytesDataByGET(String strUrl) throws IOException {
        URL url = new URL(strUrl);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        urlConnection.setRequestMethod("GET");
        urlConnection.connect();

        int response = urlConnection.getResponseCode();
        Log.d("http", "Response code: " + response);

        if (response == 200)
        {
            InputStream inputStream = urlConnection.getInputStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int lenght = 0;

            while ((lenght = inputStream.read()) != -1)
            {
                byteArrayOutputStream.write(buffer, 0, lenght);
            }

            inputStream.close();
            return byteArrayOutputStream.toByteArray();
        }
        else
            throw new IOException();
    }

    @Override
    public void run() {

        try {

            Message message = new Message();

            message.arg1 = 1;
            message.obj = getBytesDataByGET("http://imagenpng.com/wp-content/uploads/2015/03/Imagenes-Mario-Bros-PNG-1.png");

            this.handler.sendMessage(message);
        } catch (IOException e) {
            Log.d("Exception Msg", e.getMessage());
        }


    }
}
