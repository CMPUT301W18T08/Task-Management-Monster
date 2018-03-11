package com.example.yanghanwen.taskmanagementmonster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewTaskActivity extends AppCompatActivity {

    private NewTaskModel newTaskModel;

    private EditText editTitle;
    private EditText editDescription;

    private Button createButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        newTaskModel = new NewTaskModel();

        editTitle = (EditText) findViewById(R.id.editTextNewTitle);
        editDescription = (EditText) findViewById(R.id.editTextNewDescription);

        createButton = (Button) findViewById(R.id.buttonNewCreate);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taskname = editTitle.getText().toString();
                String description = editDescription.getText().toString();

                newTaskModel.createNewTask(taskname,description);

                finish();
            }
        });

    }
}
