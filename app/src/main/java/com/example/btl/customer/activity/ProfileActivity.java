package com.example.btl.customer.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.btl.R;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);

        setupToolbar();

        // Sau này bạn sẽ lấy thông tin User từ Database/UserAdminManager
        // và setText cho các EditText ở đây.
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbarProfile);
        setSupportActionBar(toolbar);

        // Hiển thị nút Back (mũi tên quay lại)
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // Xử lý sự kiện nhấn nút Back
        toolbar.setNavigationOnClickListener(v -> finish());
    }
}