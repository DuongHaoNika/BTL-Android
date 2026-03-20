package com.example.btl.nhanvien.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.btl.R;
import com.example.btl.nhanvien.adapter.BanAdapter;
import com.example.btl.nhanvien.model_nv.Ban;
import java.util.ArrayList;
import java.util.List;

public class BanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhanvien_ban);

        Toolbar toolbar = findViewById(R.id.toolbarBan);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> finish());

        RecyclerView recycler = findViewById(R.id.recyclerBan);

        List<Ban> list = new ArrayList<>();
        list.add(new Ban("Bàn 1", "Trống"));
        list.add(new Ban("Bàn 2", "Có khách"));
        list.add(new Ban("Bàn 3", "Đang dọn"));
        list.add(new Ban("Bàn 4", "Trống"));
        list.add(new Ban("Bàn 5", "Có khách"));
        list.add(new Ban("Bàn 6", "Trống"));

        recycler.setLayoutManager(new GridLayoutManager(this, 2));
        recycler.setAdapter(new BanAdapter(list));
    }
}