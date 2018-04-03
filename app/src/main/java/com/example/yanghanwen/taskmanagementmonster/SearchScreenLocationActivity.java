package com.example.yanghanwen.taskmanagementmonster;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class SearchScreenLocationActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ArrayList<LatLng> getCoor = new ArrayList<>();
    private ArrayList<String> taskname = new ArrayList<>();
    private ArrayList<String> status = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_service);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        Intent intent = getIntent();

        getCoor = intent.getParcelableArrayListExtra("coordinates");
        Log.d("@@@@@@@@@@@@@@@@@@@@@@@@@@@@", getCoor.toString());
        taskname = intent.getStringArrayListExtra("taskname");
        Log.d("$$$$$$$$$$$$$$$$$$$$$$$$$$$$", taskname.toString());
        status = intent.getStringArrayListExtra("status");


        //TODO add tasks location here

        for(int i = 0; i < getCoor.size(); i++) {

            // Add a marker in current task location and move the camera
            LatLng TaskLocation = new LatLng(getCoor.get(i).latitude, getCoor.get(i).longitude);
            mMap.addMarker(new MarkerOptions().position(TaskLocation).title(taskname.get(i)).snippet(status.get(i)));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(TaskLocation));
        }


       mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                 marker.showInfoWindow();

//                    Map map = new HashMap();
//                    for(int i = 0; i < status.size(); i++) {
//                        map.put(status.get(i), marker.getId());
//                    }
//
//                    Log.d("test&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&", marker.getId());
//
//                    AlertDialog.Builder dialog = new AlertDialog.Builder(SearchScreenLocationActivity.this);
//
//                    dialog.setTitle("Reminder");
//                    if(marker.getId().equals("m1")) {
//                        dialog.setMessage("you clicked marker 1");
//                    } else if(marker.getId().equals("m0")) {
//                        dialog.setMessage("You clicked marker 0");
//                    }
//                    dialog.setCancelable(false);
//                    dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int which) {
//                        }
//                    });
//                    dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int which) {
//                        }
//                    });
//                    dialog.show();

                return false;
            }
        });

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        }

        //https://stackoverflow.com/questions/33666071/android-marshmallow-request-permission
        //2018-03-30
        else {
            ActivityCompat.requestPermissions(SearchScreenLocationActivity.this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        switch (requestCode) {

            case 1:
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ) {
                    mMap.setMyLocationEnabled(true);
                }
                else {
                    Toast.makeText(SearchScreenLocationActivity.this, "Permission denied to access your location", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
    }
}
