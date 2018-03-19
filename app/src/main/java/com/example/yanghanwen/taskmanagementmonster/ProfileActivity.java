package com.example.yanghanwen.taskmanagementmonster;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by yuhangxiong on 2018-03-04.
 */

public class ProfileActivity extends AppCompatActivity {
    TextView name;
    TextView email;
    TextView phoneNum;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

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
        }
    }

    public void edit(View view) {
        Intent intent = new Intent(ProfileActivity.this, EditprofileActivity.class);
        intent.putExtra("name", name.getText().toString());
        intent.putExtra("email", email.getText().toString());
        intent.putExtra("phone number", phoneNum.getText().toString());
        startActivityForResult(intent, 1);
       // finish();
    }

    @Override
    //get the result from activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //if it is from add new subscription function
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                name = findViewById(R.id.name);
                email = findViewById(R.id.email);
                phoneNum = findViewById(R.id.phoneNum);

                name.setText(bundle.getString("name"));
                email.setText(bundle.getString("email"));
                phoneNum.setText(bundle.getString("phone number"));
            }

        }
    }


}