package com.example.saaplication.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.saaplication.R;
import com.example.saaplication.databinding.ActivityPaginainicialBinding;
import com.google.firebase.database.FirebaseDatabase;

public class PaginaInicial extends AppCompatActivity {

    ActivityPaginainicialBinding binding;

    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paginainicial);
        getWindow().setStatusBarColor(Color.argb(250, 223, 116, 1));
        binding = ActivityPaginainicialBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ReplaceFragment(new HomePage());

        firebaseDatabase = FirebaseDatabase.getInstance().getReference().getDatabase();


        /*-------------------Fragments Config-----------------*/
        binding.bottomNavegationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.homePage:
                    ReplaceFragment(new HomePage());
                    break;
                case R.id.photoPage:
                    Intent intentAddPostPage = new Intent(this, AddPostActivity.class);
                    startActivity(intentAddPostPage);
                    break;
                case R.id.perfilPage:
                    Intent intentProfilePage = new Intent(this, ProfileScrenPage1.class);
                    startActivity(intentProfilePage);
                    break;
            }
            return true;
        });
    }



    private  void  ReplaceFragment(HomePage fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_container,fragment);
        fragmentTransaction.commit();
    }

}






