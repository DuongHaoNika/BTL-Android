package com.example.btl.quanly;

import com.example.btl.model.HoaDon;
import java.util.ArrayList;
import java.util.List;

public class InvoiceManager {
    private static InvoiceManager instance;
    private List<HoaDon> invoiceList;

    private InvoiceManager() {
        invoiceList = new ArrayList<>();
        fakeData();
    }

    public static synchronized InvoiceManager getInstance() {
        if (instance == null) {
            instance = new InvoiceManager();
        }
        return instance;
    }

    private void fakeData() {
        invoiceList.add(new HoaDon(1, "HD001", 1, 1, 250000, "01/01/2025", "Đã thanh toán", "Tiền mặt", ""));
        invoiceList.add(new HoaDon(2, "HD002", 2, 1, 320000, "01/01/2025", "Đã thanh toán", "Chuyển khoản", ""));
    }

    public List<HoaDon> getAllInvoices() {
        return new ArrayList<>(invoiceList);
    }
}