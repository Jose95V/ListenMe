package com.example.josedanilo.listenme;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    String[] items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lvPlaylist);
        final ArrayList<File> canciones = EncontrarCanciones(Environment.getExternalStorageDirectory()); //hace referencia a la sdCard

        items = new String[canciones.size()];
        for(int i=0; i<canciones.size();) //extrae las canciones que se encuentran en el arreglo
        {

           items[i] = canciones.get(i).getName().toString().replace(".mp3","").replace(".vav","").toLowerCase(); //se obtiene el nombre de la cancion
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.canciones,R.id.textView,items);
        lv.setAdapter(adapter); // agregando el adaptador a laa lista
    }

    public ArrayList<File> EncontrarCanciones(File root) {

        ArrayList<File> canciones = new ArrayList<File>();
        File[] archivos = root.listFiles();
        for (File lista : archivos){

            if (lista.isDirectory() && !lista.isHidden())
            {
                canciones.addAll(EncontrarCanciones(lista));

            }
            else {

                if(lista.getName().endsWith(".mp3") || lista.getName().endsWith(".vav") )
                {

                    canciones.add(lista);
                }
            }
        }

        return canciones;
    }
}
