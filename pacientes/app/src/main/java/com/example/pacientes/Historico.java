package com.example.pacientes;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class Historico implements Serializable {
    private String Titulo;
    private String data;
    private String Detalhe;
    private String Cpf;

    public Historico(String titulo, String data, String detalhe, String cpf) {
        Titulo = titulo;
        this.data = data;
        Detalhe = detalhe;
        Cpf = cpf;
    }

    public String getCpf() {
        return Cpf;
    }

    public void setCpf(String cpf) {
        Cpf = cpf;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDetalhe() {
        return Detalhe;
    }

    public void setDetalhe(String detalhe) {
        Detalhe = detalhe;
    }
}
