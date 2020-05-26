package com.example.pacientes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button btnAdicionar;
    private Button btnPesquisar;
    private EditText textPesquisa;
    private ArrayList<Paciente> listaPacientes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAdicionar = (Button) findViewById(R.id.btnAdicionar);
        btnPesquisar = (Button) findViewById(R.id.btnPesquisar);
        textPesquisa = (EditText) findViewById(R.id.textPesquisa);
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
        this.btnPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!textPesquisa.getText().toString().isEmpty()) {
                    boolean validarExistencia = false;
                    for (Paciente p : listaPacientes) {
                        if (textPesquisa.getText().toString().equals(p.getCPF())) {
                            validarExistencia = true;
                        }
                    }
                    if (validarExistencia) {
                        for (Paciente p : listaPacientes) {
                            if (textPesquisa.getText().toString().equals(p.getCPF())) {
                                Intent i = new Intent(MainActivity.this, ClienteDetalhes.class);
                                Bundle args = new Bundle();
                                args.putSerializable("paciente", (Serializable) p);
                                args.putSerializable("lista", (Serializable) listaPacientes);
                                i.putExtra("BUNDLE", args);
                                startActivity(i);
                            }
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Nao localizado", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private void OnLoad() {

        Intent i = getIntent();
        Bundle args = i.getBundleExtra("BUNDLE");
        if (args != null) {
            listaPacientes = (ArrayList<Paciente>) args.getSerializable("lista");
        }
    }

}
