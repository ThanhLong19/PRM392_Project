package com.project_prm392.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.project_prm392.R;
import com.project_prm392.entity.User;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final int FR_HOME = 0;
    private static final int FR_PROFILE = 1;
    private static final int FR_CHANGE_PW = 2;
    private int currentFr = FR_HOME;
    private TextView name, gmail, fr_name;
    private DrawerLayout drawerLayout;
    private  NavigationView navigationView;
    private User userTemp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        fr_name = findViewById(R.id.fr_name);

        User user = (User)getIntent().getSerializableExtra("user");
        View hView = navigationView.getHeaderView(0);
        name = hView.findViewById(R.id.name);
        gmail = hView.findViewById(R.id.gmail);
        name.setText(user.getUsername());
        gmail.setText(user.getEmail());

//        Log.e("User info homefr", user.toString());

        relaceFr(new HomeFragment());
        navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.nav_home){
            if(currentFr != FR_HOME){
                relaceFr(new HomeFragment());
                currentFr = FR_HOME;
            }
        }else if(id == R.id.nav_fav){


        }else if(id == R.id.nav_history){

        }else if(id == R.id.nav_profile){
            if(currentFr != FR_PROFILE){
                relaceFr(new ProfileFragment());
                navigationView.getMenu().findItem(R.id.nav_profile).setChecked(true);
                currentFr = FR_PROFILE;
                fr_name.setText("Profile");
            }
        }else if(id == R.id.nav_changePw){
            if(currentFr != FR_CHANGE_PW){
                relaceFr(new ChangePwFr());
                navigationView.getMenu().findItem(R.id.nav_profile).setChecked(true);
                currentFr = FR_PROFILE;
                fr_name.setText("Create new password");
            }
        }

        //close drawer
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else{
            super.onBackPressed();
        }
    }

    private void relaceFr (Fragment fragment){
        User user = (User)getIntent().getSerializableExtra("user");
        userTemp = user;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment);
        ft.commit();
    }

    public User getUser(){
        return userTemp;
    }
}