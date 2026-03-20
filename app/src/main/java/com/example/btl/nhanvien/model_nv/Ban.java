package com.example.btl.nhanvien.model_nv;

public class Ban {
    private String tenBan;
    private String trangThai;

    public Ban(String tenBan, String trangThai) {
        this.tenBan = tenBan;
        this.trangThai = trangThai;
    }

    public String getTenBan() { return tenBan; }
    public void setTenBan(String tenBan) { this.tenBan = tenBan; }
    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
}