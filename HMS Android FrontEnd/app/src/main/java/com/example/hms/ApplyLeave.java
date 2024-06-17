package com.example.hms;

import android.content.Intent;
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

public class ApplyLeave extends AppCompatActivity {

    EditText rollNo, name,course,branch,batch,room,days,fromDate,toDate,reason;
    Retrofit retrofit;
    RetrofitInterface retrofitInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_leave);
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
        findViewById(R.id.applyLeave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyLeave();
            }
        });
    }
    private void applyLeave() {
        retrofit = new Retrofit.Builder().
                baseUrl(MainActivity.BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).
                build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);
        HashMap<String,String> map = new HashMap<>();
        map.put("rollNo",rollNo.getText().toString());
        map.put("name",name.getText().toString());
        map.put("course",course.getText().toString());
        map.put("branch",branch.getText().toString());
        map.put("batch",batch.getText().toString());
        map.put("room",room.getText().toString());
        map.put("days",days.getText().toString());
        map.put("fromDate",fromDate.getText().toString());
        map.put("toDate",toDate.getText().toString());
        map.put("reason",reason.getText().toString());
        Call<String> call = retrofitInterface.applyLeave(map);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful())
                {
                    Toast.makeText(ApplyLeave.this, "Successfully Apllied For Leave", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ApplyLeave.this,Student.class);
                    startActivity(intent);
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(ApplyLeave.this, "Could not Apply for leave", Toast.LENGTH_SHORT).show();
            }
        });
    }
}