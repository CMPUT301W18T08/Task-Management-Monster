package com.example.yanghanwen.taskmanagementmonster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * The activity control the main view of the app
 */
public class MainActivity extends AppCompatActivity {

    public static MainModel mainModel;  // model of this activity

    private Button newTaskButton;       // button to create new task
    private Button searchButton;        // button to search a task
    private Button providerButton;      // button to see my task as provider
    private Button requesterButton;     // button to see my task as requester
    private Button profileButton;       // button to see my profile

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Intent intent = getIntent();

        String username = intent.getStringExtra("username");

        // create the model by given the username
        mainModel = new MainModel(username);

        newTaskButton = (Button) findViewById(R.id.newTaskButton);
        searchButton = (Button) findViewById(R.id.mainSearch);
        providerButton = (Button) findViewById(R.id.providerButton);
        requesterButton = (Button) findViewById(R.id.requesterButton);
        profileButton = (Button) findViewById(R.id.profileButton);

        // button to create new task
        newTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,
                        NewTaskActivity.class);

                startActivity(intent);
            }
        });

        // button to search a task
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, SearchActivity.class);

                startActivity(intent);
            }
        });

        // button to see my task as provider
        providerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, MyTaskActivity.class);

                // set the type as provider
                intent.putExtra("type", "pro");

                startActivity(intent);
            }
        });

        // button to see my task as requester
        requesterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, MyTaskActivity.class);

                // set the type as requester
                intent.putExtra("type", "req");

                startActivity(intent);
            }
        });

        // button to see my profile
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,
                        EditprofileActivity.class);

                startActivity(intent);
            }
        });

    }
}