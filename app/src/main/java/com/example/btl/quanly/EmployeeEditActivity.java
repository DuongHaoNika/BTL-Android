package com.example.btl.quanly;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.btl.R;
import com.example.btl.admin.manager.UserAdminManager;
import com.example.btl.model.User;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class EmployeeEditActivity extends AppCompatActivity {

    private TextInputEditText etFullName, etPhoneNumber, etUsername, etPassword;
    private MaterialButton btnSave, btnDelete;
    private MaterialToolbar toolbar;
    private UserAdminManager userAdminManager;
    private String mode;
    private int userId = -1;
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_edit);

        userAdminManager = UserAdminManager.getInstance(this);
        initViews();
        getIntentData();
        setupActions();
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        etFullName = findViewById(R.id.etFullName);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnSave = findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);

        // Nút Back trên Toolbar
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    private void getIntentData() {
        mode = getIntent().getStringExtra("mode");
        userId = getIntent().getIntExtra("userId", -1);

        if ("edit".equals(mode) && userId != -1) {
            currentUser = userAdminManager.getUserById(userId);
            if (currentUser != null) {
                etFullName.setText(currentUser.getFullName());
                etPhoneNumber.setText(currentUser.getPhoneNumber());
                etUsername.setText(currentUser.getUsername());
                etPassword.setText(currentUser.getPassword());
                btnDelete.setVisibility(View.VISIBLE);
                toolbar.setTitle("Sửa nhân viên");
            }
        } else {
            toolbar.setTitle("Thêm nhân viên");
        }
    }

    private void setupActions() {
        btnSave.setOnClickListener(v -> handleSave());
        btnDelete.setOnClickListener(v -> handleDelete());
    }

    private void handleSave() {
        String fullName = etFullName.getText().toString().trim();
        String phone = etPhoneNumber.getText().toString().trim();
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (fullName.isEmpty() || phone.isEmpty() || username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        if ("add".equals(mode)) {
            User newUser = new User(
                    (int) (System.currentTimeMillis() % Integer.MAX_VALUE),
                    username,
                    password,
                    fullName,
                    phone,
                    "nhanvien"
            );
            if (userAdminManager.addUser(newUser)) {
                Toast.makeText(this, "Thêm nhân viên thành công", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Tên đăng nhập đã tồn tại", Toast.LENGTH_SHORT).show();
            }
        } else {
            if (currentUser != null) {
                currentUser.setFullName(fullName);
                currentUser.setPhoneNumber(phone);
                currentUser.setUsername(username);
                currentUser.setPassword(password);
                userAdminManager.updateUser(currentUser);
                Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    private void handleDelete() {
        if (currentUser != null) {
            userAdminManager.deleteUser(currentUser.getId());
            Toast.makeText(this, "Đã xóa nhân viên", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}