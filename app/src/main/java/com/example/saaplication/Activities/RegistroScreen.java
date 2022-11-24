package com.example.saaplication.Activities;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;


import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.WindowCompat;


import com.example.saaplication.R;
import com.example.saaplication.databinding.FragmentHomePageBinding;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;


public class RegistroScreen extends AppCompatActivity {

    private EditText namebox, emailbox, senhabox;
    private Button btcadastrar;
    ImageView botaoVoltarPagina;
    private ProgressBar progressBar;

    ImageView ImgUserPhoto;
    static int PReqCode = 1 ;
    static int REQUESCODE = 1 ;
    Uri pickedImgUri;

    String erro;
    String UsuarioId;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedIntanceState) {
        super.onCreate(savedIntanceState);
        setContentView(R.layout.activity_registro_scren);

        getWindow().setStatusBarColor(Color.TRANSPARENT);

        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);


        IniciarComponentes();

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


        botaoVoltarPagina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistroScreen.this, Login_register_screen.class));
            }
        });



    }


    private void updateUserInfo(final String nome, Uri pickedImgUri, final FirebaseUser currentUser) {

        StorageReference mStorage = FirebaseStorage.getInstance().getReference().child("foto_Perfil");
        final StorageReference imageFilePath = mStorage.child(pickedImgUri.getLastPathSegment());
        imageFilePath.putFile(pickedImgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {

            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                imageFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {


                        UserProfileChangeRequest profleUpdate = new UserProfileChangeRequest.Builder()
                                .setDisplayName(nome)
                                .setPhotoUri(uri)
                                .build();
                        currentUser.updateProfile(profleUpdate)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            updateUI();
                                        }
                                    }
                                });
                    }
                });
            }
        });
    }

    private void updateUI() {
        Intent homeActivity = new Intent(getApplicationContext(), FragmentHomePageBinding.class);
        startActivity(homeActivity);
        finish();
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
                    updateUserInfo( nome ,pickedImgUri, auth.getCurrentUser());

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
            botaoVoltarPagina = findViewById(R.id.backToMainActivity);

        }
    }

