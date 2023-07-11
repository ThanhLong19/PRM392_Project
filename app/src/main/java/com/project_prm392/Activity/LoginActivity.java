package com.project_prm392.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.project_prm392.DatabaseHelper;
import com.project_prm392.R;
import com.project_prm392.entity.User;
import com.google.android.material.tabs.TabLayout;

public class LoginActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager2 viewPager;
    EditText emailSi, passwordSi; // edit text of sing in
    Button login;
    EditText username, address, email, password, rePassword, phone; // edit text of register
    Button signup;
    TextView forgotPw;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DatabaseHelper(this);
        setContentView(R.layout.activity_login);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        tabLayout.addTab(tabLayout.newTab().setText("Login"));
        tabLayout.addTab(tabLayout.newTab().setText("Signup"));
        tabLayout.setTabGravity(tabLayout.GRAVITY_FILL);

        FragmentManager fm = getSupportFragmentManager();
        LoginAdapter adapter = new LoginAdapter(fm, getLifecycle());
        viewPager.setAdapter(adapter);


        // connect Tab Layout to adapter
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                login = (Button) findViewById(R.id.loginBt);
                signup = (Button) findViewById(R.id.signUpBt);
                viewPager.setCurrentItem(tab.getPosition());
                Log.e("Selected_Page", String.valueOf(tab.getPosition()));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

//         change tab when swiping
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
    }

}
//public class LoginActivity extends AppCompatActivity {
//    EditText email, password;
//    Button login;
//    TextView register;
//    DatabaseHelper db;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//        email = findViewById(R.id.email);
//        password = findViewById(R.id.password);
//        login = findViewById(R.id.login);
//        register = findViewById(R.id.register);
//        db = new DatabaseHelper(this);
//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String Email = email.getText().toString();
//                String passWord = password.getText().toString();
//                if(TextUtils.isEmpty(Email) || TextUtils.isEmpty(passWord)){
//                    Toast.makeText(LoginActivity.this, "All fields required!", Toast.LENGTH_SHORT).show();
//                } else{
//                    Boolean checkUP = db.checkAccountForLogin(Email, passWord);
//                    if(checkUP == true){
//                        Toast.makeText(LoginActivity.this, "Login successfully!", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
//                        startActivity(intent);
//                    } else{
//                        Toast.makeText(LoginActivity.this, "Login failed!", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//            }
//        });
//        register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
//                startActivity(intent);
//            }
//        });
//
//    }
//}