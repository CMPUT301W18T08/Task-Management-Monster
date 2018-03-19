package com.example.yanghanwen.taskmanagementmonster;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.TextView;

/**
 * The activity control the view of detail information and operating on a user
 */
public class EditprofileActivity extends AppCompatActivity {

    //init the parameters
    private TextView nameView;          // TextView of username

    private EditText emailEdit;         // EditView of email
    private EditText phoneNumEdit;      // EditView of phone num

    private Button changeButton;        // the button to change information

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);

        nameView = (TextView) findViewById(R.id.profileName);

        emailEdit = (EditText) findViewById(R.id.profileEmail);
        phoneNumEdit = (EditText) findViewById(R.id.profilePhoneNum);

        changeButton = (Button) findViewById(R.id.profileEditButton);

        String username = MainActivity.mainModel.getUsername();
        String email = MainActivity.mainModel.getEmail();
        String phoneNum = MainActivity.mainModel.getPhoneNum();

        nameView.setText(username);
        emailEdit.setText(email);
        phoneNumEdit.setText(phoneNum);

        // if change button clicked, update the user information
        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String newEmail = emailEdit.getText().toString();
                String newPhoneNum = phoneNumEdit.getText().toString();

                MainActivity.mainModel.updateUser(newEmail, newPhoneNum);

                finish();
            }
        });

    }

}
