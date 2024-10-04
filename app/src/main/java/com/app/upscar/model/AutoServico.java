package com.app.upscar.model;

public class AutoServico {
    private String data;
    private String garantia;
    private String local;
    private String marca;
    private String proximokm;
    private String trocadokm;

    public AutoServico() {
    }

    public AutoServico(String data, String garantia, String local, String marca, String proximokm, String trocadokm) {
        this.data = data;
        this.garantia = garantia;
        this.local = local;
        this.marca = marca;
        this.proximokm = proximokm;
        this.trocadokm = trocadokm;
    }

    // Getters e Setters
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getGarantia() {
        return garantia;
    }

    public void setGarantia(String garantia) {
        this.garantia = garantia;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getProximokm() {
        return proximokm;
    }

    public void setProximokm(String proximokm) {
        this.proximokm = proximokm;
    }

    public String getTrocadokm() {
        return trocadokm;
    }

    public void setTrocadokm(String trocadokm) {
        this.trocadokm = trocadokm;
    }

    @Override
    public String toString() {
        return "AutoServico{" +
                "data='" + data + '\'' +
                ", garantia='" + garantia + '\'' +
                ", local='" + local + '\'' +
                ", marca='" + marca + '\'' +
                ", proximokm='" + proximokm + '\'' +
                ", trocadokm='" + trocadokm + '\'' +
                '}';
    }
}
