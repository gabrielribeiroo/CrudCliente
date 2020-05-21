package com.example.pacientes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class ClienteDetalhes extends AppCompatActivity {
    private Paciente p;
    private TextView txtNome;
    private TextView txtCPF;
    private TextView txtEmail;
    private TextView txtTelefone;
    private TextView txtEndereco;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_detalhes);
        this.txtNome = (TextView) findViewById(R.id.txtNome);
        this.txtCPF = (TextView) findViewById(R.id.txtCPF);
        this.txtEmail = (TextView) findViewById(R.id.txtEmail);
        this.txtTelefone = (TextView) findViewById(R.id.txtTel);
        this.txtEndereco = (TextView) findViewById(R.id.txtEndereco);
        Intent i = getIntent();
        Bundle args = i.getBundleExtra("BUNDLE");
        p = (Paciente) args.getSerializable("paciente");
        setText(p);
    }
    private void setText(Paciente p){
    txtNome.setText(p.getNomeCompleto());
    txtCPF.setText(p.getCPF());
    txtEmail.setText(p.getEmail());
    txtTelefone.setText(p.getTelefone());
    txtEndereco.setText(p.toString());
    }
}
