package com.example.animepeak.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.animepeak.Fragments.HomeFragment;
import com.example.animepeak.Fragments.SearchFragment;
import com.example.animepeak.Fragments.SettingsFragment;
import com.example.animepeak.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class MainActivity extends AppCompatActivity {
    public static BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment = new HomeFragment();
    SearchFragment searchFragment = new SearchFragment();
    SettingsFragment settingsFragment = new SettingsFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        FragmentTransaction tr = getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, homeFragment, "HOME_FRAGMENT_TAG");
        tr.addToBackStack(null);
        tr.commit();


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, homeFragment, "HOME_FRAGMENT_TAG")
                                .commit();
//                        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
                        return true;
                    case R.id.search:
                        FragmentTransaction tr = getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, searchFragment, "SEARCH_FRAGMENT_TAG");
                        tr.addToBackStack(null);
                        tr.commit();

//                        getSupportFragmentManager().beginTransaction().replace(R.id.container,searchFragment).commit();
                        return true;
                    case R.id.settings:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, settingsFragment).commit();
                        return true;

                }
                return false;
            }
        });
    }

}