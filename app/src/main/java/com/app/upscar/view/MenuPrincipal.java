package com.app.upscar.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.app.upscar.R;
import com.app.upscar.model.Cidade;
import com.app.upscar.model.Servico;
import com.app.upscar.model.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import static com.app.upscar.model.Variaveis.cidadeescolhida;
import static com.app.upscar.model.Variaveis.cidades;
import static com.app.upscar.model.Variaveis.database;
import static com.app.upscar.model.Variaveis.usuarioEscolhido;

import java.util.ArrayList;
import java.util.List;


public class MenuPrincipal extends AppCompatActivity {

    protected ArrayList<String> items;
    protected Spinner dropdown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        setTitle("Menu Principal");

        items = new ArrayList<>();

        View ViewAutomoveis = findViewById(R.id.automoveis);
        View ViewServicos = findViewById(R.id.servicos);
        ImageView imagePerfil = findViewById(R.id.perfil);
        dropdown = findViewById(R.id.spinnerCidade);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cidadeescolhida = cidades.get(position).clone();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        imagePerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EditarUsuario.class);
                v.getContext().startActivity(intent);
            }
        });


        ViewServicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), ListaServicos.class);
                v.getContext().startActivity(intent);

            }
        });

        ViewAutomoveis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), ListaAutomoveis.class);
                v.getContext().startActivity(intent);

            }
        });

        database =  FirebaseDatabase.getInstance();

        DatabaseReference UserRef = database.getReference("usuarios").child("maF9VK0I2XeTmUV85RziKVC94za2").child("dados");

        UserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if(snapshot.exists()){
                    Usuario usuario = snapshot.getValue(Usuario.class);

                    if(usuario != null){
                        usuarioEscolhido = usuario.clone();
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        DatabaseReference myRef = database.getReference("cidades");
        cidades = new ArrayList<>();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot cidadeSnapshot : dataSnapshot.getChildren()) {
                    Cidade cidade = cidadeSnapshot.getValue(new GenericTypeIndicator<Cidade>() {});

                    if (cidade != null) {
                        String nomeCidade = cidade.getNome();
                        List<Servico> servicos = cidade.getServicos();
                        if (cidadeSnapshot.hasChild("servicos")) {
                            for (DataSnapshot servicoSnapshot : cidadeSnapshot.child("servicos").getChildren()) {
                                Servico servico = servicoSnapshot.getValue(Servico.class);
                                if (servico != null) {
                                    Log.d("SERVICO", "Nome: "+servico.getNome());
                                }
                            }
                        }

                    }

                    cidades.add(cidade.clone());
                    items.add(cidade.getNome());

                }

                cidadeescolhida = cidades.get(0).clone();
                Log.d("ESCOLHIDO", "Servicos da Cidade escolhida: "+cidadeescolhida.getNome()+cidadeescolhida.getServicos().get(0).getNome());
                AtualizarDropdown();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.d("TESTE", "Failed to read value.", error.toException());
            }
        });
    }

    public void AtualizarDropdown(){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
    }
}
