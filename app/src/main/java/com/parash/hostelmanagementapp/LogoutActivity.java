package com.parash.hostelmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class LogoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreyout.activity_logout);
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        finish();
    }
}
