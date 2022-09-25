package com.example.saaplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
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

import org.w3c.dom.Text;

import java.nio.charset.StandardCharsets;

import javax.annotation.meta.When;

public class RegistroScreen extends AppCompatActivity {

    private ActivityRegistroScrenBinding binding;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        binding = ActivityRegistroScrenBinding.inflate(getLayoutInflater());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registro_scren);

        setContentView(binding.getRoot());


        binding.btRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText nome = binding.nameBox;
                EditText email = binding.emailText;
                EditText senha = binding.senhaText;

                auth = FirebaseAuth.getInstance();

                if (TextUtils.isEmpty(nome.getText().toString()) || TextUtils.isEmpty(email.getText().toString()) || TextUtils.isEmpty(senha.getText().toString())){
                    Snackbar mySnackbar = Snackbar.make(v, "Preencha todos os Campos!", Snackbar.LENGTH_SHORT);
                    mySnackbar.getView().setBackgroundColor(Color.parseColor("#ffa500"));
                    mySnackbar.setTextColor(Color.parseColor("#ffffff"));
                    mySnackbar.show();
                }else{
                        auth.createUserWithEmailAndPassword(email.getText().toString(), senha.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Snackbar mySnackbar = Snackbar.make(v, "Sucesso ao Cadastrar o Usuário!", Snackbar.LENGTH_SHORT);
                                    mySnackbar.getView().setBackgroundColor(Color.parseColor("#ffa500"));
                                    mySnackbar.setTextColor(Color.parseColor("#ffffff"));
                                    mySnackbar.show();
                                    binding.nameBox.setText("");
                                    binding.emailText.setText("");
                                    binding.senhaText.setText("");
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                               switch (e){
                                   case 1 : {
                                      new  FirebaseAuthWeakPasswordException("", "","");
                                   }


                               }


                            }
                        });
                }
                 }
             });
        }
    }



