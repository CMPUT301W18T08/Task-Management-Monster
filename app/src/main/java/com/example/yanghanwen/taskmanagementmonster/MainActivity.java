package com.example.yanghanwen.taskmanagementmonster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void myTaskBtn(View view) {
        Intent intent = new Intent(this,MyTaskActivity.class);
        intent.putExtra("type","req");
        startActivity(intent);

    }

    public void myProvideTaskBtn(View view) {

        Intent intent = new Intent (this, MyTaskActivity.class);
        intent.putExtra("type","pro");
        startActivity(intent);
    }
}
