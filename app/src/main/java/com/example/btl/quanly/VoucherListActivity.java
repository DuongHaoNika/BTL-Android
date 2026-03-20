package com.example.btl.quanly;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.btl.R;
import com.example.btl.model.Voucher;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

public class VoucherListActivity extends AppCompatActivity {

    private RecyclerView rvVouchers;
    private FloatingActionButton fabAddVoucher;
    private MaterialToolbar toolbar;
    private VoucherAdapter adapter;
    private VoucherManager voucherManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voucher_list);

        voucherManager = VoucherManager.getInstance();
        initViews();
        setupRecyclerView();
        setupActions();
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        rvVouchers = findViewById(R.id.rvVouchers);
        fabAddVoucher = findViewById(R.id.fabAddVoucher);

        // Nút Back trên Toolbar
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    private void setupRecyclerView() {
        List<Voucher> voucherList = voucherManager.getAllVouchers();
        adapter = new VoucherAdapter(voucherList, voucher -> {
            Intent intent = new Intent(this, VoucherEditActivity.class);
            intent.putExtra("voucherId", voucher.getId());
            intent.putExtra("mode", "edit");
            startActivity(intent);
        });
        rvVouchers.setLayoutManager(new LinearLayoutManager(this));
        rvVouchers.setAdapter(adapter);
    }

    private void setupActions() {
        fabAddVoucher.setOnClickListener(v -> {
            Intent intent = new Intent(this, VoucherEditActivity.class);
            intent.putExtra("mode", "add");
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.updateData(voucherManager.getAllVouchers());
    }
}