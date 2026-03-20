package com.example.btl.admin.manager;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

public class LogManager {

    private static LogManager instance;
    private final List<String> logList;

    private LogManager(Context context) {
        this.logList = new ArrayList<>();
        fakeData();
    }

    public static synchronized LogManager getInstance(Context context) {
        if (instance == null) {
            instance = new LogManager(context.getApplicationContext());
        }
        return instance;
    }

    private void fakeData() {
        logList.clear();
        logList.add("Hệ thống khởi động");
        logList.add("Admin đăng nhập");
    }

    public List<String> getAllLogs() {
        return new ArrayList<>(logList);
    }

    public void addLog(String log) {
        logList.add(0, log);
    }

    public void clearLogs() {
        logList.clear();
    }
}