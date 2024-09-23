package com.app.upscar.view;

import static com.app.upscar.model.Variaveis.servicoescolhido;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.app.upscar.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;


public class InfoServicos extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private TextView textView;
    private ImageView iconeWhatsapp;
    private ImageView iconeTelefoneLigar;

    private ImageView imageView5;

    private ImageView imageLogo;


    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_servicos);

        textView = findViewById(R.id.textNomeServico);
        iconeWhatsapp = findViewById(R.id.ImageViewWhatsapp);
        iconeTelefoneLigar = findViewById(R.id.ImageViewLigar);
        imageView5 = findViewById(R.id.imageViewEmail);
        //imageLogo = findViewById(R.id.ImageViewServico);

        textView.setText(servicoescolhido.getNome());

        if(servicoescolhido.getWhatsapp().isEmpty()){
            aplicarFiltroPretoBranco();
        }
        //imageLogo.setImageResource(getResources().getIdentifier(servicoescolhido.getIcon(), "drawable", getPackageName()));


        iconeWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!servicoescolhido.getWhatsapp().isEmpty()){
                    String numerotelefone = servicoescolhido.getWhatsapp();
                    String mensagem = "Olá!";
                    String mensagemcodificada = Uri.encode(mensagem);
                    String url = "https://wa.me/+" + numerotelefone + "?text=" + mensagemcodificada;

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));

                    startActivity(intent);
                }else{
                    showPopup();
                }
            }
        });

        iconeTelefoneLigar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                String numeroTelefone = servicoescolhido.getWhatsapp();
                if(servicoescolhido.getWhatsapp() == "") {
                    numeroTelefone = servicoescolhido.getTelefone();
                }
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + numeroTelefone));
                startActivity(intent);
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String informacao = servicoescolhido.getWhatsapp();

                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label", informacao);
                clipboard.setPrimaryClip(clip);

                Toast.makeText(getApplicationContext(), "Número de telefone copiado", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng local = new LatLng(Double.parseDouble(servicoescolhido.getX()), Double.parseDouble(servicoescolhido.getY()));
        mMap.addMarker(new MarkerOptions().position(local).title(servicoescolhido.getNome()));


        float zoomLevel = 17.0f;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(local, zoomLevel));

    }

    public void showPopup() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Sem WhatsApp no momento!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Faça algo se o usuário clicar em OK
                    }
                });
        // Crie e exiba o AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void aplicarFiltroPretoBranco() {
        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0); // Define a saturação para 0 para remover a cor

        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
        iconeWhatsapp.setColorFilter(filter); // Aplica o filtro à ImageView
    }

}
