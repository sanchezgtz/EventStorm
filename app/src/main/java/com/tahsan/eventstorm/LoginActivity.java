package com.tahsan.eventstorm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.tahsan.eventstorm.fragmentos.LoginFragment;
import com.tahsan.eventstorm.fragmentos.RegistrarFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LoginActivity extends AppCompatActivity {

    private Fragment fragment;
    private BottomNavigationView bottomNavigationView;
    private FragmentManager fragmentManager;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fragmentManager = getSupportFragmentManager();
        bottomNavigationView = findViewById(R.id.nv_login);

        fragment = new LoginFragment();
        remplaceFragment(fragment);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener(){

                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.navigation_login:
                                fragment = new LoginFragment();
                                break;
                            case R.id.navigation_registrar:
                                fragment = new RegistrarFragment();
                                break;
                        }
                        remplaceFragment(fragment);
                        return true;
                    }
                });
    }

    private void remplaceFragment(Fragment fragment) {
        final  FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment).commit();
    }
}
