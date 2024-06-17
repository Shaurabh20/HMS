package com.example.hms;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ComplaintAdapter extends RecyclerView.Adapter<ComplaintAdapter.ComplaintViewHolder> {
    private List<Complaint> ComplaintList;
    private Context context;

    public ComplaintAdapter(Context context,List<Complaint> ComplaintList) {
        this.ComplaintList = ComplaintList;
        this.context=context;
    }

    @NonNull
    @Override
    public ComplaintViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.complaint_view, parent, false);
        return new ComplaintViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComplaintViewHolder holder, int position) {
        Complaint Complaint = ComplaintList.get(position);
        holder.email.setText("Email: "+Complaint.getEmail());
        holder.status.setText("Status: " +Complaint.getStatus());
        holder.roomNo.setText("To Date: "+Complaint.getRoomNo());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Student.class);
                intent.putExtra("ComplaintId",Complaint.get_id());
                context.startActivity(intent);
            }
        });

    }
    @Override
    public int getItemCount() {
        return ComplaintList.size();
    }

    public static class ComplaintViewHolder extends RecyclerView.ViewHolder {
        TextView email, roomNo, status;

        public ComplaintViewHolder(@NonNull View itemView) {
            super(itemView);
            email = itemView.findViewById(R.id.email);
            roomNo = itemView.findViewById(R.id.roomNo);
            status = itemView.findViewById(R.id.status);
        }
    }
}

