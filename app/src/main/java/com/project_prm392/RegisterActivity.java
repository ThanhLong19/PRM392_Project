package com.project_prm392;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project_prm392.Entity.User;
import com.project_prm392.RoomDB.RoomDB;
import com.project_prm392.RoomDB.UserDAO;

public class RegisterActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText, retypePasswordEditText, emailEditText, phoneEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        retypePasswordEditText = findViewById(R.id.retypePasswordEditText);
        emailEditText = findViewById(R.id.emailEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        Button registerButton = findViewById(R.id.registerButton);
        Button backButton = findViewById(R.id.backButton);

        registerButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            String retypePassword = retypePasswordEditText.getText().toString();
            String email = emailEditText.getText().toString();
            String phone = phoneEditText.getText().toString();

            if (username.isEmpty() || password.isEmpty() || retypePassword.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                Toast.makeText(RegisterActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            } else if (!password.equals(retypePassword)) {
                Toast.makeText(RegisterActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            } else {
                RoomDB db = RoomDB.getInstance(getApplicationContext());
                UserDAO userDAO = db.userDAO();
                new Thread(() -> {
                    User u = new User(username, password, email, phone, 2);
                    userDAO.insertUser(u);
                    finish();
                }).start();
                Toast.makeText(getApplicationContext(),"Register successfully.",Toast.LENGTH_SHORT).show();
            }
        });

        backButton.setOnClickListener(v -> {
            onBackPressed(); // Trigger the back button functionality
        });
    }
}