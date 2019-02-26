package com.yaskovskiy.testwork.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.yaskovskiy.testwork.R;

public class FavoritesActivity extends AppCompatActivity {




    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_adme:
                        Intent intent = new Intent(FavoritesActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_fav:
//                        Intent intent = new Intent(MainActivity.this, PostShowActivity)
                        break;
                }
                return false;
            }
        });
    }
}
