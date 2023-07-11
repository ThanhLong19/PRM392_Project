package com.project_prm392.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project_prm392.Activity.HomeActivity;
import com.project_prm392.Activity.ListByCategoryActivity;
import com.project_prm392.R;
import com.project_prm392.entity.Category;
import com.project_prm392.entity.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private ArrayList<Category> listCategory;
    private HomeActivity homeActivity;
    private User user;
    Context context;

    public CategoryAdapter(ArrayList<Category> listCategory, Context context) {
        this.listCategory = listCategory;
        this.context = context;
    }

    public void setData(ArrayList<Category> list){
        this.listCategory = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = listCategory.get(position);
        if(category == null){
            return;
        }


        holder.title.setText(category.getCategoryName());
        String imageUrl = category.getImageUrl();
        Picasso.get().load(imageUrl).into(holder.imgCategory);
//        Glide.with(holder.itemView.getContext()).load(category.getImageUrl()).into(holder.imgCategory);
        holder.imgCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToListItemByCategory(category);
            }
        });
    }

    private void goToListItemByCategory(Category categoryX) {
        SharedPreferences sharedPreference = context.getSharedPreferences("DATA",Context.MODE_PRIVATE);
        String userGmail = sharedPreference.getString("account", "");
        Intent intent = new Intent(context, ListByCategoryActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("category", categoryX);
        bundle.putSerializable("userGmail", userGmail);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        if(listCategory!=null){
            return  listCategory.size();
        }
        return 0;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgCategory;
        private TextView title;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCategory = itemView.findViewById(R.id.img_category);
            title = itemView.findViewById(R.id.title);

        }

    }

}
