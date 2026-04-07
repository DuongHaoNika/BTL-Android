package com.example.btl.admin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.btl.R;
import com.example.btl.admin.manager.AdminManager;
import com.example.btl.admin.manager.UserAdminManager;
import com.google.android.material.appbar.MaterialToolbar;

public class AdminHomeActivity extends AppCompatActivity {

    private TextView txtTotalUsers;
    private TextView txtRevenue;
    private com.google.android.material.button.MaterialButton btnUsers;
    private com.google.android.material.button.MaterialButton btnLogs;
    private MaterialToolbar toolbar;

    private UserAdminManager userAdminManager;
    private AdminManager adminManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        initViews();
        initManagers();
        loadDashboardData();
        setupActions();
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        txtTotalUsers = findViewById(R.id.txtTotalUsers);
        txtRevenue = findViewById(R.id.txtRevenue);
        btnUsers = findViewById(R.id.btnUsers);
        btnLogs = findViewById(R.id.btnLogs);

        toolbar.setNavigationOnClickListener(v -> {
            Intent intent = new Intent(AdminHomeActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }

    private void initManagers() {
        userAdminManager = UserAdminManager.getInstance(this);
        adminManager = new AdminManager(this);
    }

    private void loadDashboardData() {
        int totalUsers = userAdminManager.getTotalUsers();
        double todayRevenue = adminManager.getTodayRevenue();

        txtTotalUsers.setText(String.valueOf(totalUsers));
        txtRevenue.setText(String.format("%,.0f đ", todayRevenue));
    }

    private void setupActions() {
        btnUsers.setOnClickListener(v -> {
            Intent intent = new Intent(AdminHomeActivity.this, UserManagementActivity.class);
            startActivity(intent);
        });

        btnLogs.setOnClickListener(v -> {
            Intent intent = new Intent(AdminHomeActivity.this, LogActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadDashboardData();
    }
}