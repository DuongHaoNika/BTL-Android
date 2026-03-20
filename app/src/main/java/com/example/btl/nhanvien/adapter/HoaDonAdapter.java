package com.example.btl.nhanvien.adapter;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.btl.R;
import com.example.btl.nhanvien.model_nv.HoaDon;
import java.util.List;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.ViewHolder> {
    private List<HoaDon> list;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(HoaDon hoaDon);
    }

    public HoaDonAdapter(List<HoaDon> list, OnItemClickListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_nhanvien_hoadon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HoaDon hd = list.get(position);
        holder.txtMa.setText("Hóa đơn #" + hd.getMa());
        holder.txtBan.setText(hd.getBan());
        holder.txtTrangThai.setText(hd.getTrangThai());

        GradientDrawable drawable = (GradientDrawable) holder.txtTrangThai.getBackground();
        String trangThai = hd.getTrangThai();

        if (trangThai.equals("Mới tạo")) {
            drawable.setColor(Color.parseColor("#E0E0E0"));
            holder.txtTrangThai.setTextColor(Color.BLACK);
        } else if (trangThai.equals("Chờ xác nhận")) {
            drawable.setColor(Color.parseColor("#FFD600"));
            holder.txtTrangThai.setTextColor(Color.BLACK);
        } else if (trangThai.equals("Đang chế biến")) {
            drawable.setColor(Color.parseColor("#80D8FF"));
            holder.txtTrangThai.setTextColor(Color.BLACK);
        } else {
            drawable.setColor(Color.parseColor("#E0E0E0"));
            holder.txtTrangThai.setTextColor(Color.BLACK);
        }

        holder.itemView.setOnClickListener(v -> listener.onItemClick(hd));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void xacNhanTatCa() {
        for (HoaDon i : list) {
            if (i.getTrangThai().equals("Chờ xác nhận")) {
                i.setTrangThai("Đang chế biến");
            }
        }
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtMa, txtBan, txtTrangThai;

        public ViewHolder(View view) {
            super(view);
            txtMa = view.findViewById(R.id.txtMa);
            txtBan = view.findViewById(R.id.txtBan);
            txtTrangThai = view.findViewById(R.id.txtTrangThai);
        }
    }
}