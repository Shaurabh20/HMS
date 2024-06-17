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

public class AddStudent extends AppCompatActivity {

    EditText rollNo, name,email,phone,address,aadhar,tempPassword,course,branch,batch;
    Retrofit retrofit;
    RetrofitInterface retrofitInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        rollNo = findViewById(R.id.rollNo);
        name = findViewById(R.id.name);
        course = findViewById(R.id.course);
        branch = findViewById(R.id.branch);
        aadhar = findViewById(R.id.aadhar);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        address = findViewById(R.id.address);
        batch = findViewById(R.id.batch);
        tempPassword = findViewById(R.id.tempPassword);
        findViewById(R.id.addStudent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleAddStudent();
            }
        });
    }
    private void handleAddStudent() {
        retrofit = new Retrofit.Builder().
                baseUrl(MainActivity.BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).
                build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);
        HashMap<String,String> map = new HashMap<>();
        map.put("rollNo",rollNo.getText().toString());
        map.put("name",name.getText().toString());
        map.put("email",email.getText().toString());
        map.put("phone",phone.getText().toString());
        map.put("address",address.getText().toString());
        map.put("aadhar",aadhar.getText().toString());
        map.put("tempPassword",tempPassword.getText().toString());
        map.put("course",course.getText().toString());
        map.put("branch",branch.getText().toString());
        map.put("batch",batch.getText().toString());
        Call<String> call = retrofitInterface.addStudent(map);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful())
                {
                    Toast.makeText(AddStudent.this, "Student added successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddStudent.this,Warden.class);
                    startActivity(intent);
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(AddStudent.this, "Error while imposing fine", Toast.LENGTH_SHORT).show();
            }
        });
    }
}