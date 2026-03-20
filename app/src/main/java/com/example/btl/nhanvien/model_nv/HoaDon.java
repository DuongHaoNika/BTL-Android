package com.example.btl.nhanvien.model_nv;

public class HoaDon {
    private String ma;
    private String ban;
    private String trangThai;

    public HoaDon(String ma, String ban, String trangThai) {
        this.ma = ma;
        this.ban = ban;
        this.trangThai = trangThai;
    }

    public String getMa() { return ma; }
    public void setMa(String ma) { this.ma = ma; }
    public String getBan() { return ban; }
    public void setBan(String ban) { this.ban = ban; }
    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
}