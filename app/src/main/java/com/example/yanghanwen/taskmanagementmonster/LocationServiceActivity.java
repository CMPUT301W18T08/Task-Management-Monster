package com.example.yanghanwen.taskmanagementmonster;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;


public class LocationServiceActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Intent intent;
    private FusedLocationProviderClient mFusedLocationClient;
    double latToSend, lngToSend = 0;
    private LocationManager locationManager;
//    private FusedLocationProviderClient mFusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_service);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        setMyLastKnownLocation();

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        intent = getIntent();

        double LatitudeGet = intent.getDoubleExtra("latitude", 0);
        double LongitudeGet = intent.getDoubleExtra("longitude", 0);
        String TitleToReceive = intent.getStringExtra("taskTitle");
        String StatusToReceive = intent.getStringExtra("status");

        mMap = googleMap;


        //TODO add tasks location here
        // Add a marker in current task location and move the camera
        LatLng TaskLocation = new LatLng(LatitudeGet, LongitudeGet);
        mMap.addMarker(new MarkerOptions().position(TaskLocation).title(TitleToReceive).snippet(StatusToReceive));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(TaskLocation));




       /*mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                AlertDialog.Builder dialog = new AlertDialog.Builder(LocationServiceActivity.this);
                dialog.setTitle("Reminder");
                dialog.setMessage("This task is at" + ExactPlaceToReceive);
                dialog.setCancelable(false);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        finish();
                    }
                });
                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                    }
                });
                dialog.show();

                return false;
            }
        });*/

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        }

        //https://stackoverflow.com/questions/33666071/android-marshmallow-request-permission
        //2018-03-30
        else {
            ActivityCompat.requestPermissions(LocationServiceActivity.this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        switch (requestCode) {

            case 1:
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    mMap.setMyLocationEnabled(true);
                } else {
                    Toast.makeText(LocationServiceActivity.this, "Permission denied to access your location", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
    }

    private void setMyLastKnownLocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            Log.d("TEST@KEVIN", "#####################");
                            if (location != null) {
                                // Logic to handle location object
                                Log.d("Latitude", Double.toString(location.getLatitude()));
                                Log.d("Lontitude", Double.toString(location.getLongitude()));
                                latToSend = location.getLatitude();
                                lngToSend = location.getLongitude();
                            }
                        }
                    });
        }
    }


    @Override
    public void onBackPressed() {
        Intent intent3 = new Intent();
        intent3.putExtra("latitudeSent", latToSend);
        intent3.putExtra("longitudeSent", lngToSend);
        setResult(Activity.RESULT_OK, intent3);
        super.onBackPressed();
    }
}

