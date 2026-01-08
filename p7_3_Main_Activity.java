package com.example.p7_3;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    TextView tvProgress;
    SeekBar seekBar;
    Button btnStart;

    int delay = 50; // default delay (speed)
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        tvProgress = findViewById(R.id.tvProgress);
        seekBar = findViewById(R.id.seekBar);
        btnStart = findViewById(R.id.btnStart);

        // Change download speed using SeekBar
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                delay = 100 - progress; // higher progress = faster download
                if (delay < 10) delay = 10;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        btnStart.setOnClickListener(v -> {
            progressBar.setProgress(0);
            new DownloadTask().execute();
        });
    }

    // AsyncTask for background processing
    private class DownloadTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            for (int i = 1; i <= 100; i++) {
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(i);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int progress = values[0];
            progressBar.setProgress(progress);
            tvProgress.setText("Progress: " + progress + "%");
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            tvProgress.setText("Download Completed!");
        }
    }
}
