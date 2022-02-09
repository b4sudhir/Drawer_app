package com.iot4all.in.drawer_app;
import static com.iot4all.in.drawer_app.R.*;
import static com.iot4all.in.drawer_app.R.layout.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);
        drawerLayout=findViewById(id.drawer);
        toolbar=findViewById(id.toolBar);
        setSupportActionBar(toolbar);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar, string.open, string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView=findViewById(id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id=menuItem.getItemId();
                Fragment fragment=null;
                switch (id)
                {
                    case R.id.main_page:
                        FragmentManager fragmentManager=getSupportFragmentManager();
                        fragmentManager.popBackStack();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.add_device:
                        fragment=new BasketFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.setting:
                        //fragment=new FavFragment();
                        //loadFragment(fragment);
                        break;
                    case R.id.log_file:
                        //fragment=new PromoCodeFragment();
                        //loadFragment(fragment);
                        break;
                    case R.id.support:
                        //fragment=new SettingFragment();
                        //loadFragment(fragment);
                        break;
                    case R.id.exit:
                        Toast.makeText(getApplicationContext(), "Closing application...",   Toast.LENGTH_SHORT).show();
                        android.os.Process.killProcess(android.os.Process.myPid());
                        finish();

                        break;
                    default:
                        return true;
                }
                return true;
            }
        });
    }
    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(id.frame,fragment).commit();
        drawerLayout.closeDrawer(GravityCompat.START);
        fragmentTransaction.addToBackStack(null);
    }
}