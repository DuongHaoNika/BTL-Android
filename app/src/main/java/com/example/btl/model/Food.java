package com.example.btl.model;

public class Food {
    private int id;
    private String name;
    private double price;
    private String description;
    private String imageUrl;
    private boolean available;

    public Food() {
    }

    public Food(int id, String name, double price, String description, String imageUrl, boolean available) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}