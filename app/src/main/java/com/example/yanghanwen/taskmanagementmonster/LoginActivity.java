package com.example.yanghanwen.taskmanagementmonster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Activity for login, the user can use an exist username to enter the main activity
 * or can enter another activity to create a new username
 */
public class LoginActivity extends AppCompatActivity {

    private EditText editID;
    private Button loginButton;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editID = (EditText)findViewById(R.id.id);

        loginButton = (Button)findViewById(R.id.login);
        registerButton = (Button)findViewById(R.id.register);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userId = editID.getText().toString();

                if (existedUser(userId)){

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    Log.d("username", userId);
                    intent.putExtra("username",userId);
                    startActivity(intent);

                    Toast.makeText(getApplicationContext(), "Online Login Success",
                            Toast.LENGTH_SHORT).show();
                }

                else {

                    Toast.makeText(getApplicationContext(), "Username Not Exist",
                            Toast.LENGTH_SHORT).show();
                }
            }

        });


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    // ? what is the purpose if this?
    private Boolean isUserIdLegal(String text){
        if (text.equals("")) {
            return Boolean.FALSE;
        }
        else{
            return Boolean.TRUE;
        }
    }

    /**
     * Check is the input username already exist
     *
     * @param name the username for check
     * @return weather the username exist
     */
    public boolean existedUser(String name) {
        ElasticSearch.IsExist isExist = new ElasticSearch.IsExist();
        isExist.execute(name);
        try {
            if (isExist.get()){
                Log.d("it exist!!!",name);
                return true;
            }
            else{
                Log.d("unidentified user",name);
                return false;
            }}
        catch (Exception e){
            return false;
        }

    }

}
