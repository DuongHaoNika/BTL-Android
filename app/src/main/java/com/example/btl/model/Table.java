package com.example.btl.model;

public class Table {
    private int id;
    private String tableName;
    private int capacity;
    private int floor;
    private String room;
    private String status;

    public Table() {
    }

    public Table(int id, String tableName, int capacity, int floor, String room, String status) {
        this.id = id;
        this.tableName = tableName;
        this.capacity = capacity;
        this.floor = floor;
        this.room = room;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}