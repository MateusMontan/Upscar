package com.app.upscar.view;

import static com.app.upscar.model.Variaveis.database;

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
    public static ArrayList<Automovel> caminhoes;
    public static ArrayList<Automovel> motos;

    ListView listViewCaminhoes;
    ListView listViewCarros;
    ListView listViewMotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_automoveis);
        setTitle("Lista de Automóveis");
        listViewCarros = findViewById(R.id.listViewCarros);
        listViewCaminhoes = findViewById(R.id.listViewCaminhoes);
        listViewMotos = findViewById(R.id.listViewMotos);
        database =  FirebaseDatabase.getInstance();
        DatabaseReference reference_carros = database.getReference("usuarios/maF9VK0I2XeTmUV85RziKVC94za2/automoveis/carros");
        carros = new ArrayList<>();
        DatabaseReference reference_caminhoes = database.getReference("usuarios/maF9VK0I2XeTmUV85RziKVC94za2/automoveis/caminhoes");
        caminhoes = new ArrayList<>();
        DatabaseReference reference_motos = database.getReference("usuarios/maF9VK0I2XeTmUV85RziKVC94za2/automoveis/motos");
        motos = new ArrayList<>();
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
        reference_caminhoes.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot servicoSnapshot : dataSnapshot.getChildren()) {
                    Automovel value = servicoSnapshot.getValue(Automovel.class);
                    if (value != null) {
                        caminhoes.add(new Automovel(value.getCategoria(),value.getCor(),value.getMarca(),
                                value.getModelo(),value.getPlaca()));
                    }
                }
                atualizaCaminhoesAdapter();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TESTE", "Failed to read value.", error.toException());
            }
        });
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

        Button criarVeiculo = findViewById(R.id.adicionarVeiculo);

        criarVeiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaAutomoveis.this, AdicionarAutomoveis.class);
                startActivity(intent);
            }
        });

        //exibirPopUp();
    }

    public void atualizaCarrosAdapter(){
        //AdapterAutomoveis adapter = new AdapterAutomoveis(this, carros, exibirPopUp);
        if(carros.isEmpty()){
            listViewCarros.setVisibility(View.INVISIBLE);
        }else{
            listViewCarros.setVisibility(View.VISIBLE);
            //listViewCarros.setAdapter(adapter);
        }
    }

    public void atualizaCaminhoesAdapter(){
        AdapterAutomoveis adapter = new AdapterAutomoveis(this, caminhoes);
        if(caminhoes.isEmpty()){
            listViewCaminhoes.setVisibility(View.INVISIBLE);
        }else{
            listViewCaminhoes.setVisibility(View.VISIBLE);
            listViewCaminhoes.setAdapter(adapter);
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

//    public static void exibirPopUp() {
//        // Cria um objeto Dialog sem um título padrão
//        final Dialog dialog = new Dialog(this);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.popup_layout);
//
//        // Referências aos elementos de UI no layout do pop-up
//        TextView textView = dialog.findViewById(R.id.textView);
//        Button btnFechar = dialog.findViewById(R.id.btnFechar);
//
//        // Define o texto do TextView conforme necessário
//        textView.setText("Conteúdo do Pop-up");
//
//        // Configura o botão para fechar o pop-up
//        btnFechar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Lógica a ser executada ao clicar no botão Fechar
//                Toast.makeText(ListaAutomoveis.this, "Pop-up fechado", Toast.LENGTH_SHORT).show();
//                dialog.dismiss(); // Fecha o pop-up
//            }
//        });
//
//        // Exibe o pop-up
//        dialog.show();
//    }
}
