package com.example.saaplication;

import android.content.Intent;
import android.graphics.Color;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Size;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.saaplication.databinding.ActivityRegistroScrenBinding;
import com.example.saaplication.modelo.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.auth.User;

import org.checkerframework.checker.guieffect.qual.UI;
import org.w3c.dom.Text;

import java.nio.charset.StandardCharsets;

import javax.annotation.meta.When;

public class RegistroScreen extends AppCompatActivity {

    private EditText etNome;
    private EditText etEmail;
    private EditText etSenha;
    private Button btCadastrar;
    private FirebaseAuth mAuth;
    private Usuario u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_scren);
        etNome = findViewById(R.id.name_box);
        etEmail = findViewById(R.id.email_text);
        etSenha = findViewById(R.id.senha_text);
        btCadastrar = findViewById(R.id.bt_registrar);
        mAuth = FirebaseAuth.getInstance();

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recuperarDados();
                criarLogin();
            }
        });


    }

    private void criarLogin() {
        mAuth.createUserWithEmailAndPassword(u.getEmail(),u.getSenha())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();
                            u.setId(user.getUid());
                            u.salvarDados();
                        }else{
                            Snackbar mySnackbar = Snackbar.make(btCadastrar, "Preencha todos os Campos!", Snackbar.LENGTH_SHORT);
                            mySnackbar.getView().setBackgroundColor(Color.parseColor("#ffa500"));
                            mySnackbar.setTextColor(Color.parseColor("#ffffff"));
                            mySnackbar.show();
                        }
                    }
                });
    }



    private void recuperarDados() {
        if(etNome.getText().toString()==""||etEmail.getText().toString()==""||etSenha.getText().toString()==""){
            Toast.makeText(RegistroScreen.this,"Você deve preencher todos os Dados corretamente!",Toast.LENGTH_LONG);
        }else{
            u = new Usuario();
            u.setNome(etNome.getText().toString());
            u.setEmail(etEmail.getText().toString());
           u.setSenha(etSenha.getText().toString());
        }
    }
}



