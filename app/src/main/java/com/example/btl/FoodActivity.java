package com.example.btl;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl.adapter.UserAdapter;
import com.example.btl.model.User;

import java.util.ArrayList;

public class FoodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_food);

        RecyclerView rv = findViewById(R.id.rvUsers);

        // 1) LayoutManager (list dọc)
        rv.setLayoutManager(new LinearLayoutManager(this));

        // 2) Data
        ArrayList<User> list = new ArrayList<>();
        list.add(new User("Hao", "Mô tả 1"));
        list.add(new User("Minh", "Mô tả 2"));
        list.add(new User("Lan", "Mô tả 3"));

        // 3) Adapter
        UserAdapter adapter = new UserAdapter(list);
        rv.setAdapter(adapter);
    }
}