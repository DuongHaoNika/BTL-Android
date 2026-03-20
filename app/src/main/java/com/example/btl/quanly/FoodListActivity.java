package com.example.btl.quanly;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.btl.R;
import com.example.btl.model.Food;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

public class FoodListActivity extends AppCompatActivity {

    private RecyclerView rvFoods;
    private FloatingActionButton fabAddFood;
    private MaterialToolbar toolbar;
    private FoodAdapter adapter;
    private FoodManager foodManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        foodManager = FoodManager.getInstance();
        initViews();
        setupRecyclerView();
        setupActions();
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        rvFoods = findViewById(R.id.rvFoods);
        fabAddFood = findViewById(R.id.fabAddFood);

        // Nút Back trên Toolbar
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    private void setupRecyclerView() {
        List<Food> foodList = foodManager.getAllFoods();
        adapter = new FoodAdapter(foodList, food -> {
            Intent intent = new Intent(this, FoodEditActivity.class);
            intent.putExtra("foodId", food.getId());
            intent.putExtra("mode", "edit");
            startActivity(intent);
        });
        rvFoods.setLayoutManager(new LinearLayoutManager(this));
        rvFoods.setAdapter(adapter);
    }

    private void setupActions() {
        fabAddFood.setOnClickListener(v -> {
            Intent intent = new Intent(this, FoodEditActivity.class);
            intent.putExtra("mode", "add");
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.updateData(foodManager.getAllFoods());
    }
}