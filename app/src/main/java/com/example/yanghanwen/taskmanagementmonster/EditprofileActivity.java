package com.example.yanghanwen.taskmanagementmonster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;
import android.widget.TextView;

public class EditprofileActivity extends AppCompatActivity {
    private TextView name;
    private EditText email;
    private EditText phoneNum;
    private User user;
    private String userEmail;
    private String userPhoneNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);

        Intent intent = getIntent();
        //String name = intent.getStringExtra(MainActivity."name");
        //name = findViewById(R.id.name);

        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            name = findViewById(R.id.name);
            email = findViewById(R.id.email);
            phoneNum = findViewById(R.id.phoneNum);

            name.setText(bundle.getString("name"));
            email.setText(bundle.getString("email"));
            phoneNum.setText(bundle.getString("phone number"));

            userEmail = email.getText().toString();
            userPhoneNum = phoneNum.getText().toString();

            user = new User();
            user.setEmail(userEmail);
            user.setPhoneNum(userPhoneNum);
            user.setUserName(bundle.getString("name"));
        }
     //   ElasticSearch.UpdateUser updateUser = new ElasticSearch.UpdateUser();
       // updateUser.execute(user);
    }

    public void savefile(View view) {
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phoneNum = findViewById(R.id.phoneNum);
        String username = name.getText().toString();
        String useremail = email.getText().toString();
        String userphone = phoneNum.getText().toString();
        ElasticSearch.DeleteUser deleteUser = new ElasticSearch.DeleteUser();
        deleteUser.execute(user);
        user.setEmail(useremail);
        user.setPhoneNum(userphone);
        ElasticSearch.AddUser addUser = new ElasticSearch.AddUser();
        addUser.execute(user);

        Intent intent = new Intent(EditprofileActivity.this, MainActivity.class);
        intent.putExtra("name", username);
        startActivity(intent);
        finish();
    }

}