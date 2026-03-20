package com.example.btl.admin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.btl.R;
import com.example.btl.admin.manager.UserAdminManager;
import com.example.btl.model.User;

public class LoginActivity extends AppCompatActivity {

    private EditText edtUsername;
    private EditText edtPassword;
    private Button btnLogin;

    private UserAdminManager userAdminManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        initViews();
        initManagers();
        setupActions();
    }

    private void initViews() {
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
    }

    private void initManagers() {
        userAdminManager = UserAdminManager.getInstance(this);
    }

    private void setupActions() {
        btnLogin.setOnClickListener(v -> handleLogin());
    }

    private void handleLogin() {
        String username = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        if (username.isEmpty()) {
            edtUsername.setError("Vui lòng nhập username");
            edtUsername.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            edtPassword.setError("Vui lòng nhập password");
            edtPassword.requestFocus();
            return;
        }

        User user = userAdminManager.findByUsername(username);

        if (user == null) {
            Toast.makeText(this, "Tài khoản không tồn tại", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!user.getPassword().equals(password)) {
            Toast.makeText(this, "Sai mật khẩu", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!"admin".equalsIgnoreCase(user.getRole())) {
            Toast.makeText(this, "Tài khoản này không thuộc quyền admin", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(LoginActivity.this, AdminHomeActivity.class);
        startActivity(intent);
        finish();
    }
}