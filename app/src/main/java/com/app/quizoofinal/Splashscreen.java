package com.app.quizoofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


public class Splashscreen extends AppCompatActivity {
    private final int Splash_Time = 5000;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splashscreen.this, LoginActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_up_in,R.anim.slide_up_out);
                finish();
            }
        },Splash_Time);
    }
}