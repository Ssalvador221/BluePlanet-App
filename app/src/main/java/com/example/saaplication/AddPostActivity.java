package com.example.saaplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class AddPostActivity extends AppCompatActivity {

    private ImageView imagemDaPublicacao;
    private ImageButton voltarPagina;
    private Uri uri_imagem;
    private Button selecionarImagem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);
        IniciarComponentes();


        voltarPagina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddPostActivity.this, PaginaInicial.class));
            }
        });


        //----------------------------------------------Botão para abrir a galeria e selecionar a foto--------------------------------------------------------------

        selecionarImagem.setOnClickListener(new View.OnClickListener() {
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
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            uri_imagem = data.getData();
            imagemDaPublicacao.setImageURI(uri_imagem);
//          uploadPicture();
        }
    }


    private void IniciarComponentes(){
        imagemDaPublicacao = findViewById(R.id.imagemDaPublicacao);
        voltarPagina = findViewById(R.id.voltarPagina);
        selecionarImagem = findViewById(R.id.selecionar_foto);


    }
}
