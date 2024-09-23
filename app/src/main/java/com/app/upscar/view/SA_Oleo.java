package com.app.upscar.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.app.upscar.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class SA_Oleo extends AppCompatActivity {
    private static final int PICK_FILE_REQUEST_CODE = 123;
    private static final int REQUEST_PERMISSION_CODE = 456;
    private File internalFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sa_oleo);

        if (checkPermission()) {
            setupUI();
        } else {
            requestPermission();
        }
    }

    private void setupUI() {
        final ImageView imageView = findViewById(R.id.imageView5);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFilePicker();
                // Aqui, você pode adicionar qualquer lógica adicional que deseja executar ao clicar na imagem.
            }
        });
    }
    private boolean checkPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION_CODE);
    }

    private void openFilePicker() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_FILE_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                setupUI();
            } else {
                Toast.makeText(this, "Permissão negada. Não é possível acessar a galeria.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_FILE_REQUEST_CODE && resultCode == RESULT_OK) {
            Uri selectedFileUri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(selectedFileUri);

                // Especifique o diretório desejado
                File customDirectory = new File(getExternalFilesDir(null), "MyCARPhoto");

                if (!customDirectory.exists()) {
                    customDirectory.mkdirs();
                }

                // Nome do arquivo com carimbo de data e hora
                String timeStamp = String.valueOf(System.currentTimeMillis());
                String imageFileName = "JPEG_" + timeStamp + ".jpg";

                // Cria o arquivo no diretório personalizado
                internalFile = new File(customDirectory, imageFileName);

                FileOutputStream outputStream = new FileOutputStream(internalFile);

                byte[] buffer = new byte[1024];
                int length;
                while ((length = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, length);
                }

                inputStream.close();
                outputStream.close();

                Toast.makeText(this, "Foto salva no diretório personalizado", Toast.LENGTH_SHORT).show();
                exibirFoto();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Erro ao salvar a foto: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void exibirFoto() {
        if (internalFile != null && internalFile.exists()) {
            TextView textView = findViewById(R.id.editTextText);
            String nomeDaImagem = internalFile.getName();
            textView.setText(nomeDaImagem);
        } else {
            Toast.makeText(this, "A foto não está disponível.", Toast.LENGTH_SHORT).show();
        }
    }

}
