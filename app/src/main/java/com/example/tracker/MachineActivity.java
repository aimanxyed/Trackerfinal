package com.example.tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.HashMap;
import java.util.Map;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MachineActivity extends AppCompatActivity {
    private FusedLocationProviderClient client;
    String url = "http://mmgps.000webhostapp.com/location_model.php";
    Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine);
        requestPermission();
        client = LocationServices.getFusedLocationProviderClient(this);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(MachineActivity.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                client.getLastLocation().addOnSuccessListener(MachineActivity.this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            final double users_latitude = location.getLatitude();
                            final double users_longitude = location.getLongitude();

                            final TextView textView = findViewById(R.id.textView);
                            textView.setText(String.valueOf(users_latitude));
                            final String latitude1 = textView.getText().toString();
                            // final String longitude1 =longitude.getText().toString();


                        }


                    }
                });
            }

        });
    }

    private void requestPermission()
    {
        ActivityCompat.requestPermissions(MachineActivity.this, new String[]{ACCESS_FINE_LOCATION},1);
    }
}
