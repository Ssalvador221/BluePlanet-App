package com.example.saaplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class LoginScreen extends AppCompatActivity {

    private  EditText box_email, senhabox;
    private Button btLogin;
    //private ProgressBar progressBar;
    String[] mensagem = {"Preencha todos os Campos!", "Login efetuado com sucesso!"};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        IniciarComponentes();


        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = box_email.getText().toString();
                String senha  = senhabox.getText().toString();

                if (email.isEmpty() || senha.isEmpty()){

                    Snackbar snackbar = Snackbar.make(view, mensagem[0], BaseTransientBottomBar.LENGTH_SHORT);
                    snackbar.getView().setBackgroundColor(Color.parseColor("#ffffff"));
                    snackbar.setTextColor(Color.parseColor("#000000"));
                    snackbar.show();
                }
            }
        });

    }


    private void IniciarComponentes() {
        box_email = findViewById(R.id.box_email);
        senhabox = findViewById(R.id.senhabox);
        btLogin = findViewById(R.id.btLogin);
    }
}