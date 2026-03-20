package com.example.btl.nhanvien.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.btl.R;

public class NhanVienActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhanvien_home);

        findViewById(R.id.btnBan).setOnClickListener(v -> {
            startActivity(new Intent(this, BanActivity.class));
        });

        findViewById(R.id.btnHoaDon).setOnClickListener(v -> {
            startActivity(new Intent(this, HoaDonActivity.class));
        });
    }
}