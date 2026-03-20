package com.example.btl.quanly;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.btl.R;
import com.example.btl.model.HoaDon;
import java.util.List;

public class InvoiceAdapter extends RecyclerView.Adapter<InvoiceAdapter.ViewHolder> {

    private List<HoaDon> invoices;

    public InvoiceAdapter(List<HoaDon> invoices) {
        this.invoices = invoices;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_invoice, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HoaDon invoice = invoices.get(position);
        holder.tvInvoiceCode.setText("Mã HD: " + invoice.getCode());
        holder.tvInvoiceDate.setText(invoice.getCreatedDate());
        holder.tvInvoiceAmount.setText(String.format("Tổng: %,.0f đ", invoice.getTotalAmount()));
        holder.tvInvoiceStatus.setText(invoice.getStatus());
    }

    @Override
    public int getItemCount() {
        return invoices.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvInvoiceCode, tvInvoiceDate, tvInvoiceAmount, tvInvoiceStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvInvoiceCode = itemView.findViewById(R.id.tvInvoiceCode);
            tvInvoiceDate = itemView.findViewById(R.id.tvInvoiceDate);
            tvInvoiceAmount = itemView.findViewById(R.id.tvInvoiceAmount);
            tvInvoiceStatus = itemView.findViewById(R.id.tvInvoiceStatus);
        }
    }
}