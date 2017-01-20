package com.example.pablo.ad4a;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Estudiantes extends AppCompatActivity {
    EditText nombre;
    EditText edad;
    EditText curso;
    EditText ciclo;
    Button btn;
    MyDBAdapter myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiantes);
        myDB = new MyDBAdapter(this);
        myDB.open();

        nombre = (EditText) findViewById(R.id.nombreE);
        edad = (EditText) findViewById(R.id.edadE);
        ciclo = (EditText) findViewById(R.id.cicloE);
        curso = (EditText) findViewById(R.id.cursoE);

        btn = (Button) findViewById(R.id.button5);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int edads = Integer.parseInt(edad.getText().toString());
                int cursos = Integer.parseInt(curso.getText().toString());
                myDB.insertarEstudiante(nombre.getText().toString(), edads, cursos, ciclo.getText().toString());
                Intent i = new Intent();
                setResult(RESULT_OK, i);
                finish();
            }
        });

    }
}
