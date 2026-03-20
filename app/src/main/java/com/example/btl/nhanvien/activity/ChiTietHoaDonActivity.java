package com.example.btl.nhanvien.activity;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.btl.R;

public class ChiTietHoaDonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhanvien_chitiethoadon);

        String ma = getIntent().getStringExtra("ma");
        if (ma == null) ma = "001";
        String ban = getIntent().getStringExtra("ban");
        if (ban == null) ban = "01";

        Toolbar toolbar = findViewById(R.id.toolbarCT);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("HÓA ĐƠN #" + ma);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setNavigationOnClickListener(v -> finish());

        TextView txtBanDetail = findViewById(R.id.txtBanDetail);
        txtBanDetail.setText("Bàn: " + ban);

        TextView txtItems = findViewById(R.id.txtItems);
        txtItems.setText("Phở bò x2\nCơm rang x1\nCoca x1");

        TextView txtTongTien = findViewById(R.id.txtTongTien);
        txtTongTien.setText("Tổng tiền:  150.000đ");

        findViewById(R.id.btnXacNhanDon).setOnClickListener(v -> finish());
    }
}