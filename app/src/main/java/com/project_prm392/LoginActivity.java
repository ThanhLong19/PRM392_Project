package com.project_prm392;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.project_prm392.Entity.User;
import com.project_prm392.RoomDB.RoomDB;
import com.project_prm392.RoomDB.UserDAO;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        Button loginButton = findViewById(R.id.loginButton);
        TextView forgotPasswordTextView = findViewById(R.id.forgotPasswordTextView);
        TextView registerTextView = findViewById(R.id.registerTextView);

        loginButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            // TODO: Add your login logic here

            if (username.isEmpty()) {
                usernameEditText.setError("Username cannot be empty");
            } else if (password.isEmpty()) {
                passwordEditText.setError("Password cannot be empty");
            } else {
                RoomDB db = RoomDB.getInstance(getApplicationContext());
                UserDAO userDAO = db.userDAO();
                new Thread(() -> {
                    User u = userDAO.getAccountByUsernameAndPassword(username, password);
                    if (u == null) {
                        runOnUiThread(() -> Toast.makeText(getApplicationContext(), "Username or password invalid.", Toast.LENGTH_SHORT).show());
                    } else {
                        SessionManager sessionManager = new SessionManager(getApplicationContext());
                        sessionManager.setLoggedIn(true);
                        Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(i);
                    }
                }).start();
            }
        });

        forgotPasswordTextView.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
        });

        registerTextView.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });
    }
}
