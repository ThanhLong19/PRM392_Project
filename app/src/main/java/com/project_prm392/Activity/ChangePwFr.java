package com.project_prm392.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project_prm392.DatabaseHelper;
import com.project_prm392.R;
import com.project_prm392.entity.User;

public class ChangePwFr extends Fragment {

    private  View view;
    private EditText newPw, reNewPw;
    private HomeActivity homeActivity;
    private Button saveBt;
    private User user;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.change_pw_fr, container, false);
        DatabaseHelper db = new DatabaseHelper(getActivity());

        homeActivity = (HomeActivity) getActivity();
        user = homeActivity.getUser();
        String gmailTmp = user.getEmail();
        User userTmp = db.getUserByEmail(gmailTmp);
        user = userTmp;

        newPw = view.findViewById(R.id.password);
        reNewPw = view.findViewById(R.id.rePassword);
        saveBt = view.findViewById(R.id.saveBt);

        saveBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPwStr = newPw.getText().toString().trim();
                String rePwStr = reNewPw.getText().toString().trim();
                if(newPwStr.isEmpty() || rePwStr.isEmpty()){
                    Toast.makeText(getActivity(), "All fields required", Toast.LENGTH_SHORT).show();
                    newPw.setText("");
                    reNewPw.setText("");
                } else{
                    if(!newPwStr.equals(rePwStr)){
                        Toast.makeText(getActivity(), "Password are not matching!", Toast.LENGTH_SHORT).show();
                        newPw.setText("");
                        reNewPw.setText("");
                    } else{
                        Boolean changePw = db.changePw(newPwStr, user.getId());
                        if(changePw){
                            Toast.makeText(homeActivity, "Change password successfully", Toast.LENGTH_SHORT).show();
                            newPw.setText("");
                            reNewPw.setText("");
                        } else{
                            Toast.makeText(homeActivity, "Change password failed!", Toast.LENGTH_SHORT).show();
                            newPw.setText("");
                            reNewPw.setText("");
                        }
                    }
                }
            }
        });
        Log.e("User info in pw fr", user.toString());
        return view;
    }
}