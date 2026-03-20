package com.example.btl.model;

public class HoaDon {
    private int id;
    private String code;
    private int tableId;
    private int userId;
    private double totalAmount;
    private String createdDate;
    private String status;
    private String paymentMethod;
    private String note;

    public HoaDon() {
    }

    public HoaDon(int id, String code, int tableId, int userId, double totalAmount,
                  String createdDate, String status, String paymentMethod, String note) {
        this.id = id;
        this.code = code;
        this.tableId = tableId;
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.createdDate = createdDate;
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}