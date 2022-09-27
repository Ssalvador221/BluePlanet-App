
package com.example.saaplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.google.firebase.auth.FirebaseAuth;

public class LoginScreen extends AppCompatActivity {

    private EditText etEmail;
    private EditText etSenha;
    private Button btCadastrar;
    private ProgessBar progessBar;
    private FirebaseAuth mAuth;
    String[] mensagens = {"Preencha todos os campos","Login efetuado com sucesso"}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login_screen);

        getSupportActionBar().hide();
        IniciarComponentes();
    }

    btCadastrar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String email = etEmail.getText().toString();
            String senha = etSenha.getText().toString();

            if (email.isEmpty() || senha.isEmpty()) {
                Snackbar snackbar = Snackbar.make(v, mensagens[0], Snackbar.LENGHT_SHORT);
                snackbar.setBackgroundTint("#ffa500");
                snackbar.setTextColor("#ffffff");
                snackbar.show();
             }else{
                AutenticarUsuario()
            }
        });
    }

    private void AutenticarUsuario(){

        String email = etEmail.getText().toString();
        String senha = etSenha.getText().toString();

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,senha).addOncompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    progessBar.setVisibility(View.VISIBLE);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Home();
                        }
                    }, 5000)
                }
            }
        });
    }

    private void Home(){
        Intent intent = new Intent(RegistroScreen.this,Home.class);
        startActivity(intent);
        finish();
    }

    private void IniciarComponentes(){
        etEmail = findViewById(R.id.etEmail);
        etSenha = findViewById(R.id.etSenha);
        btCadastrar = findViewById(R.id.btcadastrar)
        progessBar = findViewById(R.id.progressbar)
    }
}
