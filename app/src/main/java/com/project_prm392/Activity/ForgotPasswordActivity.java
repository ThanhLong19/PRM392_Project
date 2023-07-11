package com.project_prm392.Activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.project_prm392.DatabaseHelper;
import com.project_prm392.R;
import com.project_prm392.VolleySingleton;
import com.project_prm392.entity.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText emailEditText;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pw);

        emailEditText = findViewById(R.id.email);
        Button resetButton = findViewById(R.id.send);
        db = new DatabaseHelper(getApplicationContext());

        resetButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString();
            try {
                if (isValidEmail(email)) {
                    // Generate a new password
                    String newPassword = generateNewPassword();
                    User u = db.getUserByEmail(email);
                    db.changePw(newPassword, u.getId());
                    sendPasswordToEmail(email, newPassword);
                } else {
                    Toast.makeText(ForgotPasswordActivity.this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private boolean isValidEmail(CharSequence target) throws InterruptedException {
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()) {
            return false;
        }
        User u = db.getUserByEmail(target.toString());
        return u != null;
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
                    "https://aa25fb0d1f24-8068224068665278382.ngrok-free.app/api/send", jsonObjectRequest, response -> {
                Toast.makeText(ForgotPasswordActivity.this, "A new password has been sent to your email.", Toast.LENGTH_SHORT).show();
                finish();
            }, error -> {
                String er = error.networkResponse.statusCode + ": " + new String(error.networkResponse.data);
                Log.e("Volley Error: ", er);
                Toast.makeText(ForgotPasswordActivity.this, "Server is error right now, please try again.", Toast.LENGTH_SHORT).show();
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Content-Type", "application/json; charset=utf-8");
                    headers.put("ngrok-skip-browser-warning", "true");
                    return headers;
                }
            };
            jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            VolleySingleton.getmInstance(getApplicationContext()).addToRequestQueue(jsonObjReq);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
