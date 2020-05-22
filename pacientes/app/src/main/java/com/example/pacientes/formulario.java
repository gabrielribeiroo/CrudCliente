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

public class formulario extends AppCompatActivity {
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
    private Button btnAdd;
    private ArrayList<Paciente> listaPaciente = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        Intent i = getIntent();
        Bundle args = i.getBundleExtra("BUNDLE");
        listaPaciente = (ArrayList<Paciente>) args.getSerializable("lista");
        btnAdd = (Button) findViewById(R.id.btnAlterar);
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

        this.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean validarRepetido = false;
                if (!listaPaciente.isEmpty() && listaPaciente != null) {
                    for (Paciente p : listaPaciente) {
                        if (p.getCPF() != null) {
                            if (!txtCPF.getText().toString().isEmpty() && !p.getCPF().isEmpty()) {
                                if (p.getCPF().equals(txtCPF.getText().toString())) {
                                    validarRepetido = true;
                                }
                            }
                        }
                    }
                    if (validarRepetido) {
                        Toast.makeText(formulario.this, "CPF:" + txtCPF.getText().toString() + " Nao pode ser cadastrado pois ja existe outro registro com o mesmo CPF", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(formulario.this, MainActivity.class);
                        Bundle args = new Bundle();
                        args.putSerializable("lista", (Serializable) listaPaciente);
                        intent.putExtra("BUNDLE", args);
                        startActivity(intent);
                    } else {
                        Paciente paciente = salvaPaciente();
                        if (paciente != null) {
                            listaPaciente.add(paciente);
                            Toast.makeText(formulario.this, txtNomeCompleto.getText().toString() + " foi cadastrado com Sucesso", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(formulario.this, MainActivity.class);
                            Bundle args = new Bundle();
                            args.putSerializable("lista", (Serializable) listaPaciente);
                            intent.putExtra("BUNDLE", args);
                            startActivity(intent);
                        }
                    }
                } else {
                    if (!txtCPF.getText().toString().isEmpty()) {
                        Paciente paciente = salvaPaciente();
                        if (paciente != null) {
                            listaPaciente.add(paciente);
                            Toast.makeText(formulario.this, txtNomeCompleto.getText().toString() + " foi cadastrado com Sucesso", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(formulario.this, MainActivity.class);
                            Bundle args = new Bundle();
                            args.putSerializable("lista", (Serializable) listaPaciente);
                            intent.putExtra("BUNDLE", args);
                            startActivity(intent);
                        } else {
                            Toast.makeText(formulario.this, "Falha ao cadastrar o Paciente", Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(formulario.this, "Falha ao cadastrar o Paciente", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

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