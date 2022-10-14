package com.example.saaplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Login_register_screen extends AppCompatActivity {


    TextView regislink;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_registro_screen);

//Animation Button Login_register_screen Screen
        Button login1 = findViewById(R.id.loginScreenButton);


        login1.setAlpha(0f);

        login1.animate().alpha(1f).setDuration(1500);

        login1 = findViewById(R.id.loginScreenButton);

        login1.setAlpha(0f);

        login1.setTranslationY(50);

        login1.animate().alpha(1f).translationYBy(-50).setDuration(1500);



        Button loginScreenButton = (Button) findViewById(R.id.loginScreenButton);

        loginScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_register_screen.this, LoginScreen.class);
                Log.d("Que PORRAAAA", "onClick: Botao do krl");
                startActivity(intent);
            }
        });



        //Text With Link to Register Screen, Animation slide up.


       regislink = (TextView) findViewById(R.id.link_registroScreen);

       regislink.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(Login_register_screen.this,RegistroScreen.class);
               startActivity(intent);
               Toast.makeText(Login_register_screen.this, "Bem-Vindo a Tela de Registro",Toast.LENGTH_LONG).show();
           }
       });

       regislink.setPaintFlags(regislink.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);


    }
}