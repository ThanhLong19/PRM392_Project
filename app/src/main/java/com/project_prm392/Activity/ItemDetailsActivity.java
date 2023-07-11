package com.project_prm392.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.project_prm392.R;
import com.project_prm392.entity.Category;
import com.project_prm392.entity.Item;

public class ItemDetailsActivity extends AppCompatActivity {

    private TextView itemName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        itemName = findViewById(R.id.item_name);
        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        // get user email
        String gmail = bundle.getString("userGmail");
        Item item = (Item) bundle.get("item");
        Log.e("Name", item.getItemName() + gmail);

        itemName.setText(item.getItemName().toString());

    }
}