package com.project_prm392.Activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.project_prm392.DatabaseHelper;
import com.project_prm392.R;
import com.project_prm392.entity.User;
import com.google.android.material.navigation.NavigationView;

public class ProfileFragment extends Fragment {

    private HomeActivity homeActivity;
    private View view;
    private User user;
    private TextView name, gmail;
    private EditText nameE, address, phone;
    private Button cancelBt, saveChangeBt;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.profile_fr, container, false);
        DatabaseHelper db = new DatabaseHelper(getActivity());

        homeActivity = (HomeActivity) getActivity();
        user = homeActivity.getUser();
        String gmailTmp = user.getEmail();
        User userTmp = db.getUserByEmail(gmailTmp);
        user = userTmp;

        NavigationView navigationView = (NavigationView) homeActivity.findViewById(R.id.navigation_view);
        gmail = view.findViewById(R.id.gmail);
        nameE = view.findViewById(R.id.nameEdit);
        phone = view.findViewById(R.id.phoneEdit);
        address = view.findViewById(R.id.addressEdit);
        saveChangeBt = view.findViewById(R.id.saveChange);
        cancelBt = view.findViewById(R.id.cancelBt);

        gmail.setText(user.getEmail());
        nameE.setText(user.getUsername());
        phone.setText(user.getPhone());
        address.setText(user.getAddress());
        Log.e("User info in profile fr", user.toString());

        saveChangeBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameStr = nameE.getText().toString().trim();
                String phoneStr = phone.getText().toString().trim();
                String addressStr = address.getText().toString().trim();
                if(TextUtils.isEmpty(nameStr) || TextUtils.isEmpty(phoneStr) || TextUtils.isEmpty(addressStr)){
                    Toast.makeText(getActivity(), "All fields required", Toast.LENGTH_SHORT).show();
                } else{
                    User newUser = new User(user.getId(), user.getEmail(), nameStr, phoneStr, addressStr,
                            user.getPassword(), user.getRole());
                            Boolean update = db.editProfile(newUser);
                    if(update == true){
                        Toast.makeText(getActivity(), "Updated successfully!", Toast.LENGTH_SHORT).show();
                        View hView = navigationView.getHeaderView(0);
                        name = hView.findViewById(R.id.name);
                        gmail = hView.findViewById(R.id.gmail);
                        name.setText(newUser.getUsername());
                        gmail.setText(user.getEmail());
                    }else{
                        Toast.makeText(getActivity(), "Updated failed!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return view;
    }
}
