package com.example.yangwenhan.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class TaskAdding extends AppCompatActivity {

    private EditText taskName;
    private EditText taskDescription;
    private String taskStatus;
    private ArrayList<Bid> bids;
    private String taskID;
    private String taskname;
    private String userName;
    private Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_adding);

        taskName = (EditText)findViewById(R.id.name) ;
        taskDescription = (EditText)findViewById(R.id.description);
        confirm = (Button)findViewById(R.id.save);


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = getIntent();

                userName = intent.getStringExtra("username");
                taskStatus = "requested";
                taskname = taskName.getText().toString();
                taskID = userName+taskname;
                bids = new ArrayList<Bid>();

                Task task = new Task();
                task.setUsername(userName);
                task.setTaskname(taskName.getText().toString());
                task.setDescription(taskDescription.getText().toString());
                task.setStatus(taskStatus);
                task.setBids(bids);

                if (existedTask(taskID)){
                    Toast.makeText(getApplicationContext(), "key conflict detected, unable to generate task", Toast.LENGTH_SHORT).show();
                }
                else {

                    ElasticSearch.AddTask addTask
                            = new ElasticSearch.AddTask();
                    addTask.execute(task);
                    Log.d("task added is", taskID);
                }
                finish();
            }
        });



    }
    public boolean existedTask(String taskID) {
        ElasticSearch.IsExistTask isExistTask = new ElasticSearch.IsExistTask();
        isExistTask.execute(taskID);
        try {
            if (isExistTask.get()){
                Log.d("it exist!!!",taskID);
                return true;
            }
            else{
                Log.d("unidentified user",taskID);
                return false;
            }}
        catch (Exception e){
            return false;
        }


    }


}
