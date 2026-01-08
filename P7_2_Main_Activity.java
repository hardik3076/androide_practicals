package com.example.p7_2;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    TextView tvStatus;
    Button btnDownload;

    int progress = 0;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        tvStatus = findViewById(R.id.tvStatus);
        btnDownload = findViewById(R.id.btnDownload);

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress = 0;
                progressBar.setProgress(0);
                tvStatus.setText("Downloading...");
                startDownload();
            }
        });
    }

    private void startDownload() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progress < 100) {
                    progress += 10;

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progress);
                            tvStatus.setText("Downloading: " + progress + "%");
                        }
                    });

                    try {
                        Thread.sleep(1000); // Simulate download time
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        tvStatus.setText("Download Complete");
                    }
                });
            }
        }).start();
    }
}
