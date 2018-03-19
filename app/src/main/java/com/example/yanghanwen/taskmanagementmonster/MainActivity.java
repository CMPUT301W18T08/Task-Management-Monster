package com.example.yanghanwen.taskmanagementmonster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static MainModel mainModel;

    private Button newTaskButton;
    private Button searchButton;
    private Button providerButton;
    private Button requesterButton;

    //private EditText searchKeyWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Intent intent = getIntent();

        String username = intent.getStringExtra("username");

        mainModel = new MainModel(username);

        newTaskButton = (Button) findViewById(R.id.newTaskButton);
        searchButton = (Button) findViewById(R.id.mainSearch);
        providerButton = (Button) findViewById(R.id.providerButton);
        requesterButton = (Button) findViewById(R.id.requesterButton);

        newTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,
                        NewTaskActivity.class);

                startActivity(intent);
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, SearchActivity.class);

                startActivity(intent);
            }
        });

        providerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, MyTaskActivity.class);

                intent.putExtra("type", "pro");

                startActivity(intent);
            }
        });

        requesterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, MyTaskActivity.class);

                intent.putExtra("type", "req");

                startActivity(intent);
            }
        });

    }
}