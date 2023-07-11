package com.project_prm392.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.project_prm392.DatabaseHelper;
import com.project_prm392.R;
import com.project_prm392.entity.User;

public class SignupTabFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_tab_fr, container, false);
        Button signupBt = root.findViewById(R.id.signUpBt);
        EditText email = root.findViewById(R.id.emailSu);
        EditText name = root.findViewById(R.id.name);
        EditText address = root.findViewById(R.id.address);
        EditText phone = root.findViewById(R.id.phone);
        EditText password = root.findViewById(R.id.passwordSu);
        EditText rePassword = root.findViewById(R.id.rePassword);
        DatabaseHelper db = new DatabaseHelper(getActivity());

        signupBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = name.getText().toString();
                String Email = email.getText().toString();
                String Address = address.getText().toString();
                String Phone = phone.getText().toString();
                String Password = password.getText().toString();
                String Repassword = rePassword.getText().toString();
                if(TextUtils.isEmpty(userName) || TextUtils.isEmpty(Email) || TextUtils.isEmpty(Address)
                        || TextUtils.isEmpty(Phone) || TextUtils.isEmpty(Password) || TextUtils.isEmpty(Repassword)){
                    Toast.makeText(getActivity(), "All fields required", Toast.LENGTH_SHORT).show();
                } else{
                    if(Password.equals(Repassword)){
                        Boolean checkUser = db.checkEmail(Email);
                        if(checkUser == false){
                            User user = new User(Email, userName, Address, Phone, Password);
                            Boolean insertUser = db.insertUser(user);
                            if(insertUser == true){
                                user = db.getUserByEmail(Email);
                                Toast.makeText(getActivity(), "Registered successfully!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getActivity(), HomeActivity.class);
                                intent.putExtra("user", user);
                                startActivity(intent);
                            }else{
                                Toast.makeText(getActivity(), "Registration failed!", Toast.LENGTH_SHORT).show();
                            }

                        }else{
                            Toast.makeText(getActivity(), "User already existed!", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getActivity(), "Password are not matching!", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
        return root;
    }
}
