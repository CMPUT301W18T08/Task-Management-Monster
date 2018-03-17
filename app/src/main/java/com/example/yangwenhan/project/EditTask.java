package com.example.yangwenhan.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditTask extends AppCompatActivity {

    private EditText editTitle;
    private TextView showNewTitle;
    private Button save;
    private String search_key;
    private Task task;
    private String newTitle;
    private Task task2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        editTitle = (EditText)findViewById(R.id.editTitle);
        showNewTitle = (TextView)findViewById(R.id.view_change);
        save = (Button)findViewById(R.id.confirm);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                search_key = intent.getStringExtra("taskID");
                ElasticSearch.GetTask getTask = new ElasticSearch.GetTask();
                getTask.execute(search_key);
                try{
                    task = getTask.get();
                    Log.i("print something","should print information");
                }
                catch (Exception e) {
                    Log.i("Error", "Fail to connect to server");
                }

                newTitle = editTitle.getText().toString();
                task.setTaskname(newTitle);
                ElasticSearch.UpdateTask updateTask = new ElasticSearch.UpdateTask();
                updateTask.execute(task);

                ElasticSearch.GetTask getTask2 = new ElasticSearch.GetTask();
                getTask2.execute(task.getUsername()+task.getTaskname());
                try{
                    task = getTask2.get();
                    Log.i("print something","should print information");
                }
                catch (Exception e) {
                    Log.i("Error", "Fail to connect to server");
                }

                showNewTitle.setText(task.getTaskname());

            }
        });


    }
}
