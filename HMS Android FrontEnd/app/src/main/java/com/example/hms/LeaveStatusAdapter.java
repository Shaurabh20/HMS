package com.example.hms;
import android.content.Context;
import android.content.Intent;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class LeaveStatusAdapter extends RecyclerView.Adapter<LeaveStatusAdapter.LeaveViewHolder> {
    private List<Leave> LeaveList;
    private Context context;

    public LeaveStatusAdapter(Context context,List<Leave> LeaveList) {
        this.LeaveList = LeaveList;
        this.context=context;
    }

    @NonNull
    @Override
    public LeaveViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.status_view, parent, false);
        return new LeaveViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LeaveViewHolder holder, int position) {
        Leave leave = LeaveList.get(position);
        holder.name.setText(leave.getName());
        holder.days.setText("No. of days: " +leave.getDays());
        holder.fromDate.setText("From Date: "+leave.getFromDate());
        holder.toDate.setText("To Date: "+leave.getToDate());
        holder.status.setText("Status: "+leave.getStatus());

    }
    @Override
    public int getItemCount() {
        return LeaveList.size();
    }

    public static class LeaveViewHolder extends RecyclerView.ViewHolder {
        TextView name, days, fromDate, toDate,status;

        public LeaveViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            days = itemView.findViewById(R.id.days);
            fromDate = itemView.findViewById(R.id.fromDate);
            toDate = itemView.findViewById(R.id.toDate);
            status = itemView.findViewById(R.id.status);
        }
    }
}

