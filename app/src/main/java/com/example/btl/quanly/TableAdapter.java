package com.example.btl.quanly;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.btl.R;
import com.example.btl.model.Table;
import java.util.List;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.ViewHolder> {

    private List<Table> tables;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Table table);
    }

    public TableAdapter(List<Table> tables, OnItemClickListener listener) {
        this.tables = tables;
        this.listener = listener;
    }

    public void updateData(List<Table> newTables) {
        this.tables = newTables;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_table, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Table table = tables.get(position);
        holder.tvTableName.setText(table.getTableName());
        holder.tvTableInfo.setText("Tầng " + table.getFloor() + " - " + table.getRoom() + " - " + table.getCapacity() + " chỗ");
        holder.tvTableStatus.setText(table.getStatus());
        
        // Color status for better UI
        if ("Trống".equalsIgnoreCase(table.getStatus())) {
            holder.tvTableStatus.setTextColor(holder.itemView.getContext().getResources().getColor(android.R.color.holo_green_dark));
        } else {
            holder.tvTableStatus.setTextColor(holder.itemView.getContext().getResources().getColor(android.R.color.holo_red_dark));
        }

        holder.itemView.setOnClickListener(v -> listener.onItemClick(table));
    }

    @Override
    public int getItemCount() {
        return tables.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTableName, tvTableInfo, tvTableStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTableName = itemView.findViewById(R.id.tvTableName);
            tvTableInfo = itemView.findViewById(R.id.tvTableInfo);
            tvTableStatus = itemView.findViewById(R.id.tvTableStatus);
        }
    }
}