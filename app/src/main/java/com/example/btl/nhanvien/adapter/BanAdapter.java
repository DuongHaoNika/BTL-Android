package com.example.btl.nhanvien.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.example.btl.R;
import com.example.btl.nhanvien.model_nv.Ban;
import java.util.List;

public class BanAdapter extends RecyclerView.Adapter<BanAdapter.ViewHolder> {
    private List<Ban> list;

    public BanAdapter(List<Ban> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_nhanvien_ban, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ban ban = list.get(position);
        holder.txtTen.setText(ban.getTenBan());
        holder.txtTrangThai.setText(ban.getTrangThai().toUpperCase());

        Context context = holder.itemView.getContext();
        String trangThai = ban.getTrangThai();

        if (trangThai.equals("Trống")) {
            holder.layout.setBackgroundColor(ContextCompat.getColor(context, R.color.green_trong));
        } else if (trangThai.equals("Có khách")) {
            holder.layout.setBackgroundColor(ContextCompat.getColor(context, R.color.red_co_khach));
        } else if (trangThai.equals("Đang dọn")) {
            holder.layout.setBackgroundColor(ContextCompat.getColor(context, R.color.yellow_dang_don));
        }

        holder.btnMenu.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(context, holder.btnMenu);
            popup.getMenu().add("Trống");
            popup.getMenu().add("Có khách");
            popup.getMenu().add("Đang dọn");

            popup.setOnMenuItemClickListener(item -> {
                ban.setTrangThai(item.getTitle().toString());
                notifyItemChanged(position);
                return true;
            });
            popup.show();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTen, txtTrangThai;
        public ImageView btnMenu;
        public View layout;

        public ViewHolder(View view) {
            super(view);
            txtTen = view.findViewById(R.id.txtTenBan);
            txtTrangThai = view.findViewById(R.id.txtTrangThai);
            btnMenu = view.findViewById(R.id.btnMenu);
            layout = view.findViewById(R.id.layoutBackground);
        }
    }
}