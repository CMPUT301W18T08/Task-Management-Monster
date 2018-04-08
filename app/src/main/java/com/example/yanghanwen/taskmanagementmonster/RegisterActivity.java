package com.example.yanghanwen.taskmanagementmonster;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Activity of register a new user to use the app
 *
 * This activity will get name, email, and phone number of a user.
 */
public class RegisterActivity extends AppCompatActivity {

    private EditText nameView;
    private EditText emailView;
    private EditText phoneView;

    private ImageButton saveButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameView = (EditText) findViewById(R.id.profileName);
        emailView = (EditText) findViewById(R.id.profileEmail);
        phoneView = (EditText) findViewById(R.id.profilePhoneNum);
        saveButton = (ImageButton) findViewById(R.id.save);

        ActionBar bar = getSupportActionBar();

        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));

            // use the input data to create a new user
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String name = nameView.getText().toString();
                    if (name.length() < 8) {
                        Toast.makeText(RegisterActivity.this, "Your username must be at least 8 characters",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        String userName = nameView.getText().toString();
                        String email = emailView.getText().toString();
                        String phone = phoneView.getText().toString();

                        User newUser = new User(userName, email, phone);

                        ElasticSearch.AddUser addUser = new ElasticSearch.AddUser();
                        addUser.execute(newUser);
                        Log.i("test", "hahhahahahahahhahahahahah");
                        Log.d("user is", userName);

                        finish();
                    }
                }

            });
        }
    }

