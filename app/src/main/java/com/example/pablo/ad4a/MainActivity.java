package com.example.pablo.ad4a;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button estudiantes;
    Button profesores;
    Button bprofesores;
    Button bestudiantes;
    Button buscar;
    Button b;
    MyDBAdapter myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        estudiantes = (Button) findViewById(R.id.button);
        profesores = (Button) findViewById(R.id.button2);
        bprofesores = (Button) findViewById(R.id.bP);
        bestudiantes = (Button) findViewById(R.id.bE);
        buscar = (Button) findViewById(R.id.buscar);
        b = (Button) findViewById(R.id.b);
        myDB = new MyDBAdapter(this);
        myDB.open();

        //Va al activity para insertar estudiantes
        estudiantes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Estudiantes.class);
                MainActivity.this.startActivityForResult(i, 1);
            }
        });
        //Va al activity para insertar profesores
        profesores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Profesores.class);
                MainActivity.this.startActivityForResult(i, 2);
            }
        });
        //Va al activity para borrar estudiantes
        bestudiantes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), BEstuante.class);
                MainActivity.this.startActivityForResult(i, 3);
            }
        });
        //Va al activity para borrar profesores
        bprofesores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), BProfesor.class);
                MainActivity.this.startActivityForResult(i, 4);
            }
        });
        //Borra la base de datos
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myDB.eliminarDatabase()) {
                    Toast.makeText(getApplicationContext(), "BD borrada", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), "No se ha podido borrar la BD", Toast.LENGTH_SHORT).show();
                }
            }
        });
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Buscar.class);
                MainActivity.this.startActivityForResult(i, 5);
            }
        });

    }
}
