package com.example.yangwenhan.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class Menu extends AppCompatActivity {

    private TextView uName;
    private TextView uEmail;
    private TextView uPhone;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();

        uName = (TextView)findViewById(R.id.name);
        uEmail = (TextView)findViewById(R.id.email);
        uPhone = (TextView)findViewById(R.id.phone);

        ElasticSearch.GetUser getUser = new ElasticSearch.GetUser();
        getUser.execute(intent.getStringExtra("username"));
        try{
            user = getUser.get();
        }
        catch (Exception e) {
            Log.i("Error", "Fail to connect to server");
        }

        uName.setText(user.getUserName());
        uEmail.setText(user.getEmail());
        uPhone.setText(user.getPhoneNum());
    }

}
