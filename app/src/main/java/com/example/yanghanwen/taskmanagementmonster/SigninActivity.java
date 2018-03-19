package com.example.yanghanwen.taskmanagementmonster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SigninActivity extends AppCompatActivity {

    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
    }




    public void SignMeUp(View view) {
        Intent intent = new Intent(SigninActivity.this, SignupActivity.class);
        startActivity(intent);
    }



    public void LogIn(View view) {
        name = findViewById(R.id.userName);
        String username = name.getText().toString();
        ElasticSearch.IsExist isExist = new ElasticSearch.IsExist();
        isExist.execute(username);
        try{
            if(isExist.get()){
                Intent intent = new Intent(SigninActivity.this, MainActivity.class);
                intent.putExtra("name", username);
                startActivity(intent);
            }else{
                Toast.makeText(getApplicationContext(),"Unidentified user account",Toast.LENGTH_SHORT);
            }
        }catch (Exception e){
            Log.i("Error", "WRONG");
        }

    }


}
