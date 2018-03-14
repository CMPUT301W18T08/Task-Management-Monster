package com.example.yanghanwen.taskmanagementmonster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MyTaskActivity extends AppCompatActivity {
    private ListView providerList;
    private ListView requesterList;
    private String OperationType;
    private ArrayAdapter<Task>myAdapter;
    private String username;
    private ArrayList<Task>tasks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_task);
        username = MainActivity.user.getUserName();
        providerList = findViewById(R.id.ProviderTask);
        requesterList = findViewById(R.id.RequesterTask);
        OperationType = getIntent().getStringExtra("type");

        if(OperationType.equals("pro")){
            providerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(MyTaskActivity.this,DetailTaskActivity.class);
                    intent.putExtra("index",i);
                    intent.putExtra("mode",2);
                    startActivity(intent);
                }
            });
            //elastic search provider's task
            ElasticSearch.getTaskList getTaskList = new ElasticSearch.getTaskList();
            getTaskList.execute(username);
            try {
                tasks = getTaskList.get();
            }catch (Exception e){
                Log.i("Error","Failed to get the tweets from the async object");
            }

            // adapter to adapt requester arrayList
            myAdapter = new ArrayAdapter<Task>(this,android.R.layout.simple_list_item_2,tasks);



        }else if(OperationType.equals("req")){
            requesterList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(MyTaskActivity.this,DetailTaskActivity.class);
                    intent.putExtra("index",i);
                    intent.putExtra("mode",3);
                    startActivity(intent);
                }
            });
            ElasticSearch.getTaskList getTaskList = new ElasticSearch.getTaskList();
            getTaskList.execute(username);
            try {
                tasks = getTaskList.get();
            }catch (Exception e){
                Log.i("Error","Failed to get the tweets from the async object");
            }
            myAdapter = new ArrayAdapter<Task>(MyTaskActivity.this,android.R.layout.simple_list_item_2,tasks);

        }
    }
}
