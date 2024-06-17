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

public class ViewFineWarden extends AppCompatActivity {

    private RetrofitInterface retrofitInterface;
    private Retrofit retrofit;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_fine_warden);

        retrofit = new Retrofit.Builder().
                baseUrl(MainActivity.BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).
                build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Call<List<Fine>> call = retrofitInterface.showFineWarden();
        call.enqueue(new Callback<List<Fine>>() {
            @Override
            public void onResponse(Call<List<Fine>> call, Response<List<Fine>> response) {
                if(response.isSuccessful())
                {
                    List<Fine> FineData= response.body();
                    Toast.makeText(ViewFineWarden.this, "got data"+Integer.toString(FineData.size()), Toast.LENGTH_SHORT).show();
                    FineWardenAdapter FineRecordAdapter = new FineWardenAdapter(ViewFineWarden.this,FineData);
                    recyclerView.setAdapter(FineRecordAdapter);
                }
            }
            @Override
            public void onFailure(Call<List<Fine>> call, Throwable t) {
                Toast.makeText(ViewFineWarden.this, "Cannot get Fines", Toast.LENGTH_SHORT).show();
            }
        });
    }
}