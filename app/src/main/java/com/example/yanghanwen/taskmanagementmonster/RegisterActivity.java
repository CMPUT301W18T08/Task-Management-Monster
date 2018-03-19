package com.example.yanghanwen.taskmanagementmonster;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    private EditText nameView;
    private EditText emailView;
    private EditText phoneView;

    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameView = (EditText)findViewById(R.id.name);
        emailView = (EditText)findViewById(R.id.email);
        phoneView = (EditText)findViewById(R.id.phoneNum) ;
        saveButton = (Button)findViewById(R.id.save);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userName = nameView.getText().toString();
                String email = emailView.getText().toString();
                String phone = phoneView.getText().toString();

                User newUser = new User(userName, email, phone);

                ElasticSearch.AddUser addUser = new ElasticSearch.AddUser();
                addUser.execute(newUser);
                Log.i("test","hahhahahahahahhahahahahah");
                Log.d("user is",userName);

                finish();

            }

        });
    }
}