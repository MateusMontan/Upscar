package com.app.upscar.model;

public class Servico implements Cloneable{

    private String tipo;
    /*
    Tipo de Servico

    0 - Posto de Gasolina
    1 - Autoeletrica
    2 - Mecanica
    3 - Borracharia
    4 - Concessionaria
    5 - Guincho
    6 - Lanternagem
     */

    private String nome;
    private String email;
    private String whatsapp;
    private String endereco;
    private String telefone;
    private String x;
    private String y;
    private String icon;

    public void setX(String x) {
        this.x = x;
    }

    public String getX() {
        return x;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setY(String y) {
        this.y = y;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getY() {
        return y;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }


    public String getIcon() {
        return icon;
    }

    public Servico(){

    }
    public Servico(String icon, String nome, String whatsapp, String x, String y, String email, String tipo, String endereco) {
        this.nome = nome;
        this.whatsapp = whatsapp;
        this.x = x;
        this.y = y;
        this.icon = icon;
        this.email = email;
        this.tipo = tipo;
        this.endereco = endereco;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipo() {return tipo; }

    public void setTipo(String tipo){this.tipo = tipo;}


    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public Servico clone() {
        try {
            return (Servico) super.clone();
        } catch (CloneNotSupportedException e) {
            // Tratar a exceção, se necessário
            return null;
        }
    }


}
