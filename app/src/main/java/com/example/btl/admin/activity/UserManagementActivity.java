package com.example.btl.admin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.btl.R;
import com.example.btl.admin.manager.UserAdminManager;

public class UserManagementActivity extends AppCompatActivity {

    private Button btnBack;
    private Button btnAddUser;
    private Button btnEditUser;
    private Button btnDeleteUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_management);

        initViews();
        setupActions();
    }

    private void initViews() {
        btnBack = findViewById(R.id.btnBack);
        btnAddUser = findViewById(R.id.btnAddUser);
        btnEditUser = findViewById(R.id.btnEditUser);
        btnDeleteUser = findViewById(R.id.btnDeleteUser);
    }

    private void setupActions() {
        // BACK
        btnBack.setOnClickListener(v -> onBackPressed());

        // ADD USER
        btnAddUser.setOnClickListener(v -> {
            Intent intent = new Intent(UserManagementActivity.this, UserEditActivity.class);
            intent.putExtra("mode", "add");
            startActivity(intent);
        });

        // EDIT USER
        btnEditUser.setOnClickListener(v -> {
            Intent intent = new Intent(UserManagementActivity.this, UserEditActivity.class);
            intent.putExtra("mode", "edit");
            startActivity(intent);
        });

        // DELETE USER - Demo: Xóa user cuối cùng nếu có
        btnDeleteUser.setOnClickListener(v -> {
            UserAdminManager manager = UserAdminManager.getInstance(this);
            if (manager.getTotalUsers() > 0) {
                int lastUserId = manager.getAllUsers().get(manager.getAllUsers().size() - 1).getId();
                manager.deleteUser(lastUserId);
                // Có thể thêm log ở đây
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}