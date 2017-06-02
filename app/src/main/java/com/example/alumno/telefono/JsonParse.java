package com.example.alumno.telefono;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by alumno on 01/06/2017.
 */

public class JsonParse {
    public void jsonParse(String jsonFrutas)
    {
        try {
            JSONObject jsonObject = new JSONObject(jsonFrutas);
            JSONArray frutas = jsonObject.getJSONArray("frutas");
            for (int i=0 ; i < frutas.length() ; i++)
            {
                JSONObject fruta = frutas.getJSONObject(i);
                String nombre = fruta.getString("nombre_fruta");
                Integer cantidad = fruta.getInt("cantidad");
            }
        }
        catch (Exception e)
        {

        }
    }
}
