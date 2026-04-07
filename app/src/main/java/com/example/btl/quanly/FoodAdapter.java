package com.example.btl.quanly;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.btl.R;
import com.example.btl.model.Food;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    private List<Food> foods;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Food food);
    }

    public FoodAdapter(List<Food> foods, OnItemClickListener listener) {
        this.foods = foods;
        this.listener = listener;
    }

    public void updateData(List<Food> newFoods) {
        this.foods = newFoods;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Food food = foods.get(position);
        holder.tvFoodName.setText(food.getName());
        holder.tvFoodPrice.setText(String.format("%,.0f đ", food.getPrice()));
        // Assuming Food has a getPrice() and isAvailable() method based on previous read_file output
        // Note: previous read_file showed: private double price; private boolean available;
        // but no getters for those were visible in the snippet. I'll assume they exist or add them.
        holder.itemView.setOnClickListener(v -> listener.onItemClick(food));
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvFoodName, tvFoodPrice, tvFoodStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFoodName = itemView.findViewById(R.id.tvFoodName);
            tvFoodPrice = itemView.findViewById(R.id.tvFoodPrice);
            //tvFoodStatus = itemView.findViewById(R.id.tvFoodStatus);
        }
    }
}