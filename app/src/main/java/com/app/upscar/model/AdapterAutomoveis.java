package com.app.upscar.model;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.app.upscar.R;
import com.app.upscar.view.ListaAutoservicos;
import static com.app.upscar.model.Variaveis.automovelescolhido;

import java.util.List;

public class AdapterAutomoveis extends ArrayAdapter<Automovel> {

    protected Context adapContext;
    public AdapterAutomoveis(Context context, List<Automovel> services) {
        super(context, 0, services);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obter o objeto Service para esta posição
        Automovel service = getItem(position);

        // Verificar se a view está sendo reutilizada, caso contrário, inflar a view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.xml_botao_adapter_automovel, parent, false);
        }

        // Configurar o botão do layout
        Button btnService = convertView.findViewById(R.id.Btn1);

        btnService.setText(service.getModelo());
        btnService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ListaAutoservicos.class);
                automovelescolhido = service.clone();
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
        return convertView;

    }


}




