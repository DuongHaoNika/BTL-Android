package com.example.btl.customer.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.btl.R;

public class BookingDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_booking_detail);

        Spinner spTables = findViewById(R.id.spTables);
        // Giả lập danh sách bàn trống từ Quản lý
        String[] mockTables = {"Bàn A1 (4 người)", "Bàn A2 (2 người)", "Bàn B5 (10 người)"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mockTables);
        spTables.setAdapter(adapter);

        findViewById(R.id.btnBookNow).setOnClickListener(v -> {
            Toast.makeText(this, "Đặt bàn thành công! Chờ Admin xác nhận.", Toast.LENGTH_LONG).show();
            finish();
        });
    }
}