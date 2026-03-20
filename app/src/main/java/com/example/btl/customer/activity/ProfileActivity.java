package com.example.btl.customer.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.btl.R;

public class ProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);

        findViewById(R.id.btnBackFromProfile).setOnClickListener(v -> finish());
    }
}