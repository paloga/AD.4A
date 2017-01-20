package com.example.pablo.ad4a;

import android.content.Intent;
import android.graphics.Color;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

public class Buscar extends AppCompatActivity {
    RadioButton rbE;
    RadioButton rbP;
    MyDBAdapter myDB;
    Button volver;
    Button buscar;
    EditText ciclo;
    EditText curso;
    CheckBox buscarcyc;
    ArrayList listaEstudiantes;
    ArrayList listaProfesores;
    RecyclerView rv;
    LinearLayoutManager rvLM;
    AdaptadorE apdEstudiantes;
    AdaptadorP apdProfesores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);
        myDB = new MyDBAdapter(this);
        myDB.open();
        volver = (Button) findViewById(R.id.volver);
        rbE = (RadioButton) findViewById(R.id.radioE);
        rbP = (RadioButton) findViewById(R.id.radioP);
        buscar= (Button) findViewById(R.id.search);
        buscarcyc = (CheckBox) findViewById(R.id.checkBox);
        rv = (RecyclerView) findViewById(R.id.elMeuRecyclerView);
        rvLM = new LinearLayoutManager(this);
        rv.setLayoutManager(rvLM);
        ciclo = (EditText) findViewById(R.id.cicloET);
        curso = (EditText) findViewById(R.id.cursoET);

        //Controla el buscador
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (buscarcyc.isChecked()) {
                    if (rbE.isChecked()) {
                        listaEstudiantes = new ArrayList();
                        ArrayList<String> estudiantes = myDB.recuperarEstudiantesCyC(ciclo.getText().toString(), Integer.parseInt(curso.getText().toString().toString()));
                        if ((estudiantes.size() == 0) || (ciclo.getText().toString().equals("") || (curso.getText().toString().equals("")))) {
                            Toast toast = Toast.makeText(getApplicationContext(), "No hay estudiantes en esa busqueda o necesita rellenarn ciclo y curso.",
                                    Toast.LENGTH_SHORT);
                            toast.show();
                        } else {

                            for (int i = 0; i < estudiantes.size(); i++) {
                                listaEstudiantes.add(new EstudiantesC(estudiantes.get(i)));
                            }

                            apdEstudiantes = new AdaptadorE(listaEstudiantes);
                            rv.setAdapter(apdEstudiantes);
                        }
                    } else if (rbP.isChecked()) {
                        listaProfesores = new ArrayList();
                        ArrayList<String> profesores = myDB.recuperarProfesoresCyC(ciclo.getText().toString(), Integer.parseInt(curso.getText().toString().toString()));
                        if ((profesores.size() == 0) || (ciclo.getText().toString().equals("") || (curso.getText().toString().equals("")))) {
                            Toast toast = Toast.makeText(getApplicationContext(), "No hay profesores en esa busqueda o necesita rellenarn ciclo y curso.",
                                    Toast.LENGTH_SHORT);
                            toast.show();
                        } else {
                            for (int i = 0; i < profesores.size(); i++) {
                                listaProfesores.add(new ProfesoresC(profesores.get(i)));
                            }
                            apdProfesores = new AdaptadorP(listaProfesores);
                            rv.setAdapter(apdProfesores);
                        }
                    }
                }else{
                    if (rbE.isChecked()) {
                        listaEstudiantes = new ArrayList();
                        ArrayList<String> estudiantes = myDB.recuperarEstudiantes();
                        if ((estudiantes.size() == 0)) {
                            Toast toast = Toast.makeText(getApplicationContext(), "No hay estudiantes en esa busqueda o necesita rellenarn ciclo y curso.",
                                    Toast.LENGTH_SHORT);
                            toast.show();
                        } else {

                            for (int i = 0; i < estudiantes.size(); i++) {
                                listaEstudiantes.add(new EstudiantesC(estudiantes.get(i)));
                            }

                            apdEstudiantes = new AdaptadorE(listaEstudiantes);
                            rv.setAdapter(apdEstudiantes);
                        }
                    } else if (rbP.isChecked()) {
                        listaProfesores = new ArrayList();
                        ArrayList<String> profesores = myDB.recuperarProfesores();
                        if ((profesores.size() == 0)) {
                            Toast toast = Toast.makeText(getApplicationContext(), "No hay profesores en esa busqueda o necesita rellenarn ciclo y curso.",
                                    Toast.LENGTH_SHORT);
                            toast.show();
                        } else {
                            for (int i = 0; i < profesores.size(); i++) {
                                listaProfesores.add(new ProfesoresC(profesores.get(i)));
                            }
                            apdProfesores = new AdaptadorP(listaProfesores);
                            rv.setAdapter(apdProfesores);
                        }
                    }
                }

            }
        });
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                setResult(RESULT_OK, i);
                finish();
            }
        });


    }

}
