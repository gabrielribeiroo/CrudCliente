package com.example.pacientes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.Serializable;
import java.util.ArrayList;

public class historicoDetalhe extends AppCompatActivity {
    private EditText txtTitulo;
    private EditText txtDetalhe;
    private Paciente p;
    private Historico h;
    private ArrayList<Historico> list = new ArrayList<>();
    private Button btnAlterar;
    private ArrayList<Paciente> listaPacientes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_detalhe);
        txtDetalhe = (EditText) findViewById(R.id.txtDetalhe);
        txtTitulo = (EditText) findViewById(R.id.txtTitulo);
        Intent i = getIntent();
        onLoad();
        txtTitulo.setText(h.getTitulo());
        txtDetalhe.setText(h.getDetalhe());
        btnAlterar = (Button) findViewById(R.id.btnAlterar);

        this.btnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Historico> list = p.getHistorico();
                for (Historico his : list) {
                    if (his.getTitulo().equals(h.getTitulo()) && his.getData().equals(h.getData()) && his.getDetalhe().equals(h.getDetalhe()) && his.getCpf().equals(h.getCpf())) {
                        his.setTitulo(txtTitulo.getText().toString());
                        his.setDetalhe(txtDetalhe.getText().toString());
                    }
                }

                for (Paciente paciente : listaPacientes) {
                    if (p.getCPF().equals(paciente.getCPF())) {
                        p.setHistorico(list);
                        paciente.setHistorico(list);
                    }
                }
                Intent i = new Intent(historicoDetalhe.this, ClienteDetalhes.class);
                Bundle args = new Bundle();
                args.putSerializable("lista", (Serializable) listaPacientes);
                args.putSerializable("paciente", (Serializable) p);
                i.putExtra("BUNDLE", args);
                startActivity(i);
            }
        });
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
            h = (Historico) args.getSerializable("historico");
        }
    }

}
