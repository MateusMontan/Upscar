package com.app.upscar.view;

import static com.app.upscar.model.Variaveis.database;
import static com.app.upscar.view.ListaAutomoveis.carros;
import static com.app.upscar.view.ListaAutomoveis.motos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.app.upscar.R;
import com.app.upscar.model.Automovel;
import com.google.firebase.database.DatabaseReference;

public class AdicionarAutomoveis extends AppCompatActivity {

    protected Spinner tipocategoria;

    protected EditText placaEdit, modeloEdit, marcaEdit, corEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_automoveis);
        tipocategoria = findViewById(R.id.spinnerCategoria);
        placaEdit = findViewById(R.id.EditPlaca);
        modeloEdit = findViewById(R.id.EditModelo);
        marcaEdit = findViewById(R.id.EditMarca);
        corEdit = findViewById(R.id.EditCor);
        Button adicionar = findViewById(R.id.buttonInserir);

        Spinner spinnerCategoria = findViewById(R.id.spinnerCategoria);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                new String[]{"Carro", "Moto"}
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoria.setAdapter(adapter);

        adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String index = "100";
                String tipoVeiculo = "carros"; //Vem do spinner
                String categoria = "B";
                String cor = corEdit.getText().toString();
                String marca = marcaEdit.getText().toString();
                String modelo = modeloEdit.getText().toString();
                String placa = placaEdit.getText().toString();

                Automovel temp = new Automovel(categoria, cor, marca, modelo, placa);

                if (tipoVeiculo == "carros") {
                    carros.add(temp);
                    index = String.valueOf(carros.indexOf(temp));
                } else if (tipoVeiculo == "motos") {
                    motos.add(temp);
                    index = String.valueOf(motos.indexOf(temp));
                }

                String idUsuario = "maF9VK0I2XeTmUV85RziKVC94za2";
                DatabaseReference refAutomovel = database.getReference("usuarios/" + idUsuario + "/automoveis/" + tipoVeiculo);
                DatabaseReference novoAutomovelRef = refAutomovel.child(index);
                novoAutomovelRef.setValue(temp);

                Intent intent = new Intent(AdicionarAutomoveis.this, MenuPrincipal.class);
                startActivity(intent);

                finish();

            }
        });
    }}
