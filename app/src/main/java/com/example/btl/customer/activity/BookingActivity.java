package com.example.btl.customer.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.btl.R;
import com.example.btl.customer.manager.CartManager;

import java.util.Calendar;

public class BookingActivity extends AppCompatActivity {
    private TextView txtSelectTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_booking);


        // --- CODE THÊM NÚT BACK ---
        Toolbar toolbar = findViewById(R.id.toolbarBooking);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        // Xử lý khi nhấn nút quay lại
        toolbar.setNavigationOnClickListener(v -> finish());
        // --------------------------

        txtSelectTime = findViewById(R.id.txtSelectTime);

        txtSelectTime.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            // Hiện lịch
            new DatePickerDialog(this, (view, year, month, day) -> {
                String date = day + "/" + (month + 1) + "/" + year;
                // Sau đó hiện đồng hồ
                new TimePickerDialog(this, (view1, hour, min) -> {
                    txtSelectTime.setText(date + " " + hour + ":" + min);
                }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true).show();
            }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
        });

        findViewById(R.id.btnConfirmBooking).setOnClickListener(v -> {
            Intent intent = new Intent(this, BookingDetailActivity.class);
            intent.putExtra("dateTime", txtSelectTime.getText().toString());
            startActivity(intent);
        });
    }


}