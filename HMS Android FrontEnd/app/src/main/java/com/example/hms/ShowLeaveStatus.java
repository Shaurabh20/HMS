package com.example.hms;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowLeaveStatus extends AppCompatActivity {

    private RetrofitInterface retrofitInterface;
    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private String rollNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_leave_status);
//        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
//        String email = sharedPreferences.getString("email", null);
//        retrofit = new Retrofit.Builder().
//                baseUrl(MainActivity.BASE_URL).
//                addConverterFactory(GsonConverterFactory.create()).
//                build();
//        retrofitInterface = retrofit.create(RetrofitInterface.class);
//        recyclerView = findViewById(R.id.recyclerView);
//        HashMap<String, String> map = new HashMap<>();
//        map.put("email", email);
//        Call<StudentData> call = retrofitInterface.getStudent(map);
//        call.enqueue(new Callback<StudentData>() {
//            @Override
//            public void onResponse(Call<StudentData> call, Response<StudentData> response) {
//                if (response.isSuccessful()) {
//                    StudentData StudentData = response.body();
//                    rollNo = StudentData.getRollNo();
//                    Toast.makeText(ShowLeaveStatus.this, email+rollNo, Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<StudentData> call, Throwable t) {
//                Toast.makeText(ShowLeaveStatus.this, "Cannot get Data", Toast.LENGTH_SHORT).show();
//            }
//        });
//        getLeaveDetails();
//
//    }
//
//    private void getLeaveDetails() {
//        retrofit = new Retrofit.Builder().
//                baseUrl(MainActivity.BASE_URL).
//                addConverterFactory(GsonConverterFactory.create()).
//                build();
//        retrofitInterface = retrofit.create(RetrofitInterface.class);
//        recyclerView = findViewById(R.id.recyclerView);
//        HashMap<String,String> map = new HashMap<>();
//        map.put("rollNo",rollNo);
//        Call<List<Leave>> call = retrofitInterface.getLeaveStatus(map);
//        call.enqueue(new Callback<List<Leave>>() {
//            @Override
//            public void onResponse(Call<List<Leave>> call, Response<List<Leave>> response) {
//                if(response.isSuccessful())
//                {
//                    List<Leave> leaveData= response.body();
//                    Toast.makeText(ShowLeaveStatus.this, "got data"+Integer.toString(leaveData.size()), Toast.LENGTH_SHORT).show();
//                    LeaveStatusAdapter leaveRecordAdapter = new LeaveStatusAdapter(ShowLeaveStatus.this,leaveData);
//                    recyclerView.setAdapter(leaveRecordAdapter);
//                }
//            }
//            @Override
//            public void onFailure(Call<List<Leave>> call, Throwable t) {
//                Toast.makeText(ShowLeaveStatus.this, "Cannot get Leaves", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    }

}