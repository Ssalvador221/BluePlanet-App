package com.example.saaplication.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saaplication.R;

public class Login_register_screen extends AppCompatActivity {


    TextView regislink;

    Animation rotateAnimation;
    ImageView  imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_registro_screen);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        imageView = (ImageView) findViewById(R.id.planetAnimation);

        rotateAnimation();

//Animation Button Login_register_screen Screen
        Button login1 = findViewById(R.id.loginScreenButton);

        login1.setAlpha(0f);

        login1.animate().alpha(1f).setDuration(1500);

        login1 = findViewById(R.id.loginScreenButton);

        login1.setAlpha(0f);

        login1.setTranslationY(50);

        login1.animate().alpha(1f).translationYBy(-50).setDuration(1500);


        RelativeLayout relativeLayout = findViewById(R.id.login_and_registro);

        AnimationDrawable animationDrawable = (AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();




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
               Intent intent = new Intent(Login_register_screen.this, RegistroScreen.class);
               startActivity(intent);
               Toast.makeText(Login_register_screen.this, "Bem-Vindo a Tela de Registro",Toast.LENGTH_LONG).show();
           }
       });

       regislink.setPaintFlags(regislink.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);


    }

    private void rotateAnimation() {
        rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotato_anim);
        imageView.startAnimation(rotateAnimation);
    }
}