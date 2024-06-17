package com.example.hms;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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

public class RaiseComplaint extends AppCompatActivity {

    private EditText complaint,roomNo;
    private RetrofitInterface retrofitInterface;
    private Retrofit retrofit;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raise_complaint);
        SharedPreferences sharedPreferences = getSharedPreferences("auth", Context.MODE_PRIVATE);
        email=sharedPreferences.getString("email", null);
        complaint = findViewById(R.id.complaint);
        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrofit = new Retrofit.Builder().
                        baseUrl(MainActivity.BASE_URL).
                        addConverterFactory(GsonConverterFactory.create()).
                        build();
                retrofitInterface = retrofit.create(RetrofitInterface.class);
                HashMap<String,String> map = new HashMap<>();
                map.put("email",email);
                map.put("complaint",complaint.getText().toString());
                map.put("roomNo",roomNo.getText().toString());
                Call<String> call = retrofitInterface.raiseComplaint(map);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if(response.isSuccessful())
                        {
                            Toast.makeText(RaiseComplaint.this, "Complaint Raised", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(RaiseComplaint.this, "Error in raising complaint", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}