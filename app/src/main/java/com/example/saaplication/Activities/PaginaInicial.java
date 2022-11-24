package com.example.saaplication.Activities;


import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import com.example.saaplication.R;

public class PaginaInicial extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paginainicial);
        getWindow().setStatusBarColor(Color.argb(250, 223, 116, 1));





    }
}
