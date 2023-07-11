package com.project_prm392.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project_prm392.R;
import com.project_prm392.entity.Item;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>  {

    private Context context;
    private ArrayList<Item> listItem;

    public ItemAdapter(Context context, ArrayList<Item> listItem) {
        this.context = context;
        this.listItem = listItem;
    }

    public void setData(ArrayList<Item> listItem){
        this.listItem = listItem;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = listItem.get(position);
        if(item == null){
            return;
        }
        holder.itemCalories.setText(String.valueOf(item.getCalories()));
        holder.itemName.setText(item.getItemName());
        holder.itemPrice.setText(String.valueOf(item.getPrice()));
        String imageUrl = item.getItemUrl();
        Picasso.get().load(imageUrl).into(holder.itemImage);
        holder.itemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToItemDetails(item);
            }
        });
    }

    private void goToItemDetails(Item itemX) {
        SharedPreferences sharedPreference = context.getSharedPreferences("DATA",Context.MODE_PRIVATE);
        String userGmail = sharedPreference.getString("account", "");
        Intent intent = new Intent(context, ItemDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("item", itemX);
        bundle.putSerializable("userGmail", userGmail);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        if(listItem!=null){
            return listItem.size();
        }
        return 0;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{

        private ImageView itemImage;
        private TextView itemName, itemCalories, itemPrice;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.itemImg);
            itemName = itemView.findViewById(R.id.item_name);
            itemCalories = itemView.findViewById(R.id.item_calories);
            itemPrice = itemView.findViewById(R.id.price);
        }
    }
}
