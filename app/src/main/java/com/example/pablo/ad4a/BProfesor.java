package com.example.pablo.ad4a;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BProfesor extends AppCompatActivity {

    MyDBAdapter myDB;
    EditText etxt;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bprofesor);
        myDB = new MyDBAdapter(this);
        myDB.open();
        etxt=(EditText) findViewById(R.id.etxtBP);
        btn = (Button) findViewById(R.id.borrarP);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = Integer.parseInt(etxt.getText().toString());
                if (myDB.eliminarProfesor(id)){
                    Toast toast = Toast.makeText(getApplicationContext(), "Profesor eliminado.",
                            Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(), "No se ha podido eliminar.",
                            Toast.LENGTH_SHORT);
                    toast.show();
                }
                Intent i = new Intent();
                setResult(RESULT_OK, i);
                finish();
            }
        });
    }
}

