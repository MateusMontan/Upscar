package com.app.upscar.view;

import static com.app.upscar.model.Variaveis.automovelescolhido;
import static com.app.upscar.model.Variaveis.autoservicoescolhido;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.app.upscar.R;

public class ListaAutoservicos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_autoservicos);

        Button botaoOleo = findViewById(R.id.botaoOleo);

        botaoOleo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //autoservicoescolhido = automovelescolhido.getAutoservico();
                //Log.d("AutoServico", "onCreate: "+autoservicoescolhido.toString());
                Intent intent = new Intent(ListaAutoservicos.this, ServicoOleo.class);
                startActivity(intent);
            }
        });
    }
}