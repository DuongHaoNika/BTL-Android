package com.example.btl.model;

import java.io.Serializable;

/**
 * Model đại diện cho một món ăn trong giỏ hàng.
 * Bao gồm thông tin món ăn, số lượng và trạng thái đã gửi bếp hay chưa.
 */
public class CartItem implements Serializable {
    private Food food;      // Đối tượng món ăn
    private int quantity;   // Số lượng món
    private boolean isOrdered; // Trạng thái: false (mới thêm vào giỏ), true (đã nhấn gọi món/gửi bếp)

    // Constructor đầy đủ
    public CartItem(Food food, int quantity, boolean isOrdered) {
        this.food = food;
        this.quantity = quantity;
        this.isOrdered = isOrdered;
    }

    // Getter và Setter cho Food
    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    // Getter và Setter cho số lượng
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Kiểm tra trạng thái đã gọi món chưa
    public boolean isOrdered() {
        return isOrdered;
    }

    // Cập nhật trạng thái gọi món
    public void setOrdered(boolean ordered) {
        isOrdered = ordered;
    }
}