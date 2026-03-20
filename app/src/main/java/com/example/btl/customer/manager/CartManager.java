package com.example.btl.customer.manager;

import com.example.btl.model.CartItem;
import com.example.btl.model.Food;
import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static CartManager instance;
    private List<CartItem> pendingItems = new ArrayList<>();
    private List<CartItem> orderedItems = new ArrayList<>();

    private CartManager() {}

    public static synchronized CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public void addToCart(Food food) {
        for (CartItem item : pendingItems) {
            if (item.getFood().getId() == food.getId()) {
                item.setQuantity(item.getQuantity() + 1);
                return;
            }
        }
        pendingItems.add(new CartItem(food, 1, false));
    }

    public void confirmOrder() {
        for (CartItem item : pendingItems) {
            item.setOrdered(true);
        }
        orderedItems.addAll(pendingItems);
        pendingItems.clear();
    }

    public double calculateTotal() {
        double total = 0;
        for (CartItem item : orderedItems) {
            total += item.getFood().getPrice() * item.getQuantity();
        }
        return total;
    }

    public List<CartItem> getPendingItems() { return pendingItems; }
    public List<CartItem> getOrderedItems() { return orderedItems; }
}