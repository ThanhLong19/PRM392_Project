package com.project_prm392;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.project_prm392.Entity.User;
import com.project_prm392.RoomDB.RoomDB;
import com.project_prm392.RoomDB.UserDAO;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText emailEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        emailEditText = findViewById(R.id.emailEditText);
        Button resetButton = findViewById(R.id.resetButton);
        Button backButton = findViewById(R.id.backButton);

        resetButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString();
            try {
                if (isValidEmail(email)) {
                    // Generate a new password
                    String newPassword = generateNewPassword();
                    RoomDB db = RoomDB.getInstance(getApplicationContext());
                    UserDAO userDAO = db.userDAO();
                    new Thread(() -> {
                        User u = userDAO.getUserByEmail(email);
                        if (u != null) {
                            u.setPassword(newPassword);
                            userDAO.updateUser(u);
                        }
                    }).start();
                    sendPasswordToEmail(email, newPassword);
                } else {
                    Toast.makeText(ForgotPasswordActivity.this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        backButton.setOnClickListener(v -> {
            onBackPressed();
        });
    }

    private boolean isValidEmail(CharSequence target) throws InterruptedException {
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()) {
            return false;
        }
        RoomDB db = RoomDB.getInstance(getApplicationContext());
        UserDAO userDAO = db.userDAO();
        AtomicBoolean check = new AtomicBoolean(false);
        Thread t = new Thread(() -> {
            User u = userDAO.getUserByEmail(target.toString());
            if (u != null) {
                check.set(true);
            }
        });
        t.start();
        t.join();
        return check.get();
    }

    private String generateNewPassword() {
        String allowedChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder(8);
        for (int i = 0; i < 8; i++) {
            int randomIndex = (int) (Math.random() * allowedChars.length());
            sb.append(allowedChars.charAt(randomIndex));
        }
        return sb.toString();
    }

    private void sendPasswordToEmail(String email, String newPassword) {
        JSONObject jsonObjectRequest = new JSONObject();
        try {
            jsonObjectRequest.put("toMail", email);
            jsonObjectRequest.put("subject", "Recover password");
            jsonObjectRequest.put("content", "New password for your account is " + newPassword);
            JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                    "https://263a-2405-4803-fad0-a600-edc0-231a-7819-19d5.ngrok-free.app/api/send", jsonObjectRequest, response -> {
                Toast.makeText(ForgotPasswordActivity.this, "A new password has been sent to your email.", Toast.LENGTH_SHORT).show();
                finish();
            }, error -> Toast.makeText(ForgotPasswordActivity.this, "Server is error right now, please try again.", Toast.LENGTH_SHORT).show()) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Content-Type", "application/json; charset=utf-8");
                    headers.put("User-Agent", "localtunnel");
                    return headers;
                }
            };
            VolleySingleton.getmInstance(getApplicationContext()).addToRequestQueue(jsonObjReq);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
