package com.example.momento1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Listado extends AppCompatActivity {

    ListView listView;
    ArrayList<String> listado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        listView = (ListView) findViewById(R.id.listView);

        CargarListado();
    }

    private void CargarListado(){
        listado = ListaPersonas();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, listado);
        listView.setAdapter(adapter);
    }


    private ArrayList<String> ListaPersonas(){
        ArrayList<String> datos = new ArrayList<String>();
        baseHelper helper = new baseHelper(this,"Demo",null,1);
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "select Id, Nombre, Apellido from Personas";
        Cursor c = db.rawQuery(sql,null);
        if(c.moveToFirst()){
            do {
                String linea = c.getInt(0)+" "+c.getString(1)+" "+c.getString(2);
                datos.add(linea);
            }while (c.moveToNext());
        }
        db.close();
        return datos;
    }

}