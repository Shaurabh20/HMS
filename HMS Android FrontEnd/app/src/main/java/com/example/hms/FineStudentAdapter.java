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

public class FineStudentAdapter extends RecyclerView.Adapter<FineStudentAdapter.FineStudentHolder> {
    private List<Fine> FineList;
    private Context context;

    public FineStudentAdapter(Context context,List<Fine> FineList) {
        this.FineList = FineList;
        this.context=context;
    }

    @NonNull
    @Override
    public FineStudentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fine_student, parent, false);
        return new FineStudentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FineStudentHolder holder, int position) {
        Fine fine = FineList.get(position);
        holder.reason.setText("Reason: " +fine.getReason());
        holder.date.setText("Date: "+fine.getDate());
        holder.amount.setText("Fine Amount: "+fine.getAmount());
    }
    @Override
    public int getItemCount() {
        return FineList.size();
    }

    public static class FineStudentHolder extends RecyclerView.ViewHolder {
        TextView amount, date,reason;

        public FineStudentHolder(@NonNull View itemView) {
            super(itemView);
            amount = itemView.findViewById(R.id.amount);
            date = itemView.findViewById(R.id.date);
            reason = itemView.findViewById(R.id.reason);
        }
    }
}

