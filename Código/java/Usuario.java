package com.example.amantesdasetimaarte;

public class Usuario {

    private String id_usuario;
    private String nome;
    private String sobrenome;
    private String email;
    private String senha;

    public Usuario(){

    }

    public Usuario(String nome, String sobrenome, String email, String senha) {
        this.id_usuario = id_usuario;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.senha = senha;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
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

    @Override
    public String toString() {
        return id_usuario.toString() + " " + nome.toString();
    }
}


