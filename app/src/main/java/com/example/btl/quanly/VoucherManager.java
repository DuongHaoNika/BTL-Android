package com.example.btl.quanly;

import com.example.btl.model.Voucher;
import java.util.ArrayList;
import java.util.List;

public class VoucherManager {
    private static VoucherManager instance;
    private List<Voucher> voucherList;

    private VoucherManager() {
        voucherList = new ArrayList<>();
        fakeData();
    }

    public static synchronized VoucherManager getInstance() {
        if (instance == null) {
            instance = new VoucherManager();
        }
        return instance;
    }

    private void fakeData() {
        voucherList.add(new Voucher(1, "KM10", "Giảm 10%", 10, "PERCENT", "01/01/2025", "31/12/2025", true));
        voucherList.add(new Voucher(2, "GIAM50K", "Giảm 50k", 50000, "AMOUNT", "01/01/2025", "31/12/2025", true));
    }

    public List<Voucher> getAllVouchers() {
        return new ArrayList<>(voucherList);
    }

    public Voucher getVoucherById(int id) {
        for (Voucher v : voucherList) {
            if (v.getId() == id) {
                return v;
            }
        }
        return null;
    }

    public void addVoucher(Voucher voucher) {
        voucherList.add(voucher);
    }

    public void updateVoucher(Voucher updatedVoucher) {
        for (int i = 0; i < voucherList.size(); i++) {
            if (voucherList.get(i).getId() == updatedVoucher.getId()) {
                voucherList.set(i, updatedVoucher);
                return;
            }
        }
    }

    public void deleteVoucher(int id) {
        voucherList.removeIf(v -> v.getId() == id);
    }
}