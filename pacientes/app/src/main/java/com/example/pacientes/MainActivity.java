package com.example.pacientes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button btnAdicionar;
    private ArrayList<Paciente> listaPacientes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAdicionar = (Button) findViewById(R.id.btnAdicionar);
        OnLoad();
        this.btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, formulario.class);
                Bundle args = new Bundle();
                args.putSerializable("lista", (Serializable) listaPacientes);
                intent.putExtra("BUNDLE", args);
                startActivity(intent);
            }
        });
    }

    private void OnLoad() {

        Intent i = getIntent();
        Bundle args = i.getBundleExtra("BUNDLE");
        if (args != null) {
            ArrayList<Paciente> listaPaciente = (ArrayList<Paciente>) args.getSerializable("lista");
            if (listaPaciente != null) {
                for (Paciente p : listaPaciente) {
                    listaPacientes.add(p);
                }
            }
        }
    }

}
