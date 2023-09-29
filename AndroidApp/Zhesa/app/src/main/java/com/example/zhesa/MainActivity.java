package com.example.zhesa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.*;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.annotation.NonNull;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottom_nav_view;
    int fav_id = R.id.favorite;
    int search_id = R.id.search;
    int about_id = R.id.about;
    AboutFragment aboutFragment = new AboutFragment();
    FavoriteFragment favoriteFragment = new FavoriteFragment();
    SearchFragment searchFragment = new SearchFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottom_nav_view = findViewById(R.id.bottomNavigationView);
        bottom_nav_view.setOnNavigationItemSelectedListener(MainActivity.this);
        bottom_nav_view.setSelectedItemId(R.id.favorite);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item){
        int itemId = item.getItemId();

        if (itemId == fav_id) {
            getSupportFragmentManager().beginTransaction().replace(R.id.contentFrag, favoriteFragment).commit();
            return true;
        } else if (itemId == R.id.search) {
            getSupportFragmentManager().beginTransaction().replace(R.id.contentFrag, searchFragment).commit();
            return true;
        } else if (itemId == R.id.about) {
            getSupportFragmentManager().beginTransaction().replace(R.id.contentFrag, aboutFragment).commit();
            return true;
        }

        return false;
    }
}