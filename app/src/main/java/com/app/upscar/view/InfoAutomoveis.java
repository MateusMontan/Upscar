package com.app.upscar.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.app.upscar.R;

public class InfoAutomoveis extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_automoveis);
        setTitle("Informações - Automóveis");
    }
}