package com.example.p20;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private BluetoothAdapter bluetoothAdapter;
    private WifiManager wifiManager;
    private TextView tvOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvOutput = findViewById(R.id.tvOutput);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        Button btnBluetoothOn = findViewById(R.id.btnBluetoothOn);
        Button btnPairedDevices = findViewById(R.id.btnPairedDevices);
        Button btnWifiToggle = findViewById(R.id.btnWifiToggle);
        Button btnWifiStatus = findViewById(R.id.btnWifiStatus);

        // 1️⃣ Turn Bluetooth ON
        btnBluetoothOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    turnOnBluetooth();
                } catch (Exception e) {
                    Log.e("MainActivity", "Error turning on Bluetooth", e);
                    tvOutput.setText("Error turning on Bluetooth: " + e.getMessage());
                }
            }
        });

        // 2️⃣ Show Paired Bluetooth Devices
        btnPairedDevices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    showPairedDevices();
                } catch (Exception e) {
                    Log.e("MainActivity", "Error showing paired devices", e);
                    tvOutput.setText("Error showing paired devices: " + e.getMessage());
                }
            }
        });

        // 4️⃣ Toggle Wi-Fi
        btnWifiToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    toggleWifi();
                } catch (Exception e) {
                    Log.e("MainActivity", "Error toggling Wi-Fi", e);
                    tvOutput.setText("Error toggling Wi-Fi: " + e.getMessage());
                }
            }
        });

        // 5️⃣ Check Wi-Fi Connection Status
        btnWifiStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    checkWifiStatus();
                } catch (Exception e) {
                    Log.e("MainActivity", "Error checking Wi-Fi status", e);
                    tvOutput.setText("Error checking Wi-Fi status: " + e.getMessage());
                }
            }
        });
    }

    // Turn Bluetooth ON
    private void turnOnBluetooth() {
        if (bluetoothAdapter == null) {
            throw new IllegalStateException("Bluetooth not supported on this device");
        }
        if (!bluetoothAdapter.isEnabled()) {
            Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnOn, REQUEST_ENABLE_BT);
        } else {
            tvOutput.setText("Bluetooth already ON");
        }
    }

    // Get Paired Bluetooth Devices
    private void showPairedDevices() {
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            throw new IllegalStateException("Bluetooth is OFF or not supported");
        }

        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
        if (pairedDevices.size() > 0) {
            StringBuilder devices = new StringBuilder("Paired Devices:\n");
            for (BluetoothDevice device : pairedDevices) {
                devices.append(device.getName()).append(" - ").append(device.getAddress()).append("\n");
            }
            tvOutput.setText(devices.toString());
        } else {
            tvOutput.setText("No paired devices found");
        }
    }

    // Enable or Disable Wi-Fi
    private void toggleWifi() {
        if (wifiManager == null) throw new IllegalStateException("Wi-Fi not supported on this device");

        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
            tvOutput.setText("Wi-Fi Enabled");
        } else {
            wifiManager.setWifiEnabled(false);
            tvOutput.setText("Wi-Fi Disabled");
        }
    }

    // Check Wi-Fi Connection Status
    private void checkWifiStatus() {
        if (wifiManager == null) throw new IllegalStateException("Wi-Fi not supported on this device");

        if (wifiManager.isWifiEnabled()) {
            tvOutput.setText("Wi-Fi is ON");
        } else {
            tvOutput.setText("Wi-Fi is OFF");
        }
    }

    private static final int REQUEST_ENABLE_BT = 1;
}
