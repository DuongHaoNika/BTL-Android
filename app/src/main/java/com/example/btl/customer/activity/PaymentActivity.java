package com.example.btl.customer.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.btl.R;
import com.example.btl.customer.manager.CartManager;

public class PaymentActivity extends AppCompatActivity {

    private TextView txtTotal, txtFinal;
    private Button btnPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_payment);

        initViews();
        setupToolbar();
        calculatePrice();
        setupActions();
    }

    private void initViews() {
        txtTotal = findViewById(R.id.txtTotalAmount);
        txtFinal = findViewById(R.id.txtFinalAmount);
        btnPay = findViewById(R.id.btnFinalPay);
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbarPayment);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        // Nút Back quay lại Giỏ hàng
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    private void calculatePrice() {
        // Lấy tổng tiền từ Manager (đã cộng dồn tất cả món đã gọi)
        double total = CartManager.getInstance().calculateTotal();
        String formattedPrice = String.format("%,.0f VNĐ", total);

        txtTotal.setText(formattedPrice);
        txtFinal.setText(formattedPrice);
    }

    private void setupActions() {
        btnPay.setOnClickListener(v -> {
            Toast.makeText(this, "Thanh toán thành công! Cảm ơn Quý khách.", Toast.LENGTH_LONG).show();

            // Xóa sạch giỏ hàng sau khi thanh toán xong
            CartManager.getInstance().getPendingItems().clear();
            CartManager.getInstance().getOrderedItems().clear();

            // Quay về màn hình chính (Home)
            finish();
        });
    }
}