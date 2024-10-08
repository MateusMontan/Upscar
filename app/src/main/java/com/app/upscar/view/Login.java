package com.app.upscar.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.app.upscar.R;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        Button botaoLogin = findViewById(R.id.buttonLogar);
        Button botaoCadastro = findViewById(R.id.botaoCadastro);

        EditText editUser = findViewById(R.id.EditUser);
        EditText editSenha = findViewById(R.id.editTextPassword);

        editUser.setText("mateusinfocefetmg@gmail.com");
        editSenha.setText("123456");


        botaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TESTE", "onClick: "+editUser.getText().toString()+editSenha.getText().toString());
                if(editUser.getText().toString().equals("mateusinfocefetmg@gmail.com") ){
                    if(editSenha.getText().toString().equals("123456")){
                        Intent intent = new Intent(Login.this, TeladeLoading.class);
                        startActivity(intent);

                        finish();
                    }else{
                        Toast.makeText(Login.this, "Email e/ou senha incorreto!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(Login.this, "Email e/ou senha incorreto!", Toast.LENGTH_SHORT).show();
                }

            }
        });


        botaoCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Cadastro.class);
                startActivity(intent);
            }
        });

    }
}