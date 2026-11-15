package com.lecheff.models;

public class Cliente {

    private int id;
    private String nome;
    private int idade;
    private String email;
    private String senha;
    private String telefone;


    public Cliente(String nome, int idade, String email, String senha, String telefone) {
        this.nome = nome;
        this.idade = idade;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
    }

    // Construtor com id (usado em consulta e alteração)
    public Cliente(int id, String nome, int idade, String email, String senha, String telefone) {
        this(nome, idade, email, senha, telefone);
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
}
