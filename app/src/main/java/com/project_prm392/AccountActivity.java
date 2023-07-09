package com.project_prm392;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.project_prm392.Entity.User;
import com.project_prm392.RoomDB.RoomDB;
import com.project_prm392.RoomDB.UserDAO;

public class AccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        TextView usernameTextView = findViewById(R.id.usernameText);
        TextView emailTextView = findViewById(R.id.emailText);
        TextView phoneTextView = findViewById(R.id.phoneText);
        Button updateButton = findViewById(R.id.updateButton);
        Button changePasswordButton = findViewById(R.id.changePassword);

        RoomDB db = RoomDB.getInstance(getApplicationContext());
        UserDAO userDAO = db.userDAO();
        new Thread(() -> {
            Intent i = getIntent();
            User u = userDAO.getUserById(Integer.parseInt(i.getStringExtra("userID")));
            if (u != null) {
                usernameTextView.setText(u.getUserName());
                emailTextView.setText(u.getEmail());
                phoneTextView.setText(u.getPhoneNumber());
            }
        }).start();

        updateButton.setOnClickListener(v -> {
            startActivity(new Intent(AccountActivity.this, UpdateProfileActivity.class));
        });

        changePasswordButton.setOnClickListener(v -> {
            startActivity(new Intent(this, ChangePasswordActivity.class));
        });
    }
}
