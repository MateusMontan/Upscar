package com.app.upscar.model;

import java.util.ArrayList;
import java.util.List;

public class Cidade implements Cloneable {
    private List<Servico> servicos;
    private String nome;

    public List<Servico> getServicos() {
        return servicos;
    }

    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Construtor padrão sem argumentos
    public Cidade() {
        this.servicos = new ArrayList<>();
    }

    // Construtor com argumentos
    public Cidade(String nome, ArrayList<Servico> servicos) {
        this.nome = nome;
        this.servicos = servicos;
    }

    public Cidade clone() {
        try {
            return (Cidade) super.clone();
        } catch (CloneNotSupportedException e) {
            // Tratar a exceção, se necessário
            return null;
        }
    }
}
