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

public class AllotRoom extends AppCompatActivity {

    EditText rollNo, name,room;
    Retrofit retrofit;
    RetrofitInterface retrofitInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allot_room);
        rollNo = findViewById(R.id.rollNo);
        room = findViewById(R.id.room);
        findViewById(R.id.allotRoomBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allotRoom();
            }
        });
    }
    private void allotRoom() {
        retrofit = new Retrofit.Builder().
                baseUrl(MainActivity.BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).
                build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);
        HashMap<String,String> map = new HashMap<>();
        map.put("rollNo",rollNo.getText().toString());
        map.put("room",room.getText().toString());
        Call<String> call = retrofitInterface.allotRoom(map);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful())
                {
                    Toast.makeText(AllotRoom.this, "Room Allotted successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AllotRoom.this,Warden.class);
                    startActivity(intent);
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(AllotRoom.this, "Error while allotting room", Toast.LENGTH_SHORT).show();
            }
        });
    }
}