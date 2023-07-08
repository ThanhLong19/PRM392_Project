package com.project_prm392;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

            // TODO: Implement your logic to send a new password to the provided email

            // Example implementation
            if (isValidEmail(email)) {
                // Generate a new password
                String newPassword = generateNewPassword();

                // Send the new password to the email address
                sendPasswordToEmail(email, newPassword);

                Toast.makeText(ForgotPasswordActivity.this, "A new password has been sent to your email", Toast.LENGTH_SHORT).show();
                finish(); // Finish the activity and go back to the login screen
            } else {
                Toast.makeText(ForgotPasswordActivity.this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
            }
        });

        backButton.setOnClickListener(v -> {
            onBackPressed();
        });
    }

    private boolean isValidEmail(CharSequence target) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    private String generateNewPassword() {
        // TODO: Implement your logic to generate a new password (e.g., random password generation)

        // Example implementation: Generate a random 8-character password
        String allowedChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder(8);
        for (int i = 0; i < 8; i++) {
            int randomIndex = (int) (Math.random() * allowedChars.length());
            sb.append(allowedChars.charAt(randomIndex));
        }
        return sb.toString();
    }

    private void sendPasswordToEmail(String email, String newPassword) {
        // TODO: Implement your logic to send the new password to the provided email
    }
}
