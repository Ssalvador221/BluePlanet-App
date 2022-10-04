package com.example.saaplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginScreen extends AppCompatActivity {

    private EditText box_email,senhabox;
    private Button loginBt;
    private TextView esqueceubt;
    private ProgressBar progressBar;
    String[] mensagens = {"Preencha todos os campos","Login realizado com sucesso"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        IniciarComponentes();

        loginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = box_email.getText().toString();
                String senha = senhabox.getText().toString();

                if (email.isEmpty() || senha.isEmpty()){
                    Snackbar snackbar = Snackbar.make(view ,mensagens[0],Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }else {
                    AutenticarUsuario(view);
                }
            }
        });
    }

    private void AutenticarUsuario(View view ) {

        String email = box_email.getText().toString();
        String senha = senhabox.getText().toString();

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    progressBar.setVisibility(View.VISIBLE);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Home();
                        }
                    }, 5000);
                } else {
                    String erro;

                    try {
                        throw task.getException();
                    } catch (Exception e) {
                        erro = "Erro ao logar usuário";
                    }

                    Snackbar snackbar = Snackbar.make(view, mensagens[0], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }
            }
        });
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        FirebaseUser usuarioAtual = FirebaseAuth.getInstance().getCurrentUser();
//
//        if (usuarioAtual != null) {
//            Home();
//        }
//    }

    private void Home(){
        Intent intent = new Intent(LoginScreen.this, PaginaInicial.class);
        startActivity(intent);
        finish();
    }

    private void IniciarComponentes() {
        box_email = findViewById(R.id.box_email);
        senhabox = findViewById(R.id.senhabox);
        loginBt = findViewById(R.id.btLogin);
        esqueceubt = findViewById(R.id.redefenirpss);
    }
}


