package com.github.willpinhal.apivendas.apivendas.model;

public class ClienteModel {

    public ClienteModel() {

    }

    public ClienteModel(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id='" + id + '\'' +
                "nome='" + nome + '\'' +
                '}';
    }
}
