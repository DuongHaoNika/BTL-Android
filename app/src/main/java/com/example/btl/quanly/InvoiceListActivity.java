package com.example.btl.quanly;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.btl.R;
import com.example.btl.model.HoaDon;
import com.google.android.material.appbar.MaterialToolbar;
import java.util.List;

public class InvoiceListActivity extends AppCompatActivity {

    private RecyclerView rvInvoices;
    private MaterialToolbar toolbar;
    private InvoiceAdapter adapter;
    private InvoiceManager invoiceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_list);

        invoiceManager = InvoiceManager.getInstance();
        initViews();
        setupRecyclerView();
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        rvInvoices = findViewById(R.id.rvInvoices);
        
        // Nút Back trên Toolbar
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    private void setupRecyclerView() {
        List<HoaDon> invoiceList = invoiceManager.getAllInvoices();
        adapter = new InvoiceAdapter(invoiceList);
        rvInvoices.setLayoutManager(new LinearLayoutManager(this));
        rvInvoices.setAdapter(adapter);
    }
}