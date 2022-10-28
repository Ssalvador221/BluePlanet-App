package com.example.saaplication;



import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import com.example.saaplication.databinding.ActivityPaginainicialBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.ktx.Firebase;

public class PaginaInicial extends AppCompatActivity {


    ActivityPaginainicialBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paginainicial);
        binding = ActivityPaginainicialBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ReplaceFragment(new Home_page_Fragment());

        getWindow().setStatusBarColor(Color.TRANSPARENT);




        /*-------------------Fragments Config-----------------*/
        binding.bottomNavegationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.homePage:
                    setTitle("Bem-Vindo a Página Inicial");
                    ReplaceFragment(new Home_page_Fragment());
                    break;
                case R.id.searchPage:
                    setTitle("Busque por seus Amigos!");
                    ReplaceFragment(new SearchFragement());
                    break;
                case R.id.photoPage:
                    setTitle("publicar Postagem");
                    ReplaceFragment(new AddPost_Fragment());
                    break;
                case R.id.perfilPage:
                    setTitle("Perfil");
                    Intent intent = new Intent(this, ProfileScrenPage.class);
                    startActivity(intent);
                    break;
            }
            return true;
        });
    }



    private  void  ReplaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutId,fragment);
        fragmentTransaction.commit();
    }

}