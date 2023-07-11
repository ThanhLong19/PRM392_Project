package com.project_prm392.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.project_prm392.DatabaseHelper;
import com.project_prm392.R;
import com.project_prm392.entity.Category;
import com.project_prm392.entity.Item;
import com.project_prm392.entity.User;

import java.util.ArrayList;

public class ListByCategoryActivity extends AppCompatActivity {

    private ArrayList<Item> listItemByCategory;
    private HomeActivity homeActivity;
    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_by_category);
        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        // get user email
        String gmail = bundle.getString("userGmail");
        Category category = (Category) bundle.get("category");
        Log.e("Name", category.getCategoryName() + gmail);

        recyclerView = findViewById(R.id.recycle_list_item);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        DatabaseHelper db = new DatabaseHelper(this);
        listItemByCategory = db.getListItemByCategory(category.getId());
        itemAdapter = new ItemAdapter(this, listItemByCategory);
        itemAdapter.setData(listItemByCategory);
        recyclerView.setAdapter(itemAdapter);


        Log.e("List item: ", listItemByCategory.toString());

    }
}