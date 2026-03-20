package com.example.btl.admin.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl.R;

import java.util.List;

public class LogAdapter extends RecyclerView.Adapter<LogAdapter.LogViewHolder> {

    private List<String> logList;

    public LogAdapter(List<String> logList) {
        this.logList = logList;
    }

    @NonNull
    @Override
    public LogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_log, parent, false);
        return new LogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LogViewHolder holder, int position) {
        String log = logList.get(position);
        holder.txtLog.setText(log);
    }

    @Override
    public int getItemCount() {
        return logList.size();
    }

    // ================= VIEW HOLDER =================
    public static class LogViewHolder extends RecyclerView.ViewHolder {

        TextView txtLog;

        public LogViewHolder(@NonNull View itemView) {
            super(itemView);
            txtLog = itemView.findViewById(R.id.txtLog);
        }
    }

    // ================= UPDATE DATA =================
    public void updateData(List<String> newLogs) {
        this.logList = newLogs;
        notifyDataSetChanged();
    }
}