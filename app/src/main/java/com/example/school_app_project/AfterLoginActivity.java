package com.example.school_app_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.school_app_project.Fragments.AccountFragment;
import com.example.school_app_project.Fragments.HomeFragment;
import com.example.school_app_project.Fragments.NotificationFragment;
import com.example.school_app_project.Fragments.SearchFragment;
import com.example.school_app_project.databinding.ActivityAfterLoginBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class AfterLoginActivity extends AppCompatActivity {
    ActivityAfterLoginBinding binding;
    FragmentTransaction transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAfterLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout,new HomeFragment());
        transaction.commit();

        getSupportActionBar().hide();

        binding.bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                transaction = getSupportFragmentManager().beginTransaction();
                switch(item.getItemId()) {
                    case R.id.search:
                        transaction.replace(R.id.frameLayout, new SearchFragment());
                        break;
                    case R.id.home:
                        transaction.replace(R.id.frameLayout, new HomeFragment());
                        break;
                    case R.id.notification:
                        transaction.replace(R.id.frameLayout,new NotificationFragment());
                        break;
                    case R.id.account:
                        transaction.replace(R.id.frameLayout,new AccountFragment());
                        break;
                }
                transaction.commit();
                return true;
            }
        });
    }
}