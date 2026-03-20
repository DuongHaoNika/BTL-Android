package com.example.btl.admin.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.btl.R;
import com.example.btl.admin.manager.LogManager;
import com.example.btl.admin.manager.UserAdminManager;
import com.example.btl.model.User;

import java.util.Arrays;
import java.util.List;

public class UserEditActivity extends AppCompatActivity {

    private EditText edtUsername, edtPassword, edtRole;
    private Button btnBack, btnSaveUser, btnDeleteUser;

    private UserAdminManager userAdminManager;
    private LogManager logManager;

    private String mode; // add / edit
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_edit);

        initViews();
        initManagers();
        getIntentData();
        setupActions();
    }

    private void initViews() {
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        edtRole = findViewById(R.id.edtRole);

        btnBack = findViewById(R.id.btnBack);
        btnSaveUser = findViewById(R.id.btnSaveUser);
        btnDeleteUser = findViewById(R.id.btnDeleteUser);
    }

    private void initManagers() {
        userAdminManager = UserAdminManager.getInstance(this);
        logManager = LogManager.getInstance(this);
    }

    private void getIntentData() {
        mode = getIntent().getStringExtra("mode");

        if ("edit".equals(mode)) {
            // Ưu tiên lấy theo ID truyền vào, nếu không có mới lấy user cuối cùng
            int userId = getIntent().getIntExtra("userId", -1);
            if (userId != -1) {
                currentUser = userAdminManager.getUserById(userId);
            } else if (!userAdminManager.getAllUsers().isEmpty()) {
                // Giữ lại logic cũ của bạn để không làm hỏng flow hiện tại
                currentUser = userAdminManager.getAllUsers().get(userAdminManager.getAllUsers().size() - 1);
            }

            if (currentUser != null) {
                edtUsername.setText(currentUser.getUsername());
                edtPassword.setText(currentUser.getPassword());
                edtRole.setText(currentUser.getRole());
                // Không cho sửa username nếu đang ở chế độ edit (thông lệ quản lý)
                edtUsername.setEnabled(false);
            }
        }
    }

    private void setupActions() {
        btnBack.setOnClickListener(v -> finish());
        btnSaveUser.setOnClickListener(v -> handleSave());
        btnDeleteUser.setOnClickListener(v -> handleDelete());
    }

    private void handleSave() {
        String username = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        String role = edtRole.getText().toString().trim().toLowerCase(); // Chuyển về chữ thường để đồng bộ

        if (username.isEmpty() || password.isEmpty() || role.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        // Kiểm tra role hợp lệ (Đảm bảo Admin nhập đúng role để LoginActivity bắt được)
        List<String> validRoles = Arrays.asList("admin", "customer", "nhanvien", "quanly");
        if (!validRoles.contains(role)) {
            Toast.makeText(this, "Role không hợp lệ (admin, customer, nhanvien, quanly)", Toast.LENGTH_LONG).show();
            return;
        }

        if ("add".equals(mode)) {
            User newUser = new User(
                    (int) (System.currentTimeMillis() % Integer.MAX_VALUE),
                    username,
                    password,
                    username, // Hiển thị tên mặc định là username
                    "",
                    role
            );

            boolean success = userAdminManager.addUser(newUser);

            if (success) {
                logManager.addLog("Thêm user: " + username + " với quyền " + role);
                Toast.makeText(this, "Thêm " + role + " thành công", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Username đã tồn tại", Toast.LENGTH_SHORT).show();
            }

        } else if ("edit".equals(mode)) {
            if (currentUser != null) {
                currentUser.setPassword(password);
                currentUser.setRole(role);

                userAdminManager.updateUser(currentUser);
                logManager.addLog("Cập nhật user: " + username);

                Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    private void handleDelete() {
        if (currentUser != null) {
            userAdminManager.deleteUser(currentUser.getId());
            logManager.addLog("Xóa user: " + currentUser.getUsername());

            Toast.makeText(this, "Đã xóa user", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}