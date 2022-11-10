package com.example.saaplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.UUID;

public class ProfileScrenPage1 extends AppCompatActivity {


    private TextView viewProfileName;
    private ImageView addProfileImg;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    FirebaseFirestore firestoreBancoDeDados = FirebaseFirestore.getInstance();
    String usuarioId;
    ImageButton botaoVoltarPagina;
    private  Uri uri_imagem;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_scren_page);
        IniciarComponentes();

        FirebaseStorage storage = FirebaseStorage.getInstance();




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
//            uploadPicture();
        }
    }


    //----------------------------------------------Fazer Upload das Imagens no Storage--------------------------------------------------------------


//    private void uploadPicture(){
//
//
//    }




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
    }


}
