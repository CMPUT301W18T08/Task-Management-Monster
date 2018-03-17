package com.example.yangwenhan.project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class DisplayTasks extends AppCompatActivity {


    private ListView listView;
    public ArrayList<Task> taskList = new ArrayList<>();
    public ArrayAdapter<Task> adapter;
    private String userID;
    private Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_tasks);

        listView = (ListView) findViewById(R.id.display_tasks);

        userID = "yxiong3";

        ElasticSearch.GetTasks getTasks = new ElasticSearch.GetTasks();
        String q = "{\"query\" : {\"term\" : { \"username\" : \""+userID+"\" }}}";
        getTasks.execute(q);

        try {
            taskList = getTasks.get();
            Log.i("inside try","getTasks.get() executes");
        } catch (Exception e) {
            Log.i("Error", "Failed to get the tweets from the async object");
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, taskList);
        listView.setAdapter(adapter);

        Log.d("print task f", taskList.get(0).getTaskname());
    }
}
