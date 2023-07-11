//package com.project_prm392.Activity;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.project_prm392.DatabaseHelper;
//import com.project_prm392.R;
//
//public class RegisterActivity extends AppCompatActivity {
//    EditText username, address, email, password, repassword;
//    Button register;
//    TextView signin;
//    DatabaseHelper db;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register);
//        username = findViewById(R.id.username);
//        email = findViewById(R.id.email);
//        address = findViewById(R.id.address);
//        password = findViewById(R.id.password);
//        repassword = findViewById(R.id.repassword);
//        signin = findViewById(R.id.textView9);
//        register = findViewById(R.id.register);
//        db = new DatabaseHelper(this);
//        register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String userName = username.getText().toString();
//                String Email = email.getText().toString();
//                String Address = address.getText().toString();
//                String Password = password.getText().toString();
//                String Repassword = repassword.getText().toString();
//                if(TextUtils.isEmpty(userName) || TextUtils.isEmpty(Email) || TextUtils.isEmpty(Address)
//                || TextUtils.isEmpty(Password) || TextUtils.isEmpty(Repassword)){
//                    Toast.makeText(RegisterActivity.this, "All fields required", Toast.LENGTH_SHORT).show();
//                } else{
//                    if(Password.equals(Repassword)){
//                        Boolean checkUser = db.checkEmail(Email);
//                        if(checkUser == false){
//                            Boolean insertUser = db.insertUser(userName, Email, Password, Address);
//                            if(insertUser == true){
//                                Toast.makeText(RegisterActivity.this, "Registered successfully!", Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
//                                startActivity(intent);
//                            }else{
//                                Toast.makeText(RegisterActivity.this, "Registration failed!", Toast.LENGTH_SHORT).show();
//                            }
//
//                        }else{
//                            Toast.makeText(RegisterActivity.this, "User already existed!", Toast.LENGTH_SHORT).show();
//                        }
//                    }else{
//                        Toast.makeText(RegisterActivity.this, "Password are not matching!", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//            }
//        });
//
//        signin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//                startActivity(intent);
//            }
//        });
//    }
//}