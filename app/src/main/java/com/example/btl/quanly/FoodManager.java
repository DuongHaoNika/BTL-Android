package com.example.btl.quanly;

import com.example.btl.model.Food;
import java.util.ArrayList;
import java.util.List;

public class FoodManager {
    private static FoodManager instance;
    private List<Food> foodList;

    private FoodManager() {
        foodList = new ArrayList<>();
        fakeData();
    }

    public static synchronized FoodManager getInstance() {
        if (instance == null) {
            instance = new FoodManager();
        }
        return instance;
    }

    private void fakeData() {
        foodList.add(new Food(1, "Xúc xích", 25000, "Xúc xích nướng", "", true));
        foodList.add(new Food(2, "Lạp xưởng", 30000, "Lạp xưởng tươi", "", true));
    }

    public List<Food> getAllFoods() {
        return new ArrayList<>(foodList);
    }

    public Food getFoodById(int id) {
        for (Food food : foodList) {
            if (food.getId() == id) {
                return food;
            }
        }
        return null;
    }

    public void addFood(Food food) {
        foodList.add(food);
    }

    public void updateFood(Food updatedFood) {
        for (int i = 0; i < foodList.size(); i++) {
            if (foodList.get(i).getId() == updatedFood.getId()) {
                foodList.set(i, updatedFood);
                return;
            }
        }
    }

    public void deleteFood(int id) {
        foodList.removeIf(food -> food.getId() == id);
    }
}