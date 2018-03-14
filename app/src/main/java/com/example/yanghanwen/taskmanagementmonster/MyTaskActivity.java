package com.example.yanghanwen.taskmanagementmonster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MyTaskActivity extends AppCompatActivity {
    private ListView providerList;
    private ListView requesterList;
    private String OperationType;
    private ArrayAdapter<Task>myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_task);
        providerList = findViewById(R.id.ProviderTask);
        requesterList = findViewById(R.id.RequesterTask);
        OperationType = getIntent().getStringExtra("type");

        if(OperationType.equals("pro")){
            //elastic search provider's task

            // adapter to adapt requester arrayList
            myAdapter = new ArrayAdapter<Task>(this,android.R.layout.simple_list_item_2,
                    TaskSingleton.getInstance().getTaskArrayList());



        }else if(OperationType.equals("req")){

        }
    }
}
