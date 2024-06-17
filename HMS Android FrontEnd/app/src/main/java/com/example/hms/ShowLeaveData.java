package com.example.hms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowLeaveData extends AppCompatActivity {

    private String id;
    private TextView rollNo, name,course,branch,batch,room,days,fromDate,toDate,reason;
    private RetrofitInterface retrofitInterface;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_leave_data);
        Intent intent= getIntent();
        id = intent.getStringExtra("LeaveId");
        rollNo = findViewById(R.id.rollNo);
        name = findViewById(R.id.name);
        course = findViewById(R.id.course);
        branch = findViewById(R.id.branch);
        batch = findViewById(R.id.batch);
        room = findViewById(R.id.room);
        days = findViewById(R.id.days);
        fromDate = findViewById(R.id.fromDate);
        toDate = findViewById(R.id.toDate);
        reason = findViewById(R.id.reason);
        retrofit = new Retrofit.Builder().
                baseUrl(MainActivity.BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).
                build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);
        HashMap<String,String> map = new HashMap<>();
        map.put("id",id);
        Call<Leave> call = retrofitInterface.getLeaveDetails(map);
        call.enqueue(new Callback<Leave>() {
            @Override
            public void onResponse(Call<Leave> call, Response<Leave> response) {
                if(response.isSuccessful()) {
                    Leave leave = response.body();
                    rollNo.setText("Roll Number: " +leave.getRollNo());
                    name.setText("Name: "+leave.getName());
                    course.setText("Course: "+leave.getCourse());
                    branch.setText("Branch: "+leave.getBranch());
                    batch.setText("Batch: "+leave.getBatch());
                    room.setText("Room Number: "+leave.getRoom());
                    days.setText("No. of Days: "+leave.getDays());
                    fromDate.setText("From Date: "+leave.getFromDate());
                    toDate.setText("To Date: "+leave.getToDate());
                    reason.setText("Reason: "+leave.getReason());
                }
                }
            @Override
            public void onFailure(Call<Leave> call, Throwable t) {
                Toast.makeText(ShowLeaveData.this, "Error fetching data "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.approveLeave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleChangeStatus("Approved");
            }
        });
        findViewById(R.id.denyLeave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleChangeStatus("Denied");
            }
        });
    }
    private void handleChangeStatus(String status)
    {
        retrofit = new Retrofit.Builder().
                baseUrl(MainActivity.BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).
                build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);
        HashMap<String,String> map = new HashMap<>();
        map.put("id",id);
        map.put("status",status);
        Call<String> call = retrofitInterface.updateLeaveStatus(map);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful())
                {
                    String str = response.body();
                    Toast.makeText(ShowLeaveData.this, str, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(ShowLeaveData.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}