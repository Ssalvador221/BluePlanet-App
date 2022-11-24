package com.example.saaplication.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.content.Intent;
import androidx.core.view.WindowCompat;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.saaplication.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import java.util.UUID;

public class ProfileScrenPage1 extends AppCompatActivity {


    private TextView viewProfileName;
    private ImageView addProfileImg;
    FirebaseFirestore firestoreBancoDeDados = FirebaseFirestore.getInstance();
    FirebaseStorage storage = FirebaseStorage.getInstance();
    FirebaseUser userId;
    FirebaseAuth mAuth;
    String usuarioId;
    ImageButton botaoVoltarPagina;
    private Uri uri_imagem;
    Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_scren_page);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        IniciarComponentes();

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);

        FirebaseStorage storage = FirebaseStorage.getInstance();
        mAuth = FirebaseAuth.getInstance();
        userId = mAuth.getCurrentUser();

        botaoVoltarPagina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileScrenPage1.this, PaginaInicial.class));
            }
        });




        //----------------------------------------------Botão para abrir a galeria e selecionar a foto--------------------------------------------------------------

        addProfileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePicture();
            }
        });
    }


    private void choosePicture() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }


    //----------------------------------------------Selecionar foto e colocar como foto de Perfil--------------------------------------------------------------
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode==RESULT_OK && data !=null && data.getData() !=null){
            uri_imagem = data.getData();
            addProfileImg.setImageURI(uri_imagem);
            uploadPicture();

        }
    }


    //----------------------------------------------Fazer Upload das Imagens no Storage--------------------------------------------------------------


     private void uploadPicture() {
      String filename = UUID.randomUUID().toString();
      final StorageReference ref = FirebaseStorage.getInstance().getReference("/images/" + filename);
      ref.putFile(uri_imagem)
              .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                  @Override
                  public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                      ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                          @Override
                          public void onSuccess(Uri uri) {
                              Log.i("Imagem salva no banco", uri.toString());
                          }
                      });
                  }
              })
              .addOnFailureListener(new OnFailureListener() {
                  @Override
                  public void onFailure(@NonNull Exception e) {
                      Log.e("Deu ruim", e.getMessage(), e);
                  }
              });
      }




    //----------------------------------------------Recuperar Nome de Usuario--------------------------------------------------------------

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
        addProfileImg = findViewById(R.id.addProfileImg);
        viewProfileName = findViewById(R.id.viewProfileName);
        botaoVoltarPagina = findViewById(R.id.botaoVoltarPagina);

        Glide.with(ProfileScrenPage1.this).load(userId.getPhotoUrl()).into(addProfileImg);

    }


}
