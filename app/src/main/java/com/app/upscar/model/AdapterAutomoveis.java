package com.app.upscar.model;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.app.upscar.R;
import com.app.upscar.view.ListaAutomoveis;
import com.app.upscar.view.ListaAutoservicos;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.app.upscar.model.Variaveis.automovelescolhido;
import static com.app.upscar.model.Variaveis.tipoAutomovelEscolhido;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class AdapterAutomoveis extends ArrayAdapter<Automovel> {

    protected Context adapContext;
    public AdapterAutomoveis(Context context, List<Automovel> services) {
        super(context, 0, services);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Automovel automovel = getItem(position);

        Log.d("Automovel", "getView: "+automovel.toString());

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.xml_botao_adapter_automovel, parent, false);
        }

        Button btnService = convertView.findViewById(R.id.Btn1);

        btnService.setText(automovel.getModelo() + " - " + automovel.getMarca());
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
                // Criar o popup de confirmação
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater = LayoutInflater.from(getContext());
                View popupView = inflater.inflate(R.layout.popup_confirm_delete, null);

                builder.setView(popupView);
                AlertDialog dialog = builder.create();
                dialog.show();
                dialog.getWindow().setLayout(800, ViewGroup.LayoutParams.WRAP_CONTENT);


                Button btnConfirmDelete = popupView.findViewById(R.id.btnConfirmDelete);
                btnConfirmDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatabaseReference reference_automovel = FirebaseDatabase.getInstance()
                                .getReference("usuarios/maF9VK0I2XeTmUV85RziKVC94za2/automoveis/carros")
                                .child(getItem(position).toString());

                        reference_automovel.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                remove(automovel);
                                notifyDataSetChanged();
                                Toast.makeText(getContext(), "Automóvel removido com sucesso!", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();

                                if(tipoAutomovelEscolhido == "Carros"){
                                    //((ListaAutomoveis) adapContext).atualizaCarrosAdapter();
                                }else if(tipoAutomovelEscolhido == "Motos"){
                                    //((ListaAutomoveis) adapContext).atualizaMotosAdapter();
                                }

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getContext(), "Erro ao remover o carro.", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        });
                    }
                });

                Button btnCancelDelete = popupView.findViewById(R.id.btnCancelDelete);
                btnCancelDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });



        return convertView;

    }

    public void atualizarDados(ArrayList<Automovel> novosDados) {
        this.clear(); // Limpa os dados antigos do adapter
        this.addAll(novosDados); // Adiciona os novos dados ao adapter
        notifyDataSetChanged(); // Notifica o adapter sobre a mudança
    }


}




