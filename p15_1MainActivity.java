package com.example.p15_1;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.os.VibratorManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView timerText;
    EditText inputTime;
    Button startBtn, resetBtn, lockedBtn;

    CountDownTimer countDownTimer;
    long timeInMillis = 10000; // default 10 seconds

    MediaPlayer mediaPlayer;
    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerText = findViewById(R.id.timerText);
        inputTime = findViewById(R.id.inputTime);
        startBtn = findViewById(R.id.startBtn);
        resetBtn = findViewById(R.id.resetBtn);
        lockedBtn = findViewById(R.id.lockedBtn);

        mediaPlayer = MediaPlayer.create(this, R.raw.beep);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            VibratorManager vibratorManager =
                    (VibratorManager) getSystemService(VIBRATOR_MANAGER_SERVICE);
            vibrator = vibratorManager.getDefaultVibrator();
        } else {
            vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        }

        lockedBtn.setEnabled(false);

        startBtn.setOnClickListener(v -> startTimer());
        resetBtn.setOnClickListener(v -> resetTimer());
    }

    private void startTimer() {

        if (!inputTime.getText().toString().isEmpty()) {
            int seconds = Integer.parseInt(inputTime.getText().toString());
            timeInMillis = seconds * 1000L;
        }

        lockedBtn.setEnabled(false);

        countDownTimer = new CountDownTimer(timeInMillis, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timerText.setText("Time Left: " + millisUntilFinished / 1000 + " sec");
            }

            @Override
            public void onFinish() {
                timerText.setText("Time’s up!");
                lockedBtn.setEnabled(true);

                // Play sound
                if (mediaPlayer != null) {
                    mediaPlayer.start();
                }

                // Vibrate (fixed & safe)
                if (vibrator != null && vibrator.hasVibrator()) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        vibrator.vibrate(
                                VibrationEffect.createOneShot(
                                        500,
                                        VibrationEffect.DEFAULT_AMPLITUDE
                                )
                        );
                    } else {
                        vibrator.vibrate(500);
                    }
                }
            }
        }.start();
    }

    private void resetTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        timerText.setText("10");
        inputTime.setText("");
        lockedBtn.setEnabled(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
