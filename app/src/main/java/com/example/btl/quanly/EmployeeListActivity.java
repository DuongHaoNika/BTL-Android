package com.example.btl.quanly;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.btl.R;
import com.example.btl.admin.manager.UserAdminManager;
import com.example.btl.model.User;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeListActivity extends AppCompatActivity {

    private RecyclerView rvEmployees;
    private FloatingActionButton fabAddEmployee;
    private MaterialToolbar toolbar;
    private EmployeeAdapter adapter;
    private UserAdminManager userAdminManager;
    private List<User> employeeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);

        userAdminManager = UserAdminManager.getInstance(this);
        initViews();
        setupRecyclerView();
        setupActions();
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        rvEmployees = findViewById(R.id.rvEmployees);
        fabAddEmployee = findViewById(R.id.fabAddEmployee);

        // Nút Back trên Toolbar
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    private void setupRecyclerView() {
        loadEmployees();
        adapter = new EmployeeAdapter(employeeList, user -> {
            Intent intent = new Intent(this, EmployeeEditActivity.class);
            intent.putExtra("userId", user.getId());
            intent.putExtra("mode", "edit");
            startActivity(intent);
        });
        rvEmployees.setLayoutManager(new LinearLayoutManager(this));
        rvEmployees.setAdapter(adapter);
    }

    private void loadEmployees() {
        employeeList = userAdminManager.getAllUsers().stream()
                .filter(u -> "nhanvien".equals(u.getRole()))
                .collect(Collectors.toList());
    }

    private void setupActions() {
        fabAddEmployee.setOnClickListener(v -> {
            Intent intent = new Intent(this, EmployeeEditActivity.class);
            intent.putExtra("mode", "add");
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadEmployees();
        adapter.updateData(employeeList);
    }
}