package com.example.saaplication;



import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.saaplication.databinding.ActivityPaginainicialBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PaginaInicial extends AppCompatActivity {


    ActivityPaginainicialBinding binding;
    private DatabaseReference firebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paginainicial);
        binding = ActivityPaginainicialBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ReplaceFragment(new Home_page_Fragment());

        firebaseDatabase = FirebaseDatabase.getInstance().getReference();


        /*-------------------Fragments Config-----------------*/
        binding.bottomNavegationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.homePage:
                    ReplaceFragment(new Home_page_Fragment());
                    break;
                case R.id.searchPage:
                    ReplaceFragment(new SearchFragement());
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



    private  void  ReplaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutId,fragment);
        fragmentTransaction.commit();
    }

}