package com.example.btl.customer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.btl.R;
import com.example.btl.customer.adapter.FoodAdapter;
import com.example.btl.customer.manager.CartManager;
import com.example.btl.model.Food;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    private RecyclerView rvMenu;
    private ExtendedFloatingActionButton btnGoToCart;
    private FoodAdapter foodAdapter;
    private List<Food> foodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_menu);

        initViews();
        setupToolbar();
        loadMenuData();
        setupActions();
    }

    private void initViews() {
        rvMenu = findViewById(R.id.rvMenuCustomer);
        btnGoToCart = findViewById(R.id.btnGoToCart);

        // Thiết lập RecyclerView dạng danh sách
        rvMenu.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbarMenu);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        // Nhấn nút Back để quay lại CustomerHome
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    private void loadMenuData() {
        // --- GIẢ LẬP DỮ LIỆU (Sau này lấy từ FoodManager/Admin) ---
        foodList = new ArrayList<>();
        foodList.add(new Food(1, "Combo 1: Gà + Pepsi", 199000, "Ngon", "", true));
        foodList.add(new Food(2, "Burger Tôm", 89000, "Đậm đà", "", true));
        foodList.add(new Food(3, "Khoai tây chiên", 35000, "Giòn", "", true));

        // Khởi tạo Adapter và xử lý sự kiện nhấn dấu (+)
        foodAdapter = new FoodAdapter(foodList, food -> {
            CartManager.getInstance().addToCart(food);
            Toast.makeText(this, "Đã thêm " + food.getName() + " vào giỏ", Toast.LENGTH_SHORT).show();
        });

        rvMenu.setAdapter(foodAdapter);
    }

    private void setupActions() {
        btnGoToCart.setOnClickListener(v -> {
            startActivity(new Intent(this, CartActivity.class));
        });
    }
}