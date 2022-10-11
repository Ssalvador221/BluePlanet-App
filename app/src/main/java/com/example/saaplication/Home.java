package com.example.saaplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Home extends AppCompatActivity {

    private Button login1;
    TextView regislink;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);


        Button login1 = findViewById(R.id.login1);

        //Animation Button Home Screen
        login1.setAlpha(0f);

        login1.animate().alpha(1f).setDuration(1500);

        login1 = findViewById(R.id.login1);

        login1.setAlpha(0f);

        login1.setTranslationY(50);

        login1.animate().alpha(1f).translationYBy(-50).setDuration(1500);



        Button yourButton = (Button) findViewById(R.id.login1);

        yourButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(Home.this, PaginaInicial.class));
                Toast.makeText(Home.this, "Bem-Vindo a Tela de Login",Toast.LENGTH_LONG).show();
            }
        });



        //Text With Link to Register Screen
       regislink = (TextView) findViewById(R.id.regislink);
       regislink.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(Home.this,RegistroScreen.class);
               ActivityOptionsCompat activityCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), R.drawable.fade_in_animacao, R.drawable.fade_right_animacao);
               startActivity(intent);
               Toast.makeText(Home.this, "Bem-Vindo a Tela de Registro",Toast.LENGTH_LONG).show();
           }
       });

       regislink.setPaintFlags(regislink.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);


    }
}