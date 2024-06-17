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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewFineStudent extends AppCompatActivity {

    private RetrofitInterface retrofitInterface;
    private Retrofit retrofit;
    private String rollNo;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_fine_warden);
        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        String email = sharedPreferences.getString("email", null);
        retrofit = new Retrofit.Builder().
                baseUrl(MainActivity.BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).
                build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);
        recyclerView = findViewById(R.id.recyclerView);
        HashMap<String, String> map = new HashMap<>();
        map.put("email", email);
        Call<StudentData> call = retrofitInterface.getStudent(map);
        call.enqueue(new Callback<StudentData>() {
            @Override
            public void onResponse(Call<StudentData> call, Response<StudentData> response) {
                if (response.isSuccessful()) {
                    StudentData StudentData = response.body();
                    rollNo = StudentData.getRollNo();
                }
            }

            @Override
            public void onFailure(Call<StudentData> call, Throwable t) {
                Toast.makeText(ViewFineStudent.this, "Cannot get Data", Toast.LENGTH_SHORT).show();
            }
        });
        getFine();

    }
    private void getFine()
    {
        retrofit = new Retrofit.Builder().
                baseUrl(MainActivity.BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).
                build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        HashMap<String,String> map = new HashMap<>();
        map.put("rollNo",rollNo);
        Call<List<Fine>> call = retrofitInterface.showFineStudent(map);
        call.enqueue(new Callback<List<Fine>>() {
            @Override
            public void onResponse(Call<List<Fine>> call, Response<List<Fine>> response) {
                if(response.isSuccessful())
                {
                    List<Fine> FineData= response.body();
                    Toast.makeText(ViewFineStudent.this, "got data"+Integer.toString(FineData.size()), Toast.LENGTH_SHORT).show();
                    FineStudentAdapter FineRecordAdapter = new FineStudentAdapter(ViewFineStudent.this,FineData);
                    recyclerView.setAdapter(FineRecordAdapter);
                }
            }
            @Override
            public void onFailure(Call<List<Fine>> call, Throwable t) {
                Toast.makeText(ViewFineStudent.this, "Cannot get Fines", Toast.LENGTH_SHORT).show();
            }
        });
    }
}