package com.app.upscar.view;

import static com.app.upscar.model.Variaveis.usuarioEscolhido;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.app.upscar.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class EditarUsuario extends AppCompatActivity {

    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_usuario);

        EditText EditUser = findViewById(R.id.EditUser);
        EditText EditEmail = findViewById(R.id.EditEmail);
        EditText EditSenha = findViewById(R.id.EditSenha);
        EditText EditTelefone = findViewById(R.id.EditTelefone);

        EditUser.setText(usuarioEscolhido.nome);
        EditEmail.setText(usuarioEscolhido.email);
        EditSenha.setText(usuarioEscolhido.senha);
        EditTelefone.setText(usuarioEscolhido.telefone);

        Button btnSalvar = findViewById(R.id.Salvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obter os valores editados dos EditText
                String novoNome = EditUser.getText().toString();
                String novoEmail = EditEmail.getText().toString();
                String novaSenha = EditSenha.getText().toString();
                String novoTelefone = EditTelefone.getText().toString();

                // Atualizar os valores no objeto usuarioEscolhido
                usuarioEscolhido.setNome(novoNome);
                usuarioEscolhido.setEmail(novoEmail);
                usuarioEscolhido.setSenha(novaSenha);
                usuarioEscolhido.setTelefone(novoTelefone);

                DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();

                databaseRef.child("usuarios").child("maF9VK0I2XeTmUV85RziKVC94za2").child("dados").child("nome").setValue(novoNome);
                databaseRef.child("usuarios").child("maF9VK0I2XeTmUV85RziKVC94za2").child("dados").child("email").setValue(novoEmail);
                databaseRef.child("usuarios").child("maF9VK0I2XeTmUV85RziKVC94za2").child("dados").child("senha").setValue(novaSenha);
                databaseRef.child("usuarios").child("maF9VK0I2XeTmUV85RziKVC94za2").child("dados").child("telefone").setValue(novoTelefone);

                Toast.makeText(EditarUsuario.this, "Dados atualizados com sucesso!", Toast.LENGTH_SHORT).show();

            }
        });

    }

}