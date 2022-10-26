package com.example.saaplication;


import android.content.Intent;
import android.graphics.Color;

import android.os.Bundle;


import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;


public class RegistroScreen extends AppCompatActivity {

    private EditText namebox, emailbox, senhabox;
    private Button btcadastrar;
    private ProgressBar progressBar;
    String erro;
    String UsuarioId;
    FirebaseAuth auth;
    ImageButton botaoVoltarPagina;


    @Override
    protected void onCreate(Bundle savedIntanceState) {
        super.onCreate(savedIntanceState);
        setContentView(R.layout.activity_registro_scren);

        IniciarComponentes();

        botaoVoltarPagina = findViewById(R.id.botaoVoltarPagina);

        botaoVoltarPagina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistroScreen.this, Login_register_screen.class));
                Toast.makeText(RegistroScreen.this, "Bem-Vindo a tela Inicial",Toast.LENGTH_LONG).show();
            }
        });


        btcadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = namebox.getText().toString();
                String email = emailbox.getText().toString();
                String senha = senhabox.getText().toString();

                if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                    Snackbar mysnack = Snackbar.make(view, "Preencha todos os campos Solicitados!", Snackbar.LENGTH_SHORT);
                    mysnack.getView().setBackgroundColor(Color.parseColor("#ffffff"));
                    mysnack.setTextColor(Color.parseColor("#000000"));
                    mysnack.show();
                } else {
                    cadastrarUsuario(view);
                }
            }
        });
    }

    private void cadastrarUsuario(View view) {

        auth = FirebaseAuth.getInstance();

        String email = emailbox.getText().toString();
        String senha = senhabox.getText().toString();
        String nome = namebox.getText().toString();


        auth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    SalvadorDados();

                    Snackbar mysnack1 = Snackbar.make(view, "Conta Criada com Sucesso!", Snackbar.LENGTH_SHORT);
                    mysnack1.getView().setBackgroundColor(Color.parseColor("#00ff37"));
                    mysnack1.setTextColor(Color.parseColor("#ffffff"));
                    mysnack1.show();

                    namebox.setText("");
                    emailbox.setText("");
                    senhabox.setText("");

                    if (task.isSuccessful()) {
                        progressBar.setVisibility(View.VISIBLE);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                TelaPrincipalApp();
                            }
                        }, 3000);
                    }

                } else {
                    try {
                        throw task.getException();
                    } catch (FirebaseAuthWeakPasswordException e) {
                        erro = "Digite uma senha com no mínimo 6 caracteres!";
                    } catch (FirebaseAuthUserCollisionException e) {
                        erro = "Email já utilizado, Utilize outro Email!";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        erro = "Email Inválido!";
                    } catch (Exception e) {
                        erro = "Erro ao cadastrar Usuário!";
                    }

                    Snackbar snackbar = Snackbar.make(view, erro, Snackbar.LENGTH_SHORT);
                    snackbar.getView().setBackgroundColor(Color.parseColor("#ffffff"));
                    snackbar.setTextColor(Color.parseColor("#000000"));
                    snackbar.show();
                }
            }
        });
    }


        private void SalvadorDados () {

            String nome = namebox.getText().toString();

            FirebaseFirestore db = FirebaseFirestore.getInstance();

            Map<String, Object> usuario = new HashMap<>();
            usuario.put("Nome do Usuario:", nome);

            UsuarioId = FirebaseAuth.getInstance().getCurrentUser().getUid();

            DocumentReference documentReference = db.collection("Usuario").document(UsuarioId);

            documentReference.set(usuario).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Log.d("db", "Sucesso ao Registrar o Usuário!");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("db", "onFailure: Error User criation!" + e.toString());
                }
            });
        }



        private void TelaPrincipalApp(){

            Intent intent = new Intent(RegistroScreen.this, LoginScreen.class);
            Toast.makeText(RegistroScreen.this, "Bem-Vindo a página Principal",Toast.LENGTH_LONG).show();
            startActivity(intent);
        }


        private void IniciarComponentes () {

            progressBar = findViewById(R.id.progressBar1);
            namebox = findViewById(R.id.nametextedit);
            emailbox = findViewById(R.id.emailtextedit);
            senhabox = findViewById(R.id.senhatextedit);
            btcadastrar = findViewById(R.id.bt_registrar);

        }
    }

