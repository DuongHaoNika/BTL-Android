package com.example.btl.quanly;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.btl.R;
import com.example.btl.model.Voucher;
import java.util.List;

public class VoucherAdapter extends RecyclerView.Adapter<VoucherAdapter.ViewHolder> {

    private List<Voucher> vouchers;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Voucher voucher);
    }

    public VoucherAdapter(List<Voucher> vouchers, OnItemClickListener listener) {
        this.vouchers = vouchers;
        this.listener = listener;
    }

    public void updateData(List<Voucher> newVouchers) {
        this.vouchers = newVouchers;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_voucher, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Voucher voucher = vouchers.get(position);
        holder.tvVoucherCode.setText(voucher.getCode());
        holder.tvVoucherName.setText(voucher.getName());
        holder.tvVoucherStatus.setText(voucher.isActive() ? "Đang chạy" : "Tạm dừng");
        holder.tvVoucherStatus.setTextColor(holder.itemView.getContext().getResources().getColor(
                voucher.isActive() ? android.R.color.holo_green_dark : android.R.color.darker_gray
        ));

        holder.itemView.setOnClickListener(v -> listener.onItemClick(voucher));
    }

    @Override
    public int getItemCount() {
        return vouchers.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvVoucherCode, tvVoucherName, tvVoucherStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvVoucherCode = itemView.findViewById(R.id.tvVoucherCode);
            tvVoucherName = itemView.findViewById(R.id.tvVoucherName);
            tvVoucherStatus = itemView.findViewById(R.id.tvVoucherStatus);
        }
    }
}