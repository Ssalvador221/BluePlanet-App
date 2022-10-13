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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

public class LoginScreen extends AppCompatActivity {

    private EditText edit_Text_Email, edit_Text_Senha;
    private Button buttonLogin;
    private ProgressBar progressBar;
    String[] mensagens = {"Preencha todos os campos"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_screen);

        IniciarComponentes();


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edit_Text_Email.getText().toString();
                String senha = edit_Text_Senha.getText().toString();

                if (email.isEmpty() || senha.isEmpty()) {
                    Snackbar mysnack = Snackbar.make(v, "Preencha todos os campos Solicitados!", Snackbar.LENGTH_SHORT);
                    mysnack.getView().setBackgroundColor(Color.parseColor("#ffffff"));
                    mysnack.setTextColor(Color.parseColor("#000000"));
                    mysnack.show();
                } else {
                    AutenticarUsuario(v);
                }

            }
        });
    }


        private void AutenticarUsuario (View view) {
            String email = edit_Text_Email.getText().toString();
            String senha = edit_Text_Senha.getText().toString();

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        progressBar.setVisibility(View.VISIBLE);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                TelaPrincipal();
                            }
                        },3000);
                    } else{
                        String erro;

                        try {
                            throw task.getException();
                        } catch (FirebaseAuthInvalidCredentialsException e) {
                            erro = "Email Inválido!";
                        } catch (Exception e) {
                            erro = "Erro ao fazer Login!";
                        }

                        Snackbar snackbar = Snackbar.make(view, erro, Snackbar.LENGTH_SHORT);
                        snackbar.getView().setBackgroundColor(Color.parseColor("#ffffff"));
                        snackbar.setTextColor(Color.parseColor("#000000"));
                        snackbar.show();
                    }
                }
            });

        }



       private void TelaPrincipal(){
        Intent intent = new Intent(LoginScreen.this, LoginScreen1.class);
        startActivity(intent);
        finish();
       }

        private void IniciarComponentes () {
            edit_Text_Email = findViewById(R.id.email_login_textedit);
            edit_Text_Senha = findViewById(R.id.senha_login_textedit);
            buttonLogin = findViewById(R.id.button_login);
            progressBar = findViewById(R.id.progressBar);
        }
    }


