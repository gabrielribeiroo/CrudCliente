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

public class ClienteEditar extends AppCompatActivity {
    private EditText txtNomeCompleto;
    private EditText txtCPF;
    private EditText txtEmail;
    private EditText txtTel;
    private EditText txtCEP;
    private EditText txtRua;
    private EditText txtNumero;
    private EditText txtCompl;
    private EditText txtBairro;
    private EditText txtCidadeEstado;
    private Button btnAlterar;
    private ArrayList<Paciente> listaPaciente = new ArrayList<>();
    private Paciente p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_editar);
        onLoad();
        btnAlterar = (Button) findViewById(R.id.btnAlterar);
        txtNomeCompleto = (EditText) findViewById(R.id.txtNome);
        txtCPF = (EditText) findViewById(R.id.txtCPF);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtTel = (EditText) findViewById(R.id.txtTel);
        txtCEP = (EditText) findViewById(R.id.txtCEP);
        txtRua = (EditText) findViewById(R.id.txtRua);
        txtNumero = (EditText) findViewById(R.id.txtNum);
        txtCompl = (EditText) findViewById(R.id.txtCompl);
        txtBairro = (EditText) findViewById(R.id.txtBairro);
        txtCidadeEstado = (EditText) findViewById(R.id.txtCidade);
        setText(p);
        this.btnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Paciente paciente = salvaPaciente();
                if (paciente != null) {
                    for (Paciente pac : listaPaciente) {
                        if (paciente.getCPF().equals(pac.getCPF())) {
                            pac.setNomeCompleto(paciente.getNomeCompleto());
                            pac.setEmail(paciente.getEmail());
                            pac.setTelefone(paciente.getTelefone());
                            pac.setCep(paciente.getCep());
                            pac.setRua(paciente.getRua());
                            pac.setNumero(paciente.getNumero());
                            pac.setComplemento(paciente.getComplemento());
                            pac.setBairro(paciente.getBairro());
                            pac.setCidadeEstado(paciente.getCidadeEstado());
                            p = pac;
                        }
                    }
                    Intent intent = new Intent(ClienteEditar.this, ClienteDetalhes.class);
                    Bundle args = new Bundle();
                    args.putSerializable("lista", (Serializable) listaPaciente);
                    args.putSerializable("paciente", (Serializable) p);
                    intent.putExtra("BUNDLE", args);
                    startActivity(intent);
                } else {
                    Toast.makeText(ClienteEditar.this, "Falha ao Editar Paciente", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void setText(Paciente p) {
        txtNomeCompleto.setText(p.getNomeCompleto());
        txtCPF.setText(p.getCPF());
        txtEmail.setText(p.getEmail());
        txtTel.setText(p.getTelefone());
        txtCEP.setText(p.getCep());
        txtRua.setText(p.getRua());
        txtNumero.setText(p.getNumero());
        txtCompl.setText(p.getComplemento());
        txtBairro.setText(p.getBairro());
        txtCidadeEstado.setText(p.getCidadeEstado());
    }

    private void onLoad() {
        //recebo a INtent gerada
        Intent i = getIntent();
        Bundle args = i.getBundleExtra("BUNDLE");
        //valido se existe algum dado a ser processado
        if (args != null) {
            //atrelo a lista atualizada e o paciente atualizado
            listaPaciente = (ArrayList<Paciente>) args.getSerializable("lista");
            p = (Paciente) args.getSerializable("paciente");
        }
    }

    private Paciente salvaPaciente() {
        Paciente paciente = new Paciente();

        if (txtNomeCompleto.getText().toString().isEmpty() == false) {
            paciente.setNomeCompleto(txtNomeCompleto.getText().toString());
        } else {
            return null;
        }
        if (this.txtCPF.getText().toString().isEmpty() == false) {
            paciente.setCPF(txtCPF.getText().toString());
        } else {
            return null;
        }
        if (this.txtEmail.getText().toString().isEmpty() == false) {
            paciente.setEmail(txtEmail.getText().toString());
        } else {
            return null;
        }
        if (this.txtTel.getText().toString().isEmpty() == false) {
            paciente.setTelefone(txtTel.getText().toString());
        } else {
            return null;
        }
        if (this.txtCEP.getText().toString().isEmpty() == false) {
            paciente.setCep(txtCEP.getText().toString());
        } else {
            return null;
        }
        if (this.txtRua.getText().toString().isEmpty() == false) {
            paciente.setRua(txtRua.getText().toString());
        } else {
            return null;
        }
        if (this.txtNumero.getText().toString().isEmpty() == false) {
            paciente.setNumero(txtNumero.getText().toString());
        } else {
            return null;
        }
        if (this.txtCompl.getText().toString().isEmpty() == false) {
            paciente.setComplemento(txtCompl.getText().toString());
        }

        if (this.txtBairro.getText().toString().isEmpty() == false) {
            paciente.setBairro(txtBairro.getText().toString());
        } else {
            return null;
        }
        if (this.txtCidadeEstado.getText().toString().isEmpty() == false) {
            paciente.setCidadeEstado(txtCidadeEstado.getText().toString());
        } else {
            return null;
        }

        return paciente;
    }
}
