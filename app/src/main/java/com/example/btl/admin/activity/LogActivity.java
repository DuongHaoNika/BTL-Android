package com.example.btl.admin.activity;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl.R;
import com.example.btl.admin.adapter.LogAdapter;
import com.example.btl.admin.manager.LogManager;

public class LogActivity extends AppCompatActivity {

    private Button btnBack;
    private RecyclerView recyclerLog;

    private LogAdapter logAdapter;
    private LogManager logManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_log);

        initViews();
        initData();
        setupRecyclerView();
        setupActions();
    }

    private void initViews() {
        btnBack = findViewById(R.id.btnBack);
        recyclerLog = findViewById(R.id.recyclerLog);
    }

    private void initData() {
        // Sửa lỗi: Sử dụng getInstance thay vì new
        logManager = LogManager.getInstance(this);
        logAdapter = new LogAdapter(logManager.getAllLogs());
    }

    private void setupRecyclerView() {
        recyclerLog.setLayoutManager(new LinearLayoutManager(this));
        recyclerLog.setAdapter(logAdapter);
    }

    private void setupActions() {
        // BACK - Sử dụng onBackPressed() để đồng bộ với nút back hệ thống
        btnBack.setOnClickListener(v -> onBackPressed());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Cập nhật lại dữ liệu mới nhất từ Singleton khi quay lại màn hình
        logAdapter.updateData(logManager.getAllLogs());
    }
}