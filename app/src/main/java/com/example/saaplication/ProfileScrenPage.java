package com.example.saaplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.io.IOException;

public class ProfileScrenPage extends AppCompatActivity {


    private TextView viewProfileName, btImageToProfile;
    private ImageView addProfileImg;
    FirebaseFirestore firestoreBancoDeDados = FirebaseFirestore.getInstance();
    String usuarioId;
    ImageButton botaoVoltarPagina;
    Bitmap bitmap = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_scren_page);
        IniciarComponentes();


        botaoVoltarPagina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileScrenPage.this, PaginaInicial.class));
            }
        });


        btImageToProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPegaFoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(intentPegaFoto, "Selecione uma imagem"), 123);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 123) {
                Uri imagemSelecionada = data.getData();
                addProfileImg.setImageURI(imagemSelecionada);
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onStart() {
        super.onStart();

        usuarioId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference = firestoreBancoDeDados.collection("Usuario").document(usuarioId);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                if (documentSnapshot != null) {
                    viewProfileName.setText(documentSnapshot.getString("Nome do Usuario:"));
                }
            }
        });
    }

    private void IniciarComponentes() {
        btImageToProfile = findViewById(R.id.addImageToProfile);
        addProfileImg = findViewById(R.id.addProfileImg);
        viewProfileName = findViewById(R.id.viewProfileName);
        botaoVoltarPagina = findViewById(R.id.botaoVoltarPagina);
    }
}
