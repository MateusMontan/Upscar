package com.app.upscar.model;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.app.upscar.R;
import com.app.upscar.view.ListaAutoservicos;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.app.upscar.model.Variaveis.automovelescolhido;

import androidx.annotation.NonNull;

import java.util.List;

public class AdapterAutomoveis extends ArrayAdapter<Automovel> {

    protected Context adapContext;
    public AdapterAutomoveis(Context context, List<Automovel> services) {
        super(context, 0, services);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obter o objeto Service para esta posição
        Automovel automovel = getItem(position);

        // Verificar se a view está sendo reutilizada, caso contrário, inflar a view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.xml_botao_adapter_automovel, parent, false);
        }

        // Configurar o botão do layout
        Button btnService = convertView.findViewById(R.id.Btn1);

        btnService.setText(automovel.getModelo());
        btnService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ListaAutoservicos.class);
                automovelescolhido = automovel.clone();
                v.getContext().startActivity(intent);
            }
        });

        btnService.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //ListaAutomoveis.exibirPopUp();

                return true;
            }
        });

        ImageButton btnDelete = convertView.findViewById(R.id.BtnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remover a referência no Firebase
                DatabaseReference reference_carro = FirebaseDatabase.getInstance()
                        .getReference("usuarios/maF9VK0I2XeTmUV85RziKVC94za2/automoveis/carros")
                        .child(automovel.getPlaca()); // Usando a placa como chave única

                reference_carro.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Remover o item da lista local
                        remove(automovel);
                        notifyDataSetChanged();
                        Toast.makeText(getContext(), "Carro removido com sucesso!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Erro ao remover o carro.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        return convertView;

    }


}




