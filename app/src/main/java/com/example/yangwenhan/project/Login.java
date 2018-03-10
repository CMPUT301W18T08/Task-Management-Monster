package com.example.yangwenhan.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    private EditText id;
    public static UserList userList;
    private Button login;
    private Button register;
    //private final Boolean existedUser = Boolean.TRUE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        id = (EditText)findViewById(R.id.id);

        login = (Button)findViewById(R.id.login);
        register = (Button)findViewById(R.id.register);
        userList = new UserList();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userId = id.getText().toString();
                //String query = "{\"query\":{\"term\":{\"userid\":"+userId+"}}}";
                if (existedUser(userId)){
                    Intent intent = new Intent(Login.this, Menu.class);
                    Log.d("username", userId);
                    intent.putExtra("username",userId);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "online login.", Toast.LENGTH_SHORT).show();
                }

            }


        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,register.class);
                startActivity(intent);
            }
        });
    }

    private Boolean isUserIdLegal(String text){
        if (text.equals("")) {
            return Boolean.FALSE;
        }
        else{
            return Boolean.TRUE;
        }
    }

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

    /*
        private boolean existedUser (String name) {
        ElasticSearchUserController.IsExist isExist = new ElasticSearchUserController.IsExist();
        isExist.execute(name);

        try {
            if (isExist.get()) {
                return true;
            } else {
                Toast.makeText(getApplicationContext(), name + " does not exist.", Toast.LENGTH_SHORT).show();
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
     */

}
