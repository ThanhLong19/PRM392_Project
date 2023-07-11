package com.project_prm392.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.project_prm392.Adapter.CategoryAdapter;
import com.project_prm392.DatabaseHelper;
import com.project_prm392.R;
import com.project_prm392.entity.Category;
import com.project_prm392.entity.User;
import com.google.android.material.slider.Slider;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    @Nullable
    private ImageSlider imageSlider;
    private HomeActivity homeActivity;
    private View view;
    private User user;
    private TextView gmail, name;

    private RecyclerView recyclerView_Category;
    ArrayList<Category> listOfCategory = new ArrayList<>();
    CategoryAdapter categoryAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.home_fr, container, false);
        homeActivity = (HomeActivity) getActivity();
        user = homeActivity.getUser();
        Log.e("User info in home fr", user.toString());
        DatabaseHelper db = new DatabaseHelper(getActivity());

        imageSlider = view.findViewById(R.id.image_slider);
        ArrayList<SlideModel> imageList = new ArrayList<>();
        imageList.add(new SlideModel("https://mir-s3-cdn-cf.behance.net/projects/404/25f6c5173930157.Y3JvcCwxMDgwLDg0NCwwLDQy.png", ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel("https://img.freepik.com/free-vector/flat-design-pizza-sale-banner_23-2149116013.jpg?size=626&ext=jpg",  ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel("https://img.freepik.com/free-psd/food-menu-restaurant-social-media-cover-template_202595-368.jpg?w=1380&t=st=1688833354~exp=1688833954~hmac=7997c52527058b5acf3a43998d4b762ac60fd276f43440e81ba1fd7068c9d1e8",  ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel("https://img.freepik.com/free-vector/flat-design-pizza-facebook-cover_23-2149116002.jpg?w=1380&t=st=1688833373~exp=1688833973~hmac=5dc64f7f3bdd5446d2d871884951d46107ca82ed7e8801aae48f899c38022341", ScaleTypes.CENTER_CROP));
        imageSlider.setImageList(imageList);

        //load category
        recyclerView_Category = view.findViewById(R.id.recycle_view_category);
        listOfCategory = db.getAllCategories();
        Log.e("List", listOfCategory.toString());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_Category.setLayoutManager(linearLayoutManager);
        recyclerView_Category.setItemAnimator(new DefaultItemAnimator());

        categoryAdapter = new CategoryAdapter(listOfCategory, getActivity()) ;
        // set adapter to recycle view
        recyclerView_Category.setAdapter(categoryAdapter);


        return view;
    }
}
