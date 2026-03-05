package com.example.p21;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    ModeReceiver receiver;

    Button silentBtn, loudBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        silentBtn = findViewById(R.id.btnSilent);
        loudBtn = findViewById(R.id.btnLoud);

        receiver = new ModeReceiver();

        // Dynamic Registration
        IntentFilter filter =
                new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);

        registerReceiver(receiver, filter);

        silentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ModeReceiver.setSilent(MainActivity.this);
            }
        });

        loudBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ModeReceiver.setLoud(MainActivity.this);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
