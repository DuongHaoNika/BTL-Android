package com.example.btl.admin.manager;

import android.content.Context;

import com.example.btl.model.HoaDon;

import java.util.ArrayList;
import java.util.List;

public class AdminManager {

    private final Context context;
    private final List<HoaDon> hoaDonList;

    public AdminManager(Context context) {
        this.context = context;
        this.hoaDonList = new ArrayList<>();
        fakeData();
    }

    private void fakeData() {
        hoaDonList.clear();

        hoaDonList.add(new HoaDon(1, "HD001", 1, 1, 250000, "2026-03-20", "DA_THANH_TOAN", "TIEN_MAT", ""));
        hoaDonList.add(new HoaDon(2, "HD002", 2, 2, 320000, "2026-03-20", "DA_THANH_TOAN", "CHUYEN_KHOAN", ""));
        hoaDonList.add(new HoaDon(3, "HD003", 3, 3, 180000, "2026-03-19", "DA_THANH_TOAN", "TIEN_MAT", ""));
    }

    public double getTodayRevenue() {
        String today = "2026-03-20";
        double total = 0;

        for (HoaDon hoaDon : hoaDonList) {
            if (today.equals(hoaDon.getCreatedDate())
                    && "DA_THANH_TOAN".equalsIgnoreCase(hoaDon.getStatus())) {
                total += hoaDon.getTotalAmount();
            }
        }

        return total;
    }

    public List<HoaDon> getAllHoaDon() {
        return new ArrayList<>(hoaDonList);
    }

    public void addHoaDon(HoaDon hoaDon) {
        hoaDonList.add(hoaDon);
    }
}