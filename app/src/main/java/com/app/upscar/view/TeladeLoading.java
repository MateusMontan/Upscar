package com.app.upscar.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.app.upscar.R;

public class TeladeLoading extends AppCompatActivity {


    private TextView loadingText;
    private Handler handler;
    private int dotCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telade_loading);

        loadingText = findViewById(R.id.loadingText);
        handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dotCount = (dotCount + 1) % 4;
                StringBuilder dots = new StringBuilder();
                for (int i = 0; i < dotCount; i++) {
                    dots.append(".");
                }
                loadingText.setText("Carregando" + dots);
                handler.postDelayed(this, 500);
            }
        }, 500);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(TeladeLoading.this, MenuPrincipal.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}