package com.example.hms;

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

public class ShowComplaints extends AppCompatActivity {

    private RetrofitInterface retrofitInterface;
    private Retrofit retrofit;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_complaints);

//        retrofit = new Retrofit.Builder().
//                baseUrl(MainActivity.BASE_URL).
//                addConverterFactory(GsonConverterFactory.create()).
//                build();
//        retrofitInterface = retrofit.create(RetrofitInterface.class);
//        recyclerView = findViewById(R.id.recyclerView);
//        Call<List<Complaint>> call = retrofitInterface.getComplaints();
//        call.enqueue(new Callback<List<Complaint>>() {
//            @Override
//            public void onResponse(Call<List<Complaint>> call, Response<List<Complaint>> response) {
//                if(response.isSuccessful())
//                {
//                    List<Complaint> ComplaintData= response.body();
//                    ComplaintAdapter ComplaintRecordAdapter = new ComplaintAdapter(ShowComplaints.this,ComplaintData);
//                    recyclerView.setAdapter(ComplaintRecordAdapter);
//                }
//            }
//            @Override
//            public void onFailure(Call<List<Complaint>> call, Throwable t) {
//                Toast.makeText(ShowComplaints.this, "Cannot get Complaints", Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}