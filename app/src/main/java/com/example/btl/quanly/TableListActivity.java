package com.example.btl.quanly;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.btl.R;
import com.example.btl.model.Table;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

public class TableListActivity extends AppCompatActivity {

    private RecyclerView rvTables;
    private FloatingActionButton fabAddTable;
    private MaterialToolbar toolbar;
    private TableAdapter adapter;
    private TableManager tableManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_list);

        tableManager = TableManager.getInstance();
        initViews();
        setupRecyclerView();
        setupActions();
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        rvTables = findViewById(R.id.rvTables);
        fabAddTable = findViewById(R.id.fabAddTable);

        // Nút Back trên Toolbar
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    private void setupRecyclerView() {
        List<Table> tableList = tableManager.getAllTables();
        adapter = new TableAdapter(tableList, table -> {
            Intent intent = new Intent(this, TableEditActivity.class);
            intent.putExtra("tableId", table.getId());
            intent.putExtra("mode", "edit");
            startActivity(intent);
        });
        rvTables.setLayoutManager(new LinearLayoutManager(this));
        rvTables.setAdapter(adapter);
    }

    private void setupActions() {
        fabAddTable.setOnClickListener(v -> {
            Intent intent = new Intent(this, TableEditActivity.class);
            intent.putExtra("mode", "add");
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.updateData(tableManager.getAllTables());
    }
}