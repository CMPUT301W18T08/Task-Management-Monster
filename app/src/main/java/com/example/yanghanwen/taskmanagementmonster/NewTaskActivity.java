package com.example.yanghanwen.taskmanagementmonster;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * activity of creating new task
 *
 * @version 1.0
 */
public class NewTaskActivity extends Activity {

    // new change -----------------------------------------

    public static NewTaskModel newTaskModel; // Model of this activity

    // new change -----------------------------------------

    private EditText editTitle;         // EditText of new title
    private EditText editDescription;   // EditText of new description

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        // create model for this activity
        newTaskModel = new NewTaskModel ();

        editTitle = (EditText) findViewById(R.id.editTextNewTitle);
        editDescription = (EditText) findViewById(R.id.editTextNewDescription);

        Button createButton = (Button) findViewById(R.id.buttonNewCreate);
        Button imageButton = (Button) findViewById(R.id.buttonNewImage);

        // get value and create new task when button clicked
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String taskname = editTitle.getText().toString();
                String description = editDescription.getText().toString();

                newTaskModel.createNewTask(taskname, description);

                finish();
            }
        });

        // this part is new  -----------------------------------------------------------

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(NewTaskActivity.this,
                        ImageListActivity.class);

                intent.putExtra("mode", "new");

                startActivity(intent);
            }
        });

        // this part is new (1) -----------------------------------------------------------

    }

}
