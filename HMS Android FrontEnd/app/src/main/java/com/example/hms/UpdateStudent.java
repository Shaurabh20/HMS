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

public class UpdateStudent extends AppCompatActivity {

    EditText rollNo, name,email,phone,address,aadhar,course,branch,batch,room;
    Retrofit retrofit;
    RetrofitInterface retrofitInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);
        rollNo = findViewById(R.id.rollNo);
        name = findViewById(R.id.name);
        course = findViewById(R.id.course);
        branch = findViewById(R.id.branch);
        aadhar = findViewById(R.id.aadhar);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        address = findViewById(R.id.address);
        batch = findViewById(R.id.batch);
        room = findViewById(R.id.room);
        findViewById(R.id.updateStudent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hnadleUpdateStudent();
            }
        });
    }
    private void hnadleUpdateStudent() {
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
        map.put("course",course.getText().toString());
        map.put("branch",branch.getText().toString());
        map.put("batch",batch.getText().toString());
        map.put("room",room.getText().toString());
        Call<String> call = retrofitInterface.updateStudent(map);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful())
                {
                    Toast.makeText(UpdateStudent.this, "Student Data updated Successfully", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(UpdateStudent.this, "Error in updating student data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}