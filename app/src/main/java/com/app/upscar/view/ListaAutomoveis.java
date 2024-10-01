package com.app.upscar.view;

import static com.app.upscar.model.Variaveis.database;
import static com.app.upscar.model.Variaveis.tipoAutomovelEscolhido;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.app.upscar.R;
import com.app.upscar.model.AdapterAutomoveis;
import com.app.upscar.model.Automovel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ListaAutomoveis extends AppCompatActivity {

    public static ArrayList<Automovel> carros;
    public static ArrayList<Automovel> motos;

    ListView listViewCarros;
    ListView listViewMotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_automoveis);
        setTitle("Lista de Automóveis");
        listViewCarros = findViewById(R.id.listViewCarros);
        listViewMotos = findViewById(R.id.listViewMotos);
        database =  FirebaseDatabase.getInstance();

        if(tipoAutomovelEscolhido == "Carros"){
            DatabaseReference reference_carros = database.getReference("usuarios/maF9VK0I2XeTmUV85RziKVC94za2/automoveis/carros");
            carros = new ArrayList<>();
            reference_carros.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot servicoSnapshot : dataSnapshot.getChildren()) {
                        Automovel value = servicoSnapshot.getValue(Automovel.class);
                        if (value != null) {
                            carros.add(new Automovel(value.getCategoria(),value.getCor(),value.getMarca(),
                                    value.getModelo(),value.getPlaca()));
                        }
                    }
                    atualizaCarrosAdapter();
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w("TESTE", "Failed to read value.", error.toException());
                }
            });
        }else if(tipoAutomovelEscolhido == "Motos"){
            DatabaseReference reference_motos = database.getReference("usuarios/maF9VK0I2XeTmUV85RziKVC94za2/automoveis/motos");
            motos = new ArrayList<>();

            reference_motos.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot servicoSnapshot : dataSnapshot.getChildren()) {
                        Automovel value = servicoSnapshot.getValue(Automovel.class);
                        if (value != null) {
                            motos.add(new Automovel(value.getCategoria(),value.getCor(),value.getMarca(),
                                    value.getModelo(),value.getPlaca()));
                        }
                    }
                    atualizaMotosAdapter();
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w("TESTE", "Failed to read value.", error.toException());
                }
            });
        }

        Button criarVeiculo = findViewById(R.id.adicionarVeiculo);

        criarVeiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaAutomoveis.this, AdicionarAutomoveis.class);
                startActivity(intent);
            }
        });

    }

    public void atualizaCarrosAdapter() {
        AdapterAutomoveis adapter = new AdapterAutomoveis(this, carros);
        if (carros.isEmpty()) {
            listViewCarros.setVisibility(View.INVISIBLE);
        } else {
            listViewCarros.setVisibility(View.VISIBLE);
            listViewCarros.setAdapter(adapter);
        }
    }

    public void atualizaMotosAdapter(){
        AdapterAutomoveis adapter = new AdapterAutomoveis(this, motos);
        if(motos.isEmpty()){
            listViewMotos.setVisibility(View.INVISIBLE);
        }else{
            listViewMotos.setVisibility(View.VISIBLE);
            listViewMotos.setAdapter(adapter);
        }
    }
}
