package com.example.saaplication;


import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.PopupWindow;

import com.example.saaplication.databinding.ActivityPaginainicialBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PaginaInicial extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    ActivityPaginainicialBinding binding;

    DrawerLayout drawerLayout;
    NavigationView navigationMenuView;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paginainicial);
        binding = ActivityPaginainicialBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ReplaceFragment(new Home_page_Fragment());


        drawerLayout = findViewById(R.id.drawer_Layout);
        navigationMenuView = findViewById(R.id.navegation_menu);
        toolbar = findViewById(R.id.toolbar);

        /*-------------------Toolbar-----------------*/
        setSupportActionBar(toolbar);

        //

        /*-------------------Navigation Drawer Menu-----------------*/
        navigationMenuView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationMenuView.setNavigationItemSelectedListener(this);








        /*-------------------Fragments Config-----------------*/
        binding.bottomNavegationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.homePage:
                    setTitle("Bem-Vindo a Página Inicial");
                    ReplaceFragment(new Home_page_Fragment());
                    navigationMenuView.setCheckedItem(R.id.homeMenuPage);
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
                    ReplaceFragment(new Perfil_Fragment());
                    navigationMenuView.setCheckedItem(R.id.perfilPage1);
                    break;
            }
            return true;
        });
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START );
        }else{
            super.onBackPressed();
        }
    }

    private  void  ReplaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutId,fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.homeMenuPage:
                ReplaceFragment(new Home_page_Fragment());
                binding.bottomNavegationView.setSelectedItemId(R.id.homePage);
                break;
            case R.id.newsNotificationPage:
                Intent intent = new Intent(PaginaInicial.this, NewsPage.class);
                startActivity(intent);
                break;
            case R.id.perfilPage1:
                ReplaceFragment(new Perfil_Fragment());
                binding.bottomNavegationView.setSelectedItemId(R.id.perfilPage);
                break;
            case R.id.searchPage:
                ReplaceFragment(new SearchFragement());
                break;
            case R.id.logoutAccount:
                FirebaseAuth.getInstance().signOut();
                Intent signOut = new Intent(PaginaInicial.this, LoginScreen.class);
                startActivity(signOut);
                finish();


        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}