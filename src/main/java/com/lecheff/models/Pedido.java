package com.lecheff.models;

public class Pedido {

    private int id;
    private String nomeCliente;
    private String endereco;
    private int qntPessoas;
    private String observacoes;
    private String dataHora;
    private String status;

    public Pedido(int id, String nomeCliente, String endereco, int qntPessoas,
                  String observacoes, String dataHora, String status) {
        this.id = id;
        this.nomeCliente = nomeCliente;
        this.endereco = endereco;
        this.qntPessoas = qntPessoas;
        this.observacoes = observacoes;
        this.dataHora = dataHora;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getQntPessoas() {
        return qntPessoas;
    }

    public void setQntPessoas(int qntPessoas) {
        this.qntPessoas = qntPessoas;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
