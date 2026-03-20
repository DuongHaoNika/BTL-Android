package com.example.btl.customer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.btl.R;
import com.example.btl.customer.manager.CartManager;

public class CartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_cart);

        // Nút GỌI MÓN
        findViewById(R.id.btnOrderFoods).setOnClickListener(v -> {
            CartManager.getInstance().confirmOrder();
            Toast.makeText(this, "Đã gửi đơn xuống bếp!", Toast.LENGTH_SHORT).show();
            recreate();
        });

        // Nút THANH TOÁN
        findViewById(R.id.btnGoToPayment).setOnClickListener(v -> {
            startActivity(new Intent(this, PaymentActivity.class));
        });
    }
}