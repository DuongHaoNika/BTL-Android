package com.example.btl.customer.activity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.btl.R;
import com.example.btl.customer.manager.CartManager;

public class PaymentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_payment);

        // Ánh xạ View
        TextView txtTotal = findViewById(R.id.txtTotalAmount);

        // Gọi phương thức tính tiền
        double total = CartManager.getInstance().calculateTotal();

        // Hiển thị tổng tiền
        txtTotal.setText(String.format("Tổng: %,.0f VNĐ", total));

        // Nút Thanh toán
        findViewById(R.id.btnFinalPay).setOnClickListener(v -> {
            Toast.makeText(this, "Thanh toán hoàn tất!", Toast.LENGTH_SHORT).show();

            // Xóa sạch giỏ hàng khi thanh toán xong
            CartManager.getInstance().getPendingItems().clear();
            CartManager.getInstance().getOrderedItems().clear();

            // Quay về màn hình trước đó
            finish();
        });
    }
}