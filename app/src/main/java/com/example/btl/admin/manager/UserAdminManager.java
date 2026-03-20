package com.example.btl.admin.manager;

import android.content.Context;
import com.example.btl.model.User;
import java.util.ArrayList;
import java.util.List;

public class UserAdminManager {

    private static UserAdminManager instance;
    private final List<User> userList;

    private UserAdminManager(Context context) {
        this.userList = new ArrayList<>();
        fakeData();
    }

    public static synchronized UserAdminManager getInstance(Context context) {
        if (instance == null) {
            instance = new UserAdminManager(context.getApplicationContext());
        }
        return instance;
    }

    private void fakeData() {
        userList.clear();
        userList.add(new User(1, "admin", "123", "Admin", "0123456789", "admin"));
        userList.add(new User(2, "ql1", "123", "Quản lý 1", "0987654321", "quanly"));
        userList.add(new User(3, "nv1", "123", "Nhân viên 1", "0911111111", "nhanvien"));
        userList.add(new User(4, "cus1", "123", "Khách 1", "0922222222", "customer"));
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(userList);
    }

    public int getTotalUsers() {
        return userList.size();
    }

    public User findByUsername(String username) {
        for (User user : userList) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        return null;
    }

    public User getUserById(int id) {
        for (User user : userList) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public boolean addUser(User user) {
        if (findByUsername(user.getUsername()) != null) {
            return false;
        }
        userList.add(user);
        return true;
    }

    public boolean updateUser(User updatedUser) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId() == updatedUser.getId()) {
                userList.set(i, updatedUser);
                return true;
            }
        }
        return false;
    }

    public boolean deleteUser(int userId) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId() == userId) {
                userList.remove(i);
                return true;
            }
        }
        return false;
    }
}