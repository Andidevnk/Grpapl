package com.example.nk.grpapl;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nk.grpapl.SharedPreferences.SP;
import com.example.nk.grpapl.Utils.ConnectionDetector;
import com.example.nk.grpapl.Utils.GPSTracking;
import com.example.nk.grpapl.dialogs.CommonDialog;

public class splash_screen extends AppCompatActivity {

    private final int SPLASH = 2000;

    ConnectionDetector connectionDetector;
    CommonDialog cd;
    SP sp;

    GPSTracking gpsTracking;

    private int REQUEST_FINE_LOCATION = 2000; /* 2 sec */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        sp =SP.getInstance();

        connectionDetector  = new ConnectionDetector(splash_screen.this);
        cd = new CommonDialog(splash_screen.this);

        gpsTracking = new GPSTracking(this, splash_screen.this);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(!connectionDetector.isConnectingToInternet()){
                    cd.showNetworkDialog();
                }else {
                    if (sp.getUsername(splash_screen.this) == "") {
                        checkPermissions();
                        Intent mainIntent = new Intent(splash_screen.this, Log_in.class);
                        startActivity(mainIntent);
                        finish();
                    }
                    else{
                        Intent intent = new Intent(splash_screen.this,Dashboard_app.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
            private void finish() {}
            {}

        } , SPLASH);

  }


    @Override
    protected void onRestart() {
        super.onRestart();

        if(!connectionDetector.isConnectingToInternet()){
            cd.showNetworkDialog();
        }else{

            Intent mainIntent = new Intent(splash_screen.this,Log_in.class);
            startActivity(mainIntent);
            finish();
        }
    }

    private boolean checkPermissions() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            gpsTracking.getLastLocation();

        } else {
            requestPermissions();

            return false;
        }
        return false;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                REQUEST_FINE_LOCATION);
    }

}
