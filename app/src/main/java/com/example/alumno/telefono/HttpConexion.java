package com.example.alumno.telefono;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by alumno on 01/06/2017.
 */

public class HttpConexion {


    public static byte[] getBytesDataByGET(String strUrl) throws IOException {
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
}
