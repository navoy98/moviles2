package com.example.momento1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText et_nombre, et_apellido;
    Button btn_guardar, btn_listar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_nombre = (EditText) findViewById(R.id.et_nombre);
        et_apellido = (EditText) findViewById(R.id.et_apellido);

        btn_guardar = (Button) findViewById((R.id.btn_guardar));
        btn_listar = (Button) findViewById((R.id.btn_listar));

        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardar(et_nombre.getText().toString(),et_apellido.getText().toString());
            }
        });

        btn_listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Listado.class));
            }
        });

    }

    private void guardar(String Nombre, String Apellido){
        baseHelper helper = new baseHelper(this,"Demo",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();
        try {
            ContentValues c = new ContentValues();
            c.put("Nombre",Nombre);
            c.put("Apellido",Apellido);
            db.insert("PERSONAS",null,c);
            db.close();
            Toast.makeText(this,"Registro Insertado",Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(this,"Error" + e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }
}