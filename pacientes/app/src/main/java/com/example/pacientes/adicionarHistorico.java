package com.example.pacientes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class adicionarHistorico extends AppCompatActivity {
    private Button btnAdd;
    private Spinner spinner;
    private EditText txtDetalhe;
    private Paciente p;
    private ArrayList<Historico> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_historico);
        txtDetalhe = (EditText) findViewById(R.id.txtDetalhe);
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(adicionarHistorico.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.titulos));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        Intent i = getIntent();
        Bundle args = i.getBundleExtra("BUNDLE");
        p = (Paciente) args.getSerializable("paciente");

        this.btnAdd = (Button) findViewById(R.id.btnAlterar);
        this.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Historico his = salvarHistorico();
                if (his != null) {
                    list = p.getHistorico();
                    if(list == null){
                        p.setHistorico(new ArrayList<Historico>());
                    }
                    list = p.getHistorico();
                    list.add(his);
                    Toast.makeText(adicionarHistorico.this, "Historico adicionado", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(adicionarHistorico.this, ClienteDetalhes.class);
                    Bundle args = new Bundle();
                    args.putSerializable("paciente", (Serializable) p);
                    i.putExtra("BUNDLE", args);
                    startActivity(i);
                } else {
                    Toast.makeText(adicionarHistorico.this, "Falha ao Adicionar historico ao Paciente", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public Historico salvarHistorico() {
        Historico hist = new Historico();
        if (txtDetalhe.getText().toString().isEmpty() == false) {
            hist.setDetalhe(txtDetalhe.getText().toString());
            Calendar calendar = Calendar.getInstance();
            String dataAtual = DateFormat.getDateInstance().format(calendar.getTime());
            hist.setData(dataAtual);
            hist.setTitulo(spinner.getSelectedItem().toString());
            hist.setCpf(p.getCPF());
            return hist;
        } else {
            return null;
        }
    }
}
