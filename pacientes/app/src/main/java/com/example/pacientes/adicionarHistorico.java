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
        final ArrayList<Paciente> listaPaciente = (ArrayList<Paciente>) args.getSerializable("lista");

        this.btnAdd = (Button) findViewById(R.id.btnAlterar);
        this.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Chamada da funcao para salvar os dados da consulta
                Historico his = salvarHistorico();
                if (his != null) {
                    //caso o objeto tenha sido salvo com sucesso
                    list = p.getHistorico();
                    //minha lista com os historicos do objeto do paciente e atrelada a uma lista interna da classe
                    if (list == null) {
                        //caso essa lista esteja vazia ele adiciona um objeto novo
                        p.setHistorico(new ArrayList<Historico>());
                    }
                    list = p.getHistorico();
                    //adiciono o historico a essa lista
                    list.add(his);
                   //entao vou atualizar a minha lista de pacientes com as informacoes atuais desse paciente
                    for (Paciente paciente : listaPaciente) {
                        if (p.getCPF().equals(paciente.getCPF())) {
                            //quando o paciente for localizado ele e removido da minha lista
                            paciente.setHistorico(list);
                        }
                    }
                    //informa que a registro foi feito com sucesso
                    Toast.makeText(adicionarHistorico.this, "Historico adicionado", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(adicionarHistorico.this, ClienteDetalhes.class);
                    Bundle args = new Bundle();
                    args.putSerializable("paciente", (Serializable) p);
                    args.putSerializable("lista", (Serializable) listaPaciente);
                    i.putExtra("BUNDLE", args);
                    //encaminho os objetos atualizados para a activity de cliente detalhe
                    startActivity(i);
                } else {
                    //mostra a falha ao adicionar o historico e mantem na mesma pagina
                    Toast.makeText(adicionarHistorico.this, "Falha ao Adicionar historico ao Paciente", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public Historico salvarHistorico() {
        //cria um objeto vazio
        Historico hist = new Historico();
        //valida que o campo de Detalhe esteja preenchido
        if (txtDetalhe.getText().toString().isEmpty() == false) {
            hist.setDetalhe(txtDetalhe.getText().toString());
            Calendar calendar = Calendar.getInstance();
            String dataAtual = DateFormat.getDateInstance().format(calendar.getTime());
            hist.setData(dataAtual);
            hist.setTitulo(spinner.getSelectedItem().toString());
            hist.setCpf(p.getCPF());
            //Retorna o objeto do tipo historico
            return hist;
        } else {
            return null;
        }
    }
}
