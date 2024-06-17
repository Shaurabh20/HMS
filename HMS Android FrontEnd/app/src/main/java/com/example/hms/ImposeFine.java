package com.example.hms;

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

public class ImposeFine extends AppCompatActivity {

    EditText rollNo, name,course,branch,batch,amount,reason;
    Retrofit retrofit;
    RetrofitInterface retrofitInterface;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_impose_fine);
        rollNo = findViewById(R.id.rollNo);
        name = findViewById(R.id.name);
        course = findViewById(R.id.course);
        branch = findViewById(R.id.branch);
        batch = findViewById(R.id.batch);
        amount = findViewById(R.id.amount);
        reason = findViewById(R.id.reason);
        findViewById(R.id.imposeBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleImposeFine();
            }
        });
    }
    private void handleImposeFine() {
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
        map.put("amount",amount.getText().toString());
        map.put("reason",reason.getText().toString());
        Call<String> call = retrofitInterface.imposeFine(map);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful())
                {
                    Toast.makeText(ImposeFine.this, "Fine imposed successfully", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(ImposeFine.this, "Error while imposing fine", Toast.LENGTH_SHORT).show();
            }
        });
    }
}