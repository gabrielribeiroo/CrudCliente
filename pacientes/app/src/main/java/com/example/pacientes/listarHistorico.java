package com.example.pacientes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class listarHistorico extends AppCompatActivity {
    private Paciente p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_historico);
        Intent i = getIntent();
        Bundle args = i.getBundleExtra("BUNDLE");
        p = (Paciente) args.getSerializable("paciente");
        final ArrayList<Historico> list = p.getHistorico();

        listaAdapterHistorico adapterHistorico = new listaAdapterHistorico(this,list);
        ListView listView = (ListView) findViewById(R.id.ListView);
        listView.setAdapter(adapterHistorico);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Historico historico = new Historico();
                historico.setCpf(list.get(position).getCpf());
                historico.setTitulo(list.get(position).getTitulo());
                historico.setDetalhe(list.get(position).getDetalhe());
                historico.setData(list.get(position).getData());
                Intent i = new Intent (listarHistorico.this, historicoDetalhe.class);
                Bundle args = new Bundle();
                args.putSerializable("paciente",(Serializable) p);
                args.putSerializable("historico",(Serializable)historico);
                i.putExtra("BUNDLE", args);
                startActivity(i);
            }
        });

    }
}
