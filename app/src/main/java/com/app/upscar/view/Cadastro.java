package com.app.upscar.view;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.app.upscar.R;
import com.app.upscar.model.Autenticador;
import com.app.upscar.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class Cadastro extends AppCompatActivity {

    private DatabaseReference usuariosRef;

    private Usuario usuario;

    private FirebaseAuth firebaseAuth;

    private EditText editNome, editEmail, editPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        setTitle("Cadastro");

        editNome = findViewById(R.id.editNome);
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editSenha);

        TextView textView = findViewById(R.id.textView10);
        textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        Button botaoEnviar = findViewById(R.id.buttonInserir);

        Button botaoLogin = findViewById(R.id.buttonInserir);
        botaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cadastro.this, Login.class);
                startActivity(intent);
            }
        });

        botaoEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validarCampos()){
                    criarUsuario();
                }

            }
        });

    }

    private void criarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNome(editNome.getText().toString());
        usuario.setEmail(editEmail.getText().toString());
        usuario.setSenha(editPassword.getText().toString());

        firebaseAuth = Autenticador.FirebaseAutenticar();

        firebaseAuth.createUserWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Cadastro.this, "Cadastro concluido!", Toast.LENGTH_SHORT).show();
                }else{

                }
            }
        });
    }

    private boolean validarCampos(){

        return true;
    }
}