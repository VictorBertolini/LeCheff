package com.lecheff;

public class Cliente {

    private int id;
    private String nome;
    private int idade;
    private String email;
    private String senha;
    private String telefone;
    private String cidade;
    private String cep;
    private String rua;
    private String numero;
    private String bairro;
    private String estado;
    private String complemento;

    public Cliente(String nome, int idade, String email, String senha, String telefone, String cidade, String cep, String rua, String numero, String bairro, String estado, String complemento) {
        this.nome = nome;
        this.idade = idade;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.cidade = cidade;
        this.cep = cep;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.estado = estado;
        this.complemento = complemento;
    }

    // Construtor com id (usado em consulta e alteração)
    public Cliente(int id, String nome, int idade, String email, String senha, String telefone, String cidade, String cep, String rua, String numero, String bairro, String estado, String complemento) {
        this(nome, idade, email, senha, telefone, cidade, cep, rua, numero, bairro, estado, complemento);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
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

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

}
