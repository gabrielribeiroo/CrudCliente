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
    private ArrayList<Paciente> listaPacientes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_historico);
        //funcao para carregar as informacoes atuais dos objetos vindos das outras activitys
        onLoad();
        final ArrayList<Historico> list = p.getHistorico();
        if (list == null) {
            Toast.makeText(listarHistorico.this, "Nao possui Historico", Toast.LENGTH_LONG).show();
            Intent i = new Intent(listarHistorico.this, ClienteDetalhes.class);
            Bundle args = new Bundle();
            args.putSerializable("paciente", (Serializable) p);
            args.putSerializable("lista", (Serializable) listaPacientes);
            i.putExtra("BUNDLE", args);
            startActivity(i);
        } else {
            listaAdapterHistorico adapterHistorico = new listaAdapterHistorico(this, list);
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
                    Intent i = new Intent(listarHistorico.this, historicoDetalhe.class);
                    Bundle args = new Bundle();
                    args.putSerializable("paciente", (Serializable) p);
                    args.putSerializable("historico", (Serializable) historico);
                    args.putSerializable("lista", (Serializable) listaPacientes);
                    i.putExtra("BUNDLE", args);
                    startActivity(i);
                }
            });
        }
    }

    private void onLoad() {
        //recebo a INtent gerada
        Intent i = getIntent();
        Bundle args = i.getBundleExtra("BUNDLE");
        //valido se existe algum dado a ser processado
        if (args != null) {
            //atrelo a lista atualizada e o paciente atualizado
            listaPacientes = (ArrayList<Paciente>) args.getSerializable("lista");
            p = (Paciente) args.getSerializable("paciente");
        }
    }
}
