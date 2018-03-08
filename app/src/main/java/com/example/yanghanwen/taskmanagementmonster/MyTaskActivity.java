package com.example.yanghanwen.taskmanagementmonster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MyTaskActivity extends AppCompatActivity {
    private ListView providerList;
    private ListView requesterList;
    private String OperationType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_task);
        providerList = findViewById(R.id.ProviderTask);
        requesterList = findViewById(R.id.RequesterTask);
        OperationType = getIntent().getStringExtra("type");

        if (OperationType.equals("pro")){
            findViewById(R.id.RequesterTask).setVisibility(View.GONE);
        }else if (OperationType.equals("req")){
            findViewById(R.id.ProviderTask).setVisibility(View.GONE);
        }


//        providerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(Task.class);
//                intent.putExtra("index",position);
//                startActivity(intent);
//
//            }
//        });
//
//        requesterList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(Task.class);
//                intent.putExtra("index",position);
//                startActivity(intent);
//
//            }
//        });

    }
}
