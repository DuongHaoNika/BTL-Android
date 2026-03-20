package com.example.btl.quanly;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.btl.R;
import com.example.btl.admin.activity.LoginActivity;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;

public class QuanLyHomeActivity extends AppCompatActivity {

    private MaterialButton btnNhanVien, btnMonAn, btnBanAn, btnHoaDon, btnVoucher;
    private MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_home);

        initViews();
        setupActions();
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        btnNhanVien = findViewById(R.id.btnNhanVien);
        btnMonAn = findViewById(R.id.btnMonAn);
        btnBanAn = findViewById(R.id.btnBanAn);
        btnHoaDon = findViewById(R.id.btnHoaDon);
        btnVoucher = findViewById(R.id.btnVoucher);
        
        // Cài đặt icon quay lại (Logout ở trang chủ)
        toolbar.setNavigationOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }

    private void setupActions() {
        btnNhanVien.setOnClickListener(v -> {
            Intent intent = new Intent(this, EmployeeListActivity.class);
            startActivity(intent);
        });

        btnMonAn.setOnClickListener(v -> {
            Intent intent = new Intent(this, FoodListActivity.class);
            startActivity(intent);
        });

        btnBanAn.setOnClickListener(v -> {
            Intent intent = new Intent(this, TableListActivity.class);
            startActivity(intent);
        });

        btnHoaDon.setOnClickListener(v -> {
            Intent intent = new Intent(this, InvoiceListActivity.class);
            startActivity(intent);
        });

        btnVoucher.setOnClickListener(v -> {
            Intent intent = new Intent(this, VoucherListActivity.class);
            startActivity(intent);
        });
    }
}