package com.example.yanghanwen.taskmanagementmonster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //init the user object
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //get the name of the user pass by log in activity
        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
        String username = bundle.getString("name");
        user = new User();


        // get the user object using the user name
        ElasticSearch.GetUser getUser = new ElasticSearch.GetUser();
        getUser.execute(username);
        try {
            user = getUser.get();
        }catch (Exception e){
            Log.i("fjaif","jfiaf");
        }

    }
    // view profile function
    public void viewProfile(View view) {

        // get the profile from user object
        String name = user.getUserName();
        String email = user.getEmail();
        String phoneNum = user.getPhoneNum();


        // intit the new intent and put the user info to the bundle and pass to the profile activity intent
        Intent viewMyProfile = new Intent(MainActivity.this, ProfileActivity.class);
        viewMyProfile.putExtra("name", name);
        viewMyProfile.putExtra("email", email);
        viewMyProfile.putExtra("phone number", phoneNum);
        startActivity(viewMyProfile);
        finish();
    }

}
