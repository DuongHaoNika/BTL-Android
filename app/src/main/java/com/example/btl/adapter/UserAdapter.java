package com.example.btl.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl.R;
import com.example.btl.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserVH> {

    private final List<User> data;

    public UserAdapter(List<User> data) {
        this.data = data;
    }

    // 1) ViewHolder: giữ view của 1 item để recycle
    static class UserVH extends RecyclerView.ViewHolder {
        TextView tvName, tvDesc;

        public UserVH(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvDesc = itemView.findViewById(R.id.tvDesc);
        }
    }

    // 2) Tạo view item từ XML
    @NonNull
    @Override
    public UserVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false);
        return new UserVH(v);
    }

    // 3) Đổ dữ liệu vào item
    @Override
    public void onBindViewHolder(@NonNull UserVH holder, int position) {
        User u = data.get(position);
        holder.tvName.setText(u.name);
        holder.tvDesc.setText(u.desc);

        // Optional: bắt sự kiện click
        holder.itemView.setOnClickListener(v -> {
            // xử lý click ở đây
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
