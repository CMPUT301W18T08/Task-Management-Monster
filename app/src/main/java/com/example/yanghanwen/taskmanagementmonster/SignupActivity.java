package com.example.yanghanwen.taskmanagementmonster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SignupActivity extends AppCompatActivity {



    EditText name;
    EditText email;
    EditText phoneNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }






    public void createfile(View view) {
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phoneNum = findViewById(R.id.phoneNum);
        Intent intent = new Intent();
        String newName = name.getText().toString();
        String newEmail = email.getText().toString();
        String newPhoneNum = phoneNum.getText().toString();

    }


    public void SignMeUp(View view) {
        Intent intent = new Intent(SignupActivity.this, SigninActivity.class);
        startActivity(intent);
    }
}
