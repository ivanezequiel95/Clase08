package com.example.alumno.telefono;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alumno on 01/06/2017.
 */

public class XmlParser {


    public static List<Persona> obtenerPersonas(String xml)
    {
        List<Persona> personaList = new ArrayList<Persona>();
        XmlPullParser xmlPullParser = Xml.newPullParser();
        try {
            xmlPullParser.setInput(new StringReader(xml));
            int event = xmlPullParser.getEventType();
            while (event != xmlPullParser.END_DOCUMENT)
            {

                if (event == xmlPullParser.START_TAG)
                {
                    if (xmlPullParser.getName().equals("Persona"))
                    {
                        Persona p = new Persona();
                        p.setNombre(xmlPullParser.getAttributeValue(null, "nombre"));
                        p.setEdad(Integer.parseInt(xmlPullParser.getAttributeValue(null, "edad")));

                        personaList.add(p);
                    }
                }
            }

        } catch (XmlPullParserException e) {
            Log.d("Error obtenerPersonas:", e.getMessage());
        }
        return personaList;
    }
}
