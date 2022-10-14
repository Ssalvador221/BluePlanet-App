package com.example.saaplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.widget.Switch;

import com.example.saaplication.databinding.ActivityMainBinding;
import com.example.saaplication.databinding.ActivityPaginainicialBinding;

public class PaginaInicial extends AppCompatActivity {


    ActivityPaginainicialBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPaginainicialBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ReplaceFragment(new Home_page_Fragment());


        binding.bottomNavegationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.homePage:
                    ReplaceFragment(new Home_page_Fragment());
                    break;
                case R.id.searchPage:
                    ReplaceFragment(new SearchFragment());
                    break;
                case R.id.photoPage:
                    ReplaceFragment(new Add_Foto_Fragment());
                    break;
                case R.id.perfilPage:
                    ReplaceFragment(new Perfil_Fragment());
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