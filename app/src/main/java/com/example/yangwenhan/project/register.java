package com.example.yangwenhan.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//import static com.example.yangwenhan.project.User.Users;

public class register extends AppCompatActivity {

    private EditText name;
    private EditText email;
    private EditText phone;
    private int uid;

    private Button quit;
    private Button save;
    private String userName;
    private String init_email = "";
    private String init_phoneNum = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        uid = Login.userList.countUser();
        Log.d("uid is", Integer.toString(uid));
        name = (EditText)findViewById(R.id.name);
        email = (EditText)findViewById(R.id.email);
        phone = (EditText)findViewById(R.id.phoneNum) ;
        quit = (Button)findViewById(R.id.quit);
        save = (Button)findViewById(R.id.save);


//        if (userName.equals("")){
//            Toast.makeText(getApplicationContext(),"invalid userName",Toast.LENGTH_LONG).show();
//        }else{
//            User newUser = new User(uid,userName);
//            Users.add(newUser);
//            ElasticSearch.AddUser addUser = new ElasticSearch.AddUser();
//            addUser.execute(newUser);
//            Log.i("test","hahhahahahahahhahahahahah");
//            Intent intent = new Intent(register.this,Login.class);
//            startActivity(intent);
//        }
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(register.this,Login.class);
                startActivity(intent);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (userName.equals("")){
//                    Log.d("UserName",userName);
//                    Toast.makeText(getApplicationContext(),"invalid userName",Toast.LENGTH_LONG).show();
//                }else{
                    userName = name.getText().toString();
                    User newUser = new User(userName,email.getText().toString(),phone.getText().toString());
                    //Users.add(newUser);
                    ElasticSearch.AddUser addUser = new ElasticSearch.AddUser();
                    addUser.execute(newUser);
                    Log.i("test","hahhahahahahahhahahahahah");
                    Log.d("user is",userName);
                    Intent intent = new Intent(register.this,Login.class);
                    startActivity(intent);

            //}
        }

    });
}}
