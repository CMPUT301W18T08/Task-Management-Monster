package com.example.yanghanwen.taskmanagementmonster;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

    private NewTaskModel newTaskModel; // Model of this activity

    private EditText editTitle;         // EditText of new title
    private EditText editDescription;   // EditText of new description

    private Button createButton;        // create Task button
    private ConnectionCheck connectionCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);



        // create model for this activity
        newTaskModel = new NewTaskModel ();

        editTitle = (EditText) findViewById(R.id.editTextNewTitle);
        editDescription = (EditText) findViewById(R.id.editTextNewDescription);

        createButton = (Button) findViewById(R.id.buttonNewCreate);

        // get value and create new task when button clicked
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(connectionCheck.isNetWorkAvailable(getApplicationContext()))){
                    for(int i = 0; i < 3; i ++){
                        Toast.makeText(getApplicationContext(),"Oops,data will upload once connected",
                                Toast.LENGTH_LONG).show();
                    }
                }
                String taskname = editTitle.getText().toString();
                String description = editDescription.getText().toString();

                newTaskModel.createNewTask(taskname, description);
                Intent intentService = new Intent(getApplicationContext(),TaskIntentService.class);
                startService(intentService);



;

                finish();
            }
        });

    }

}
