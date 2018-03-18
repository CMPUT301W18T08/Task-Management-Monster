package com.example.yanghanwen.taskmanagementmonster;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewTaskActivity extends Activity {

    private NewTaskModel newTaskModel; // Model of this activity

    private EditText editTitle;         // EditText of new title
    private EditText editDescription;   // EditText of new description

    private Button createButton;        // create Task button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        // Here is the required value need to get from elastic search and other place

        newTaskModel = new NewTaskModel ();

        editTitle = (EditText) findViewById(R.id.editTextNewTitle);
        editDescription = (EditText) findViewById(R.id.editTextNewDescription);

        createButton = (Button) findViewById(R.id.buttonNewCreate);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String taskname = editTitle.getText().toString();
                String description = editDescription.getText().toString();

                newTaskModel.createNewTask(taskname, description);

                finish();
            }
        });

    }

}
