package com.example.saaplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Login_register_screen extends AppCompatActivity {


    TextView regislink;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_registro_screen);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

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