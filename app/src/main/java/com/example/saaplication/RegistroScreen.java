package com.example.saaplication;


import android.graphics.Color;

import android.os.Bundle;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class RegistroScreen extends AppCompatActivity {

    private EditText namebox, emaibox, senhabox;
    private Button btcadastrar;
    FirebaseAuth auth;
    String[] mensagens = {"Preencha todos os Campos solicitados!", "Usuário Criado com Sucesso!"};


    @Override
    protected void onCreate(Bundle savedIntanceState) {
        super.onCreate(savedIntanceState);
        setContentView(R.layout.activity_registro_scren);

        IniciarComponentes();

        btcadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = namebox.getText().toString();
                String email = emaibox.getText().toString();
                String senha = senhabox.getText().toString();

                if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                    Snackbar mysnack = Snackbar.make(view, mensagens[0], Snackbar.LENGTH_SHORT);
                    mysnack.getView().setBackgroundColor(Color.parseColor("#ffa500"));
                    mysnack.setTextColor(Color.parseColor("#ffffff"));
                    mysnack.show();
                } else {
                    cadastrarUsuario(view);
                }
            }
        });
    }

    private void cadastrarUsuario(View view) {

        auth = FirebaseAuth.getInstance();

        String email = emaibox.getText().toString();
        String senha = senhabox.getText().toString();

        auth.createUserWithEmailAndPassword(email,senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Snackbar mysnack = Snackbar.make(view, mensagens[0], Snackbar.LENGTH_SHORT);
                    mysnack.getView().setBackgroundColor(Color.parseColor("#ffa500"));
                    mysnack.setTextColor(Color.parseColor("#ffffff"));
                    mysnack.show();
                }

            }
        });
    }

    private void IniciarComponentes() {

        namebox = findViewById(R.id.name_box);
        emaibox = findViewById(R.id.email_text);
        senhabox = findViewById(R.id.senha_text);
        btcadastrar = findViewById(R.id.bt_registrar);

    }
}
