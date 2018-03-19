package com.example.yanghanwen.taskmanagementmonster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayAdapter adapter;
    public static ArrayList<User> userList = new ArrayList<>();
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
        String username = bundle.getString("name");
        user = new User();

        ElasticSearch.GetUser getUser = new ElasticSearch.GetUser();
        getUser.execute(username);
        try {
            user = getUser.get();
        }catch (Exception e){
            Log.i("fjaif","jfiaf");
        }

    }

    public void viewProfile(View view) {
        String name = user.getUserName();
        String email = user.getEmail();
        String phoneNum = user.getPhoneNum();

        Log.d("print name", name);
        Log.d("print email", email);
        Log.d("print phone", phoneNum);

        Intent viewMyProfile = new Intent(MainActivity.this, ProfileActivity.class);
        viewMyProfile.putExtra("name", name);
        viewMyProfile.putExtra("email", email);
        viewMyProfile.putExtra("phone number", phoneNum);
        startActivity(viewMyProfile);
        finish();
    }

}
