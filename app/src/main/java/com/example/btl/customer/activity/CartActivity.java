package com.example.btl.customer.activity;import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.btl.R;
import com.example.btl.customer.adapter.CartAdapter;
import com.example.btl.customer.manager.CartManager;

public class CartActivity extends AppCompatActivity {

    private RecyclerView rvPending, rvOrdered;
    private CartAdapter pendingAdapter, orderedAdapter;
    private Button btnOrder, btnPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_cart);

        initViews();
        setupToolbar();
        setupRecyclerViews();
        setupActions();
    }

    private void initViews() {
        rvPending = findViewById(R.id.rvPendingItems);
        rvOrdered = findViewById(R.id.rvOrderedItems);
        btnOrder = findViewById(R.id.btnOrderFoods);
        btnPay = findViewById(R.id.btnGoToPayment);
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbarCart);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    private void setupRecyclerViews() {
        // Thiết lập danh sách món đang chọn
        pendingAdapter = new CartAdapter(CartManager.getInstance().getPendingItems(), false);
        rvPending.setLayoutManager(new LinearLayoutManager(this));
        rvPending.setAdapter(pendingAdapter);

        // Thiết lập danh sách món đã gọi
        orderedAdapter = new CartAdapter(CartManager.getInstance().getOrderedItems(), true);
        rvOrdered.setLayoutManager(new LinearLayoutManager(this));
        rvOrdered.setAdapter(orderedAdapter);
    }

    private void setupActions() {
        btnOrder.setOnClickListener(v -> {
            if (CartManager.getInstance().getPendingItems().isEmpty()) {
                Toast.makeText(this, "Không có món mới để gọi!", Toast.LENGTH_SHORT).show();
                return;
            }
            CartManager.getInstance().confirmOrder();
            pendingAdapter.notifyDataSetChanged();
            orderedAdapter.notifyDataSetChanged();
            Toast.makeText(this, "Đã gửi đơn xuống bếp!", Toast.LENGTH_SHORT).show();
        });

        btnPay.setOnClickListener(v -> {
            if (CartManager.getInstance().getOrderedItems().isEmpty()) {
                Toast.makeText(this, "Bạn chưa gọi món nào để thanh toán!", Toast.LENGTH_SHORT).show();
                return;
            }
            startActivity(new Intent(this, PaymentActivity.class));
        });
    }
}