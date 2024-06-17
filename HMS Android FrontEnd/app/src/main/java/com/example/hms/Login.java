package com.example.hms;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
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

public class Login extends AppCompatActivity {

    private EditText emailText,passwordText;
    private RadioButton StudentBtn,WardenBtn;
    private RetrofitInterface retrofitInterface;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailText= findViewById(R.id.email);
        passwordText = findViewById(R.id.password);
        StudentBtn = findViewById(R.id.StudentBtn);
        WardenBtn = findViewById(R.id.WardenBtn);
        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valLogin();
            }
        });


    }

    private void valLogin() {
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();
        if (TextUtils.isEmpty(email)) {
            emailText.setError("Email is required.");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            passwordText.setError("Password is required.");
            return;
        }
        if (StudentBtn.isChecked()) {
            handleLoginStudent();
        } else if (WardenBtn.isChecked()) {
            handleLoginWarden();
        }

    }

    private void handleLoginStudent() {
        String email = emailText.getText().toString().trim();
        String password = passwordText.getText().toString().trim();
        retrofit = new Retrofit.Builder().
                baseUrl(MainActivity.BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).
                build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);
        HashMap<String,String> map = new HashMap<>();
        map.put("password",password);
        map.put("email",email);
        Toast.makeText(this, email, Toast.LENGTH_SHORT).show();
        Call<String> call = retrofitInterface.loginStudent(map);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful())
                {
                    SharedPreferences sharedPreferences = getSharedPreferences("auth", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("email", email);
                    editor.putString("type", "student");
                    editor.putString("status", "online");
                    editor.apply();
                    Intent intent =new Intent(Login.this,Student.class);
                    startActivity(intent);
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(Login.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void handleLoginWarden() {
        String email = emailText.getText().toString().trim();
        String password = passwordText.getText().toString().trim();
        retrofit = new Retrofit.Builder().
                baseUrl(MainActivity.BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).
                build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);
        HashMap<String,String> map = new HashMap<>();
        map.put("password",password);
        map.put("email",email);
        Toast.makeText(this, email, Toast.LENGTH_SHORT).show();
        Call<String> call = retrofitInterface.loginWarden(map);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful())
                {
                    SharedPreferences sharedPreferences = getSharedPreferences("auth", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("email", email);
                    editor.putString("type", "warden");
                    editor.putString("status", "online");
                    editor.apply();
                    Intent intent =new Intent(Login.this,Warden.class);
                    startActivity(intent);
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(Login.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}