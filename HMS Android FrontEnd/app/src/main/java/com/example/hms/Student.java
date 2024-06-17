package com.example.hms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Student extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        findViewById(R.id.applyLeave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Student.this,ApplyLeave.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.raiseComplaint).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Student.this,RaiseComplaint.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.logOut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Student.this,Login.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.viewFine).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Student.this,Login.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.viewStatus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Student.this,ShowLeaveStatus.class);
                startActivity(intent);
            }
        });
    }
}