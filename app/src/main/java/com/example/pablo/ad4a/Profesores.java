package com.example.pablo.ad4a;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Profesores extends AppCompatActivity {
    EditText nombre;
    EditText edad;
    EditText curso;
    EditText ciclo;
    EditText despacho;
    Button btn;
    MyDBAdapter myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesores);
        myDB = new MyDBAdapter(this);
        myDB.open();

        nombre = (EditText) findViewById(R.id.nombreP);
        edad = (EditText) findViewById(R.id.edadP);
        ciclo = (EditText) findViewById(R.id.cicloP);
        curso = (EditText) findViewById(R.id.cursoP);
        despacho = (EditText) findViewById(R.id.editText5);
        btn = (Button) findViewById(R.id.button3);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int edads = Integer.parseInt(edad.getText().toString());
                int cursos = Integer.parseInt(curso.getText().toString());
                myDB.insertarProfesores(nombre.getText().toString(), edads, cursos, ciclo.getText().toString(), despacho.getText().toString());
                Intent i = new Intent();
                setResult(RESULT_OK, i);
                finish();
            }
        });
    }
}