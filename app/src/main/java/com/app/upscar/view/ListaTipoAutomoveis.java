package com.app.upscar.view;

import static com.app.upscar.model.Variaveis.tipoAutomovelEscolhido;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.app.upscar.R;

public class ListaTipoAutomoveis extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tipo_automoveis);
        setTitle("Lista de Automóveis");

        View ViewCarros = findViewById(R.id.automoveis2);
        View ViewMotos = findViewById(R.id.servicos2);


        ViewCarros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipoAutomovelEscolhido = "Carros";
                Intent intent = new Intent(v.getContext(), ListaAutomoveis.class);
                v.getContext().startActivity(intent);

            }
        });

        ViewMotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipoAutomovelEscolhido = "Motos";
                Intent intent = new Intent(v.getContext(), ListaAutomoveis.class);
                v.getContext().startActivity(intent);

            }
        });

    }
}