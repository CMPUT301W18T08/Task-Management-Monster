package com.example.yanghanwen.taskmanagementmonster;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NewTaskActivity extends Activity {

    private int tid;
    private String username;
    private String taskname;
    private String description;

    private EditText editTitle;
    private EditText editDescription;

    private Button createButton;
    private Button quitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        // Here is the required value need to get from elastic search and other place
        tid = 1;
        username = "Tom";
        // end of value

        editTitle = (EditText) findViewById(R.id.editTextNewTitle);
        editDescription = (EditText) findViewById(R.id.editTextNewDescription);

        createButton = (Button) findViewById(R.id.buttonNewCreate);
        quitButton = (Button) findViewById(R.id.buttonNewQuit);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                taskname = editTitle.getText().toString();
                description = editDescription.getText().toString();

                Task task = new Task(tid, username, taskname, description);

                // save the task in the main activity
                // MainActivity.tasks.add(task);

                finish();
            }
        });

        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

    }

}
