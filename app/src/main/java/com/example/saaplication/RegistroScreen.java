package com.example.saaplication;


import android.graphics.Color;

import android.os.Bundle;


import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class RegistroScreen extends AppCompatActivity {

    private EditText namebox, emaibox, senhabox;
    private Button btcadastrar;
    String erro;
    String UsuarioId;
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

        String nome = namebox.getText().toString();
        String email = emaibox.getText().toString();
        String senha = senhabox.getText().toString();

        auth.createUserWithEmailAndPassword(email,senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    SalvadorDados();

                    Snackbar mysnack = Snackbar.make(view, "Conta Criada com Sucesso!", Snackbar.LENGTH_SHORT);
                    mysnack.getView().setBackgroundColor(Color.parseColor("#ffffff"));
                    mysnack.setTextColor(Color.parseColor("#000000"));
                    mysnack.show();

                    namebox.setText("");
                    emaibox.setText("");
                    senhabox.setText("");
                }else

                try {
                    throw task.getException();
                }
                catch (FirebaseAuthWeakPasswordException e){
                     erro = "Digite uma senha com no mínimo 6 caracteres!";
                }catch (FirebaseAuthUserCollisionException e){
                     erro = "Email já utilizado, Utilize outro Email.";
                } catch (FirebaseAuthInvalidCredentialsException e){
                    erro = "Email Inválido!";
                }
                catch (Exception e){
                    erro = "Erro ao cadastrar Usuário!";
                }
                Snackbar mysnack = Snackbar.make(view, erro, Snackbar.LENGTH_SHORT);
                mysnack.getView().setBackgroundColor(Color.parseColor("#ffa500"));
                mysnack.setTextColor(Color.parseColor("#ffffff"));
                mysnack.show();
            }
        });
    }

    private void SalvadorDados() {

        String nome = findViewById(R.id.name_box).toString();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> usuario = new HashMap<>();
        usuario.put("Nome", nome);

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

    private void IniciarComponentes() {

        namebox = findViewById(R.id.name_box);
        emaibox = findViewById(R.id.email_text);
        senhabox = findViewById(R.id.senha_text);
        btcadastrar = findViewById(R.id.bt_registrar);

    }
}
