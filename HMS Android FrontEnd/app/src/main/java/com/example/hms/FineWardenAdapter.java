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

public class FineWardenAdapter extends RecyclerView.Adapter<FineWardenAdapter.FineWardenHolder> {
    private List<Fine> FineList;
    private Context context;

    public FineWardenAdapter(Context context,List<Fine> FineList) {
        this.FineList = FineList;
        this.context=context;
    }

    @NonNull
    @Override
    public FineWardenHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fine_warden, parent, false);
        return new FineWardenHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FineWardenHolder holder, int position) {
        Fine fine = FineList.get(position);
        holder.name.setText("Name: "+ fine.getName());
        holder.reason.setText("Reason: " +fine.getReason());
        holder.date.setText("Date: "+fine.getDate());
        holder.amount.setText("Fine Amount: "+fine.getAmount());
        holder.rollNo.setText("Roll No: "+fine.getRollNo());
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, ShowLeaveData.class);
//                intent.putExtra("LeaveId",leave.get_id());
//                context.startActivity(intent);
//            }
//        });

    }
    @Override
    public int getItemCount() {
        return FineList.size();
    }

    public static class FineWardenHolder extends RecyclerView.ViewHolder {
        TextView name, rollNo, amount, date,reason;

        public FineWardenHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            rollNo = itemView.findViewById(R.id.rollNo);
            amount = itemView.findViewById(R.id.amount);
            date = itemView.findViewById(R.id.date);
            reason = itemView.findViewById(R.id.reason);
        }
    }
}

