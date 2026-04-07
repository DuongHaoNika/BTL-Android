package com.example.btl.customer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.btl.R;
import com.example.btl.model.CartItem;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<CartItem> itemList;
    private boolean isOrderedType; // true nếu là danh sách món đã gọi

    public CartAdapter(List<CartItem> itemList, boolean isOrderedType) {
        this.itemList = itemList;
        this.isOrderedType = isOrderedType;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem item = itemList.get(position);
        holder.tvName.setText(item.getFood().getName());

        // Hiển thị Giá x Số lượng
        double subTotal = item.getFood().getPrice() * item.getQuantity();
        holder.tvPrice.setText(String.format("%d x %,.0f = %,.0f VNĐ",
                item.getQuantity(), item.getFood().getPrice(), subTotal));

        // Nếu là món đã gọi, đổi icon nút bấm thành "Gọi lại" hoặc ẩn nút tùy ý
        if (isOrderedType) {
            holder.btnAdd.setImageResource(android.R.drawable.ic_menu_rotate);
        }
    }

    @Override
    public int getItemCount() {
        return itemList != null ? itemList.size() : 0;
    }

    static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPrice;
        android.widget.ImageButton btnAdd;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvFoodName);
            tvPrice = itemView.findViewById(R.id.tvFoodPrice);
            btnAdd = itemView.findViewById(R.id.btnAddFood);
        }
    }
}