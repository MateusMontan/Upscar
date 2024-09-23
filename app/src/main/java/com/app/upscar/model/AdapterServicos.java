package com.app.upscar.model;

// ServiceAdapter.java
import static com.example.mycar.classes.Variaveis.servicoescolhido;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.example.mycar.R;
import com.example.mycar.tela.Servicos.Info_Servicos;

import java.util.List;

public class AdapterServicos extends ArrayAdapter<Servico> {

    public AdapterServicos(Context context, List<Servico> services) {
        super(context, 0, services);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obter o objeto Service para esta posição
        Servico service = getItem(position);

        // Verificar se a view está sendo reutilizada, caso contrário, inflar a view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.botao_adapter_servicos, parent, false);
        }

        // Configurar o botão do layout
        Button btnService = convertView.findViewById(R.id.Btn1);

        btnService.setText(service.getNome());
        btnService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Info_Servicos.class);
                servicoescolhido = service.clone();
                v.getContext().startActivity(intent);
            }
        });

        return convertView;
    }
}

