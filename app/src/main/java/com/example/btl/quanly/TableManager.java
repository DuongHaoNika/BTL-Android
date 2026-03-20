package com.example.btl.quanly;

import com.example.btl.model.Table;
import java.util.ArrayList;
import java.util.List;

public class TableManager {
    private static TableManager instance;
    private List<Table> tableList;

    private TableManager() {
        tableList = new ArrayList<>();
        fakeData();
    }

    public static synchronized TableManager getInstance() {
        if (instance == null) {
            instance = new TableManager();
        }
        return instance;
    }

    private void fakeData() {
        tableList.add(new Table(1, "Bàn 1", 4, 1, "Phòng A", "Có khách"));
        tableList.add(new Table(2, "Bàn 2", 2, 1, "Phòng A", "Trống"));
        tableList.add(new Table(3, "Bàn 3", 6, 2, "Phòng B", "Đã đặt 8h"));
    }

    public List<Table> getAllTables() {
        return new ArrayList<>(tableList);
    }

    public Table getTableById(int id) {
        for (Table table : tableList) {
            if (table.getId() == id) {
                return table;
            }
        }
        return null;
    }

    public void addTable(Table table) {
        tableList.add(table);
    }

    public void updateTable(Table updatedTable) {
        for (int i = 0; i < tableList.size(); i++) {
            if (tableList.get(i).getId() == updatedTable.getId()) {
                tableList.set(i, updatedTable);
                return;
            }
        }
    }

    public void deleteTable(int id) {
        tableList.removeIf(table -> table.getId() == id);
    }
}