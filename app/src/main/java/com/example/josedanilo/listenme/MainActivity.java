package com.example.josedanilo.listenme;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private int count, i, TRACK_Column, ID_Column, DATA_Column, YEAR_Column;
    private int DURATION_Column, ALBUM_ID_Column, ALBUM_Column, ARTIST_Column;
    private int[] idMusic;
    TextView title, artist;
    private String[] audioLista, artistLista, arrPath;
    ListView Lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      /*  if ((ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                || (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED))

        {
            ActivityCompat.requestPermissions
                    (MainActivity.this, new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    }, 1);
*/

        String[] information = {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.TRACK,
                MediaStore.Audio.Media.YEAR,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.ALBUM_ID,
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.ALBUM_KEY,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.TITLE_KEY,
                MediaStore.Audio.Media.ARTIST_ID,
                MediaStore.Audio.Media.ARTIST
        };

        final String orderBy = MediaStore.Audio.Media._ID;  //EXTERNAL_CONTENT_URI para el volumen de almacenamiento
        Cursor audioCursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                information, null, null, orderBy);

        Lista = (ListView) findViewById(R.id.ListView_Lista);

        audioCursor();

        AudioAdapter audioAdapter = new AudioAdapter();

        Lista.setAdapter(audioAdapter);

    }
/*}*/

    private void audioCursor(){

        String[] information= {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.TRACK,
                MediaStore.Audio.Media.YEAR,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.ALBUM_ID,
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.ALBUM_KEY,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.TITLE_KEY,
                MediaStore.Audio.Media.ARTIST_ID,
                MediaStore.Audio.Media.ARTIST
        };

        final String orderBy = MediaStore.Audio.Media._ID;  //EXTERNAL_CONTENT_URI para el volumen de almacenamiento
        Cursor audioCursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                information, null, null , orderBy);

        count = audioCursor.getCount();
        audioLista = new String[count];
        artistLista = new String[count];

        ID_Column = audioCursor.getColumnIndex(MediaStore.Audio.Media._ID);
        DATA_Column = audioCursor.getColumnIndex(MediaStore.Audio.Media.DATA);
        YEAR_Column = audioCursor.getColumnIndex(MediaStore.Audio.Media.YEAR);
        DURATION_Column = audioCursor.getColumnIndex(MediaStore.Audio.Media.DURATION);
        ALBUM_ID_Column = audioCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID);
        ALBUM_Column = audioCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM);
        TRACK_Column = audioCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
        ARTIST_Column = audioCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);

        while (audioCursor.moveToNext()){

            audioLista[i] = audioCursor.getString(TRACK_Column);
            artistLista[i] = audioCursor.getString(ARTIST_Column);

            i++;
        }

        audioCursor.close();

    }

    public class AudioAdapter extends BaseAdapter{

        private LayoutInflater inflater;
        public AudioAdapter(){

            inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return count;
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
           view = inflater.inflate(R.layout.rows,null);
            title = (TextView) view.findViewById(R.id.TextView_Tilte);
            artist = (TextView) view.findViewById(R.id.textView_Artis);

            title.setId(i);
            artist.setId(i);

            title.setText(audioLista[i]);
            artist.setText(artistLista[i]);
            return view;
        }
    }

    }

