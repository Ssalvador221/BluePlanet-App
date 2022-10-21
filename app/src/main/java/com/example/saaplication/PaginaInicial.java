package com.example.saaplication;


import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.saaplication.databinding.ActivityPaginainicialBinding;
import com.google.android.material.navigation.NavigationView;

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
        return true;
    }
}