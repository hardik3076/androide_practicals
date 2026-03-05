package com.example.p21;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.widget.Toast;

public class ModeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (Intent.ACTION_AIRPLANE_MODE_CHANGED.equals(intent.getAction())) {

            boolean isOn = intent.getBooleanExtra("state", false);

            if (isOn) {
                Toast.makeText(context, "Airplane Mode ON", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, "Airplane Mode OFF", Toast.LENGTH_LONG).show();
            }
        }
    }

    // Function to set Silent Mode
    public static void setSilent(Context context) {

        AudioManager audioManager =
                (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

        if (audioManager != null) {
            audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
            Toast.makeText(context, "Silent Mode Activated", Toast.LENGTH_SHORT).show();
        }
    }

    // Function to set Loud Mode
    public static void setLoud(Context context) {

        AudioManager audioManager =
                (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

        if (audioManager != null) {
            audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
            Toast.makeText(context, "Loud Mode Activated", Toast.LENGTH_SHORT).show();
        }
    }
}
/*
app
        ├── java
        │    └── com.example.p21
        │          ├── MainActivity.java
        │          └── ModeReceiver.java
        │
        ├── res
        │    └── layout
        │          └── activity_main.xml
        │
        └── manifests
        └── AndroidManifest.xml
*/
