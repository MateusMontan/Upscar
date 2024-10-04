package com.app.upscar.model;

public class Automovel {
    private String categoria;
    private String cor;
    private String marca;
    private String modelo;
    private String placa;
    private AutoServico autoservico; // Novo campo para serviços opcionais

    public Automovel() {
    }

    // Construtor atualizado
    public Automovel(String categoria, String cor, String marca, String modelo, String placa, AutoServico autoservico) {
        this.categoria = categoria;
        this.cor = cor;
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.autoservico = autoservico;
    }

    // Getters e Setters
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public AutoServico getAutoservico() {
        return autoservico;
    }

    public void setAutoservico(AutoServico autoservico) {
        this.autoservico = autoservico;
    }

    @Override
    public String toString() {
        return "Automovel{" +
                "categoria='" + categoria + '\'' +
                ", cor='" + cor + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", placa='" + placa + '\'' +
                ", autoservico=" + (autoservico != null ? autoservico.toString() : "Nenhum serviço registrado") +
                '}';
    }
}
