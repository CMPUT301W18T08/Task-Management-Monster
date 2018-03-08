package com.example.yanghanwen.taskmanagementmonster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public void onProviderClicked(View view){
        Intent intent = new Intent(this,MyTaskActivity.class);
        intent.putExtra("type","pro");
        startActivity(intent);
    }
    public void onRequesterClicked(View view){
        Intent intent = new Intent(this,MyTaskActivity.class);
        intent.putExtra("type","req");
        startActivity(intent);
    }
}

