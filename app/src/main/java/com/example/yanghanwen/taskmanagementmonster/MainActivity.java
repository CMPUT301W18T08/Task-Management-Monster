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

        //searchKeyWord = (EditText) findViewById(R.id.typeInSearch);

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

                //String keyWord = searchKeyWord.getText().toString();

                Intent intent = new Intent(MainActivity.this, SearchActivity.class);

                startActivity(intent);
            }
        });

    }
}
