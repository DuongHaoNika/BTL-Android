package com.example.btl.nhanvien.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.btl.R;
import com.example.btl.admin.activity.LoginActivity;
import com.google.android.material.appbar.MaterialToolbar;

public class NhanVienActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhanvien_home);

        initViews();
    }

    private void initViews() {
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        
        // Thiết lập nút Logout trên Toolbar (nút Back)
        toolbar.setNavigationOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });

        findViewById(R.id.btnBan).setOnClickListener(v -> {
            startActivity(new Intent(this, BanActivity.class));
        });

        findViewById(R.id.btnHoaDon).setOnClickListener(v -> {
            startActivity(new Intent(this, HoaDonActivity.class));
        });
    }
}