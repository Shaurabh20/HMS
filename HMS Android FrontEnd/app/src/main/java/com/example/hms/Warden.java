package com.example.hms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Warden extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warden);
        findViewById(R.id.viewLeave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Warden.this, ViewLeaves.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.viewComplaint).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Warden.this, ShowComplaints.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.logOut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Warden.this, Login.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.addStudent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Warden.this, AddStudent.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.imposeFine).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Warden.this, ImposeFine.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.removeStudent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Warden.this, RemoveStudent.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.allotRoom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Warden.this, AllotRoom.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.updateStudent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Warden.this, UpdateStudent.class);
                startActivity(intent);
            }
        });
    }
}