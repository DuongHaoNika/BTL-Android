package com.example.btl.nhanvien.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.btl.R;
import com.example.btl.nhanvien.adapter.HoaDonAdapter;
import com.example.btl.nhanvien.model_nv.HoaDon;
import java.util.ArrayList;
import java.util.List;

public class HoaDonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhanvien_hoadon);

        Toolbar toolbar = findViewById(R.id.toolbarHoaDon);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> finish());

        RecyclerView recycler = findViewById(R.id.recyclerHoaDon);

        List<HoaDon> list = new ArrayList<>();
        list.add(new HoaDon("001", "Bàn 1", "Chờ xác nhận"));
        list.add(new HoaDon("002", "Bàn 2", "Đang chế biến"));
        list.add(new HoaDon("003", "Bàn 3", "Mới tạo"));

        HoaDonAdapter adapter = new HoaDonAdapter(list, hoaDon -> {
            Intent intent = new Intent(HoaDonActivity.this, ChiTietHoaDonActivity.class);
            intent.putExtra("ma", hoaDon.getMa());
            intent.putExtra("ban", hoaDon.getBan());
            intent.putExtra("trangThai", hoaDon.getTrangThai());
            startActivity(intent);
        });

        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);

        findViewById(R.id.btnXacNhanAll).setOnClickListener(v -> adapter.xacNhanTatCa());
    }
}