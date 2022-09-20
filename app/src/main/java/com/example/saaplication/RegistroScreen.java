package com.example.saaplication;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Size;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.saaplication.databinding.ActivityRegistroScrenBinding;
import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

public class RegistroScreen extends AppCompatActivity {

    private ActivityRegistroScrenBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistroScrenBinding.inflate(getLayoutInflater());
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(binding.getRoot());


        binding.btRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText nome = binding.nameBox;
                EditText email = binding.emailText;
                EditText senha = binding.senhaText;

                if (TextUtils.isEmpty(nome.getText().toString()) || TextUtils.isEmpty(email.getText().toString()) || TextUtils.isEmpty(senha.getText().toString())){
                    Snackbar mySnackbar = Snackbar.make(v, "Preencha todos os Campos!", Snackbar.LENGTH_SHORT);
                    mySnackbar.getView().setBackgroundColor(Color.parseColor("#ffa500"));
                    mySnackbar.setTextColor(Color.parseColor("#ffffff"));
                    mySnackbar.show();
                }else{

                }
            }
        });


    }
}