package com.example.btl.customer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.btl.R;

public class CustomerHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home);

        // Kết nối các nút bấm từ Layout
        findViewById(R.id.btnBooking).setOnClickListener(v ->
                startActivity(new Intent(this, BookingActivity.class)));

        findViewById(R.id.btnMenu).setOnClickListener(v ->
                startActivity(new Intent(this, MenuActivity.class)));

        findViewById(R.id.btnProfile).setOnClickListener(v ->
                startActivity(new Intent(this, ProfileActivity.class)));

        findViewById(R.id.btnLogout).setOnClickListener(v -> {
            // Quay lại màn hình Login (giả sử LoginActivity nằm ở package gốc)
            finish();
        });
    }
}