package com.app.upscar.view;

import static com.app.upscar.model.Variaveis.database;
import static com.app.upscar.model.Variaveis.tipoAutomovelEscolhido;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

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
                // Mostra o diálogo para adicionar automóveis
                showAdicionarAutomovelDialog(ListaAutomoveis.this);
            }
        });

    }

    public void atualizaCarrosAdapter() {
        if (listViewCarros.getAdapter() == null) {
            AdapterAutomoveis adapter = new AdapterAutomoveis(this, carros);
            listViewCarros.setAdapter(adapter);
        } else {
            ((AdapterAutomoveis) listViewCarros.getAdapter()).atualizarDados(carros);
        }

        if (carros.isEmpty()) {
            listViewCarros.setVisibility(View.INVISIBLE);
        } else {
            listViewCarros.setVisibility(View.VISIBLE);
        }
    }

    public void atualizaMotosAdapter(){
        if (listViewMotos.getAdapter() == null) {
            AdapterAutomoveis adapter = new AdapterAutomoveis(this, motos);
            listViewMotos.setAdapter(adapter);
        } else {
            ((AdapterAutomoveis) listViewMotos.getAdapter()).atualizarDados(motos);
        }

        if(motos.isEmpty()){
            listViewMotos.setVisibility(View.INVISIBLE);
        }else{
            listViewMotos.setVisibility(View.VISIBLE);
        }
    }

    private void showAdicionarAutomovelDialog(Context context) {
        // Cria o diálogo
        Dialog dialog = new Dialog(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.activity_adicionar_automoveis, null);

        // Vincula o layout ao diálogo
        dialog.setContentView(dialogView);

        // Inicializa os elementos da interface
        Spinner tipocategoria = dialogView.findViewById(R.id.spinnerCategoria);
        EditText placaEdit = dialogView.findViewById(R.id.EditPlaca);
        EditText modeloEdit = dialogView.findViewById(R.id.editModelo); // Corrigido
        EditText marcaEdit = dialogView.findViewById(R.id.editMarca); // Corrigido
        EditText corEdit = dialogView.findViewById(R.id.editCor); // Corrigido
        Button adicionar = dialogView.findViewById(R.id.buttonInserir);

        // Configura o Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                context,
                android.R.layout.simple_spinner_item,
                new String[]{"Carro", "Moto"}
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipocategoria.setAdapter(adapter);

        // Lógica do botão "Adicionar" no diálogo
        adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reutilize o código que você já tem para adicionar o automóvel
                String tipoVeiculo = "carros"; //Vem do spinner
                String cor = corEdit.getText().toString();
                String marca = marcaEdit.getText().toString();
                String modelo = modeloEdit.getText().toString();
                String placa = placaEdit.getText().toString();
                Automovel temp = new Automovel("B", cor, marca, modelo, placa);

                // Adicione a lógica de inserir no Firebase e localmente (carros/motos)
                if (tipoVeiculo.equals("carros")) {
                    carros.add(temp);
                    atualizaCarrosAdapter();
                } else if (tipoVeiculo.equals("motos")) {
                    motos.add(temp);
                    atualizaMotosAdapter();
                }

                String idUsuario = "maF9VK0I2XeTmUV85RziKVC94za2"; // Substitua pelo ID real do usuário
                DatabaseReference refAutomovel = database.getReference("usuarios/" + idUsuario + "/automoveis/" + tipoVeiculo);
                refAutomovel.child(String.valueOf(carros.indexOf(temp))).setValue(temp);

                dialog.dismiss(); // Fecha o diálogo ao concluir
            }
        });

        dialog.show();
    }
}
