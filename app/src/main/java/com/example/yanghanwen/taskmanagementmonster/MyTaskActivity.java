package com.example.yanghanwen.taskmanagementmonster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MyTaskActivity extends AppCompatActivity {
    private ListView requesterBiddingListView;
    private ListView requesterListView;
    private String OperationType;
    private TwoGridsAdapter adapter;
    private ThreeGridsAdapter threeGridsAdapter;
    private String currentUsername;
    private Button biddingBtn;
    private Button assignedBtn;


    /////////for test
    private int tid;
    private String taskusername;
    private String taskname;
    private String bidusername;
    private String description;
    private double amount;
    public ArrayList<Task>taskList = new ArrayList<>();
    public ArrayList<Task>newTaskList = new ArrayList<>();
    ////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_task);
        //////////////////////
        Task task0 = new Task (tid,taskusername,taskname,description);
        Task task1 = new Task (tid,taskusername,taskname,description);
        Task task2 = new Task (tid,taskusername,taskname,description);
        Task task3 = new Task (tid,taskusername,taskname,description);
        Task task4 = new Task (tid,taskusername,taskname,description);
        Task task5 = new Task (tid,taskusername,taskname,description);
        Task task6 = new Task (tid,taskusername,taskname,description);
        Task task7 = new Task (tid,taskusername,taskname,description);
        Task task8 = new Task (tid,taskusername,taskname,description);
        Task task9 = new Task (tid,taskusername,taskname,description);

        Bid bid0 = new Bid(bidusername,amount);
        Bid bid1 = new Bid(bidusername,amount);
        Bid bid2 = new Bid(bidusername,amount);
        Bid bid3 = new Bid(bidusername,amount);






        task0.setTaskname("task0");
        task0.setStatus("requesting");
        task0.setDescription("This task is requesting");

        task1.setTaskname("task 1");
        task1.setStatus("requesting");
        task1.setDescription("This task is requesting");

        task2.setTaskname("task 2");
        task2.setStatus("requesting");
        task2.setDescription("This task is requesting");

        task3.setTaskname("task 3");
        task3.setStatus("requesting");
        task3.setDescription("This task is requesting");

        task4.setTaskname("task 4");
        task4.setStatus("requesting");
        task4.setDescription("This task is requesting");

        task5.setTaskname("task 5");
        task5.setStatus("bidding");
        task5.setDescription("This task bidding");
        task5.createNewBid("bid0",1.0);
        task5.createNewBid("bid1",2.0);


        task6.setTaskname("task 6");
        task6.setStatus("bidding");
        task6.setDescription("This task bidding");
        task6.createNewBid("bid2",3.0);
        task6.createNewBid("bid3",4.0);

        task7.setTaskname("task 7");
        task7.setStatus("bidding");
        task7.setDescription("This task bidding");
        task7.createNewBid("bid0",1.0);
        task7.createNewBid("bid1",2.0);

        task8.setTaskname("task 8");
        task8.setStatus("bidding");
        task8.setDescription("This task bidding");
        task8.createNewBid("bid2",3.0);
        task8.createNewBid("bid3",4.0);

        task9.setTaskname("task 9");
        task9.setStatus("bidding");
        task9.setDescription("This task bidding");
        task9.createNewBid("bid0",1.0);
        task9.createNewBid("bid1",2.0);


        taskList.add(task0);
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        taskList.add(task4);
        taskList.add(task5);
        taskList.add(task6);
        taskList.add(task7);
        taskList.add(task8);
        taskList.add(task9);
        ////////////////////////////////////////////



        //currentUsername = MainActivity.user.getUserName();
        requesterBiddingListView = findViewById(R.id.requesterBiddingListView);
        requesterListView = findViewById(R.id.RequesterTask);

        OperationType = getIntent().getStringExtra("type");
        biddingBtn = findViewById(R.id.biddingBtn);
        assignedBtn = findViewById(R.id.assignedBtn);


        if(OperationType.equals("req")){
            /*
            providerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(MyTaskActivity.this,DetailTaskActivity.class);
                    intent.putExtra("index",i);
                    intent.putExtra("mode",3);
                    startActivity(intent);
                }
            });
            */
            //elastic search provider's task
            //get it later


            // adapter to adapt requester arrayList
            adapter = new TwoGridsAdapter(getApplicationContext(),taskList);

            requesterListView.setAdapter(adapter);








        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        biddingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requesterListView.setVisibility(View.GONE);
                for(Task task : taskList ){

                    if (task.getStatus().equals("bidding")){
                        Log.i("getStatus","got bidding");
                        newTaskList.add(task);

                    }
                }
                Log.d("tasklist",newTaskList.get(1).getTaskname());

                threeGridsAdapter = new ThreeGridsAdapter(getApplicationContext(),newTaskList);

                requesterBiddingListView.setAdapter(threeGridsAdapter);



            }
        });

        assignedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requesterListView.setVisibility(View.GONE);
                requesterListView.setVisibility(View.GONE);
                for(Task task : taskList){
                    if(task.getStatus().equals("assigned")){

                    }
                }
            }
        });
    }
}
