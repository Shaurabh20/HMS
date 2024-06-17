package com.example.hms;

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

public class ViewLeaves extends AppCompatActivity {

    private RetrofitInterface retrofitInterface;
    private Retrofit retrofit;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_leaves);

        retrofit = new Retrofit.Builder().
                baseUrl(MainActivity.BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).
                build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Call<List<Leave>> call = retrofitInterface.getLeaves();
        call.enqueue(new Callback<List<Leave>>() {
            @Override
            public void onResponse(Call<List<Leave>> call, Response<List<Leave>> response) {
                if(response.isSuccessful())
                {
                    List<Leave> leaveData= response.body();
                    Toast.makeText(ViewLeaves.this, "got data"+Integer.toString(leaveData.size()), Toast.LENGTH_SHORT).show();
                    LeaveAdapter leaveRecordAdapter = new LeaveAdapter(ViewLeaves.this,leaveData);
                    recyclerView.setAdapter(leaveRecordAdapter);
                }
            }
            @Override
            public void onFailure(Call<List<Leave>> call, Throwable t) {
                Toast.makeText(ViewLeaves.this, "Cannot get Leaves", Toast.LENGTH_SHORT).show();
            }
        });
    }
}