package com.example.btl.quanly;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.btl.R;
import com.example.btl.model.Table;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class TableEditActivity extends AppCompatActivity {

    private TextInputEditText etTableName, etCapacity, etFloor, etRoom;
    private MaterialButton btnSaveTable, btnDeleteTable;
    private MaterialToolbar toolbar;
    private TableManager tableManager;
    private String mode;
    private int tableId = -1;
    private Table currentTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_edit);

        tableManager = TableManager.getInstance();
        initViews();
        getIntentData();
        setupActions();
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        etTableName = findViewById(R.id.etTableName);
        etCapacity = findViewById(R.id.etCapacity);
        etFloor = findViewById(R.id.etFloor);
        etRoom = findViewById(R.id.etRoom);
        btnSaveTable = findViewById(R.id.btnSaveTable);
        btnDeleteTable = findViewById(R.id.btnDeleteTable);

        // Nút Back trên Toolbar
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    private void getIntentData() {
        mode = getIntent().getStringExtra("mode");
        tableId = getIntent().getIntExtra("tableId", -1);

        if ("edit".equals(mode) && tableId != -1) {
            currentTable = tableManager.getTableById(tableId);
            if (currentTable != null) {
                etTableName.setText(currentTable.getTableName());
                etCapacity.setText(String.valueOf(currentTable.getCapacity()));
                etFloor.setText(String.valueOf(currentTable.getFloor()));
                etRoom.setText(currentTable.getRoom());
                btnDeleteTable.setVisibility(View.VISIBLE);
                toolbar.setTitle("Sửa bàn");
            }
        } else {
            toolbar.setTitle("Thêm bàn");
        }
    }

    private void setupActions() {
        btnSaveTable.setOnClickListener(v -> handleSave());
        btnDeleteTable.setOnClickListener(v -> handleDelete());
    }

    private void handleSave() {
        String name = etTableName.getText().toString().trim();
        String capacityStr = etCapacity.getText().toString().trim();
        String floorStr = etFloor.getText().toString().trim();
        String room = etRoom.getText().toString().trim();

        if (name.isEmpty() || capacityStr.isEmpty() || floorStr.isEmpty() || room.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        int capacity = Integer.parseInt(capacityStr);
        int floor = Integer.parseInt(floorStr);

        if ("add".equals(mode)) {
            Table newTable = new Table(
                    (int) (System.currentTimeMillis() % Integer.MAX_VALUE),
                    name,
                    capacity,
                    floor,
                    room,
                    "Trống"
            );
            tableManager.addTable(newTable);
            Toast.makeText(this, "Thêm bàn thành công", Toast.LENGTH_SHORT).show();
        } else {
            if (currentTable != null) {
                currentTable.setTableName(name);
                currentTable.setCapacity(capacity);
                currentTable.setFloor(floor);
                currentTable.setRoom(room);
                tableManager.updateTable(currentTable);
                Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
            }
        }
        finish();
    }

    private void handleDelete() {
        if (currentTable != null) {
            tableManager.deleteTable(currentTable.getId());
            Toast.makeText(this, "Đã xóa bàn", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}