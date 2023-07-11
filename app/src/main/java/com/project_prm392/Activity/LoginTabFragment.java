package com.project_prm392.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.project_prm392.DatabaseHelper;
import com.project_prm392.R;
import com.project_prm392.entity.User;

public class LoginTabFragment extends Fragment {

    SharedPreferences sharedPreference;
    SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fr, container, false);
        Button loginBt = root.findViewById(R.id.loginBt);
        EditText email = root.findViewById(R.id.emailSi);
        EditText password = root.findViewById(R.id.passwordSi);
        TextView forgotPw = root.findViewById(R.id.forgotPassword);
        DatabaseHelper db = new DatabaseHelper(getActivity());

        sharedPreference = getContext().getSharedPreferences("DATA", Context.MODE_PRIVATE);
        editor = sharedPreference.edit();

        forgotPw.setOnClickListener((view) -> {
            Intent i = new Intent(getActivity(), ForgotPasswordActivity.class);
            startActivity(i);
        });

//        forgotPw.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), ForgotPwActivity.class);
//                startActivity(intent);
//            }
//        });

        loginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailSi = email.getText().toString();
                String passwordSi = password.getText().toString();
                User user = null;
                if (TextUtils.isEmpty(emailSi) || TextUtils.isEmpty(passwordSi)) {
                    Toast.makeText(getActivity(), "All fields required!", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkUP = db.checkAccountForLogin(emailSi, passwordSi);
                    if (checkUP == true) {
                        user = db.getUserByEmail(emailSi);
                        Toast.makeText(getActivity(), "Login successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), HomeActivity.class);
                        intent.putExtra("user", user);
                        editor.putString("account", emailSi);
                        editor.putString("password", passwordSi);
                        editor.apply();
                        startActivity(intent);
                    } else {
                        Toast.makeText(getActivity(), "Login failed!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return root;
    }
}
