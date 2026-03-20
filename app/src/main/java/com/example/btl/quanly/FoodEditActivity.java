package com.example.btl.quanly;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.btl.R;
import com.example.btl.model.Food;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;

public class FoodEditActivity extends AppCompatActivity {

    private TextInputEditText etFoodName, etFoodPrice, etFoodDescription;
    private SwitchMaterial swAvailable;
    private MaterialButton btnSaveFood, btnDeleteFood;
    private MaterialToolbar toolbar;
    private FoodManager foodManager;
    private String mode;
    private int foodId = -1;
    private Food currentFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_edit);

        foodManager = FoodManager.getInstance();
        initViews();
        getIntentData();
        setupActions();
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        etFoodName = findViewById(R.id.etFoodName);
        etFoodPrice = findViewById(R.id.etFoodPrice);
        etFoodDescription = findViewById(R.id.etFoodDescription);
        swAvailable = findViewById(R.id.swAvailable);
        btnSaveFood = findViewById(R.id.btnSaveFood);
        btnDeleteFood = findViewById(R.id.btnDeleteFood);

        // Nút Back trên Toolbar
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    private void getIntentData() {
        mode = getIntent().getStringExtra("mode");
        foodId = getIntent().getIntExtra("foodId", -1);

        if ("edit".equals(mode) && foodId != -1) {
            currentFood = foodManager.getFoodById(foodId);
            if (currentFood != null) {
                etFoodName.setText(currentFood.getName());
                etFoodPrice.setText(String.valueOf(currentFood.getPrice()));
                etFoodDescription.setText(currentFood.getDescription());
                swAvailable.setChecked(currentFood.isAvailable());
                btnDeleteFood.setVisibility(View.VISIBLE);
                toolbar.setTitle("Sửa món ăn");
            }
        } else {
            toolbar.setTitle("Thêm món ăn");
        }
    }

    private void setupActions() {
        btnSaveFood.setOnClickListener(v -> handleSave());
        btnDeleteFood.setOnClickListener(v -> handleDelete());
    }

    private void handleSave() {
        String name = etFoodName.getText().toString().trim();
        String priceStr = etFoodPrice.getText().toString().trim();
        String desc = etFoodDescription.getText().toString().trim();
        boolean available = swAvailable.isChecked();

        if (name.isEmpty() || priceStr.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập tên và giá món ăn", Toast.LENGTH_SHORT).show();
            return;
        }

        double price = Double.parseDouble(priceStr);

        if ("add".equals(mode)) {
            Food newFood = new Food(
                    (int) (System.currentTimeMillis() % Integer.MAX_VALUE),
                    name,
                    price,
                    desc,
                    "",
                    available
            );
            foodManager.addFood(newFood);
            Toast.makeText(this, "Thêm món ăn thành công", Toast.LENGTH_SHORT).show();
        } else {
            if (currentFood != null) {
                currentFood.setName(name);
                currentFood.setPrice(price);
                currentFood.setDescription(desc);
                currentFood.setAvailable(available);
                foodManager.updateFood(currentFood);
                Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
            }
        }
        finish();
    }

    private void handleDelete() {
        if (currentFood != null) {
            foodManager.deleteFood(currentFood.getId());
            Toast.makeText(this, "Đã xóa món ăn", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}