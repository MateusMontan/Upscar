package com.app.upscar.model;

import java.util.Date;

public class GeradorDeChave {
    private String key = new String();

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void concactKey(String key) {
        this.key += key;
    }

    public String gerarChaveSegura(){

        Date hoje = new Date();

        setKey("user-");
        concactKey(hoje.getTime()+"");

        return getKey();
    }
}
