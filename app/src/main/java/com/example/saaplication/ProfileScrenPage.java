package com.example.saaplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class ProfileScrenPage extends AppCompatActivity {


    private TextView viewProfileName;
    FirebaseFirestore firestoreBancoDeDados = FirebaseFirestore.getInstance();
    String usuarioId;
    ImageButton botaoVoltarPagina;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_scren_page);
        IniciarComponentes();


        botaoVoltarPagina = findViewById(R.id.botaoVoltarPagina);

        botaoVoltarPagina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileScrenPage.this, PaginaInicial.class));
            }
        });


    }


    @Override
    protected void onStart() {
        super.onStart();

        usuarioId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference = firestoreBancoDeDados.collection("Usuario").document(usuarioId);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                     if (documentSnapshot != null){
                         viewProfileName.setText(documentSnapshot.getString("Nome do Usuario:"));
                     }
            }
        });
    }

    private void IniciarComponentes(){

        viewProfileName = findViewById(R.id.viewProfileName);


    }



}