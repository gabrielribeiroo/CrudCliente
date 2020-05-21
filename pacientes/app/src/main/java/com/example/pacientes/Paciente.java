package com.example.pacientes;

import java.io.Serializable;

public class Paciente implements Serializable {
    private String nomeCompleto;
    private String CPF;
    private String email;
    private String telefone;
    private String cep;
    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidadeEstado;

    @Override
    public String toString() {
        return "Paciente{" +
                "nomeCompleto='" + nomeCompleto + '\'' +
                ", CPF='" + CPF + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", cep='" + cep + '\'' +
                ", rua='" + rua + '\'' +
                ", numero='" + numero + '\'' +
                ", complemento='" + complemento + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidadeEstado='" + cidadeEstado + '\'' +
                '}';
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidadeEstado() {
        return cidadeEstado;
    }

    public void setCidadeEstado(String cidadeEstado) {
        this.cidadeEstado = cidadeEstado;
    }
}
