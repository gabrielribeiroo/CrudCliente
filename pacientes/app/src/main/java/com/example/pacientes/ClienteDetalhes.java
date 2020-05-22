package com.example.pacientes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class ClienteDetalhes extends AppCompatActivity {
    private Paciente p;
    private TextView txtNome;
    private TextView txtCPF;
    private TextView txtEmail;
    private TextView txtTelefone;
    private TextView txtEndereco;
    private Button btnAddHis;
    private Button btnVerHis;
    private Button btnHome;
    private Paciente paciente;
    private ArrayList<Paciente> listaPacientes = new ArrayList<>();
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_detalhes);
        this.txtNome = (TextView) findViewById(R.id.txtNome);
        this.txtCPF = (TextView) findViewById(R.id.txtCPF);
        this.txtEmail = (TextView) findViewById(R.id.txtEmail);
        this.txtTelefone = (TextView) findViewById(R.id.txtTel);
        this.txtEndereco = (TextView) findViewById(R.id.txtEndereco);
        this.btnAddHis = (Button) findViewById(R.id.btnAddHis);
        this.btnVerHis = (Button) findViewById(R.id.btnVerHis);
        this.btnHome = (Button) findViewById(R.id.btnHome);
        onLoad();
        Intent i = getIntent();
        Bundle args = i.getBundleExtra("BUNDLE");
        p = (Paciente) args.getSerializable("paciente");
        setText(p);
        this.btnAddHis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (ClienteDetalhes.this, adicionarHistorico.class);
                Bundle args = new Bundle();
                args.putSerializable("paciente",(Serializable) p);
                i.putExtra("BUNDLE", args);
                startActivity(i);
            }
        });
        this.btnVerHis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (ClienteDetalhes.this, listarHistorico.class);
                Bundle args = new Bundle();
                args.putSerializable("paciente",(Serializable) p);
                i.putExtra("BUNDLE", args);
                startActivity(i);
            }
        });
    }

    private void setText(Paciente p){
    txtNome.setText(p.getNomeCompleto());
    txtCPF.setText(p.getCPF());
    txtEmail.setText(p.getEmail());
    txtTelefone.setText(p.getTelefone());
    txtEndereco.setText(p.toString());
    }
    private void OnLoad() {

        Intent i = getIntent();
        Bundle args = i.getBundleExtra("BUNDLE");
        if (args != null) {
            ArrayList<Paciente> listaPaciente = (ArrayList<Paciente>) args.getSerializable("lista");
            p = (Paciente) args.getSerializable("paciente");
            //Fazer a referencia de todas a paginas de array para obterem as listas e pacientes atualizado a toda iteracao
            if (listaPaciente != null) {
                for (Paciente p : listaPaciente) {
                    listaPacientes.add(p);
                }
            }
        }
    }
}
