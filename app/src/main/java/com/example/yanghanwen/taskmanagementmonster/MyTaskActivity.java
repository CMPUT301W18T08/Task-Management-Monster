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
    // these ListView is named with "requester", But it used for requester & provider case
    private ListView requesterBiddingListView;
    private ListView requesterListView;
    private ListView requesterAssignedListView;

    private String OperationType;

    // different adapter for adapter different kind of ListView
    private TwoGridsAdapter adapter;
    private ThreeGridsAdapter threeGridsAdapter;
    private FourGridsAdapter fourGridsAdapter;
    private FiveGridsAdapter fiveGridsAdapter;
    private FourGridsProviderAdapter fourGridsProviderAdapter;


    public static String currentUsername;
    private Button biddingBtn;
    private Button assignedBtn;

    //get whole task list from ES
    private ArrayList<Task>wholeTaskList = new ArrayList<>();

    //the arraylist for the task the currentUser has bidded
    private ArrayList<Task>providerTaskList = new ArrayList<>();

    // the arraylist for currentUser's requested task
    public ArrayList<Task>taskList = new ArrayList<>();

    // a temp arraylist to store data and adapt to ListView
    public ArrayList<Task>newTaskList = new ArrayList<>();


    // used for ElasticSearch
    private String userID;




    /////////for test////////////////////////
    private int tid;
    private String taskusername;
    private String taskname;
    private String bidusername;
    private String description;
    private double amount;

    ////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_task);
        /////////////////////////////////////////////////
        // for testing
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
        Task task10 = new Task(tid,taskusername,taskname,description);
        Task task11 = new Task(tid,taskusername,taskname,description);


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

        task10.setTaskname("task10");
        task10.setDescription("This task assigned");
        task10.setStatus("assigned");
        task10.createNewBid("bid10",66.66);

        task11.setTaskname("task11");
        task11.setDescription("This task assigned");
        task11.setStatus("assigned");
        task11.createNewBid("bid11",77.77);


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
        taskList.add(task10);
        taskList.add(task11);
        ////////////////////////////////////////////




        // initial all listview
        requesterBiddingListView = findViewById(R.id.requesterBiddingListView);
        requesterAssignedListView = findViewById(R.id.requesterAssignedListView);
        requesterListView = findViewById(R.id.RequesterTask);

        // get incoming type (requester/provider)
        OperationType = getIntent().getStringExtra("type");

        // initial button for filter
        biddingBtn = findViewById(R.id.biddingBtn);
        assignedBtn = findViewById(R.id.assignedBtn);

        // get current username
        currentUsername = MainActivity.MainModel.getUsername();


        if(OperationType.equals("req")){


            // Elastic Search for current user to get his posted tasksList
            userID = currentUsername;

            ElasticSearch.GetTasks getTasks = new ElasticSearch.GetTasks();
            String query = "{\"query\" : {\"term\" : { \"username\" : \""+userID+"\" }}}";
            getTasks.execute(query);

            try{
                taskList = getTasks.get();
                Log.i("inside try","getTasks.get() executes");
            }catch (Exception e){
                Log.i("Error", "Failed to get the tasks from the async object");
            }
            // adapter to adapt requester arrayList
            adapter = new TwoGridsAdapter(getApplicationContext(),taskList);
            requesterListView.setAdapter(adapter);

            /*

            // for clicking each items in listview

            requesterListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(MyTaskActivity.this,DetailTaskActivity.class);
                    intent.putExtra("index",i);
                    intent.putExtra("mode",3);
                    startActivity(intent);
                }
            });
            */

            biddingBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    requesterListView.setVisibility(View.GONE);
                    requesterAssignedListView.setVisibility(View.GONE);
                    requesterBiddingListView.setVisibility(View.GONE);
                    requesterBiddingListView.setVisibility(View.VISIBLE);

                    newTaskList.clear();
                    for(Task task : taskList ){

                        if (task.getStatus().equals("bidding")){
                            Log.i("getStatus","got bidding");
                            newTaskList.add(task);

                        }
                    }
                    Log.d("tasklist",newTaskList.get(1).getTaskname());

                    threeGridsAdapter = new ThreeGridsAdapter(getApplicationContext(),newTaskList);

                    requesterBiddingListView.setAdapter(threeGridsAdapter);

                    /*
                    // for clicking each items in listview

                    requesterBiddingListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent intent = new Intent(MyTaskActivity.this, DetailTaskActivity.class);
                            intent.putExtra("index",i);
                            intent.putExtra("mode",3);
                            startActivity(intent);
                        }
                    });
                    */




                }
            });
            assignedBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    requesterListView.setVisibility(View.GONE);
                    requesterAssignedListView.setVisibility(View.GONE);
                    requesterBiddingListView.setVisibility(View.GONE);
                    requesterAssignedListView.setVisibility(View.VISIBLE);

                    newTaskList.clear();

                    for(Task task : taskList){
                        if(task.getStatus().equals("assigned")){
                            newTaskList.add(task);

                        }
                    }
                    fourGridsAdapter = new FourGridsAdapter(getApplicationContext(),newTaskList);
                    requesterAssignedListView.setAdapter(fourGridsAdapter);


                    /*
                    // for clicking each items in listview

                    requesterAssignedListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent intent = new Intent(MyTaskActivity.this, DetailTaskActivity.class);
                            intent.putExtra("index",i);
                            intent.putExtra("mode",3);
                            startActivity(intent);
                        }
                    });
                    */


                }
            });

        }


        else if (OperationType.equals("pro")){

            // ElasticSearch to get whole tasksList in db
            ElasticSearch.GetTasks getTasks = new ElasticSearch.GetTasks();
            String query = "";
            getTasks.execute(query);

            try{
                wholeTaskList = getTasks.get();
                Log.i("inside try","getTasks.get() executes");
            }catch (Exception e){
                Log.i("Error", "Failed to get the tasks from the async object");
            }





            // get the TaskList I bidded,i.e. my provider list
            for(Task task : wholeTaskList){
                if(!task.getUserAmount(currentUsername).equals(null)){
                    providerTaskList.add(task);
                }
            }

            adapter = new TwoGridsAdapter(getApplicationContext(),providerTaskList);
            //although it says it is requestorListView, but it actually is providedListView
            requesterListView.setAdapter(adapter);

            /*
            // for clicking each item in listview

            requesterListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(MyTaskActivity.this,DetailTaskActivity.class);
                    intent.putExtra("index",i);
                    intent.putExtra("mode",2);
                    startActivity(intent);
                }
            });
            */








            biddingBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    requesterListView.setVisibility(View.GONE);
                    requesterAssignedListView.setVisibility(View.GONE);
                    requesterBiddingListView.setVisibility(View.GONE);
                    requesterBiddingListView.setVisibility(View.VISIBLE);

                    newTaskList.clear();
                    for(Task task:providerTaskList){
                        if(task.getStatus().equals("bidding")){
                            newTaskList.add(task);
                        }
                    }
                    fiveGridsAdapter = new FiveGridsAdapter(getApplicationContext(),newTaskList);
                    requesterBiddingListView.setAdapter(fiveGridsAdapter);


                    /*
                    // for clicking each item in listview
                    requesterBiddingListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent intent = new Intent(MyTaskActivity.this,DetailTaskActivity.class);
                            intent.putExtra("index",i);
                            intent.putExtra("mode",2);
                            startActivity(intent);
                        }
                    });
                    */

                }
            });

            assignedBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    requesterListView.setVisibility(View.GONE);
                    requesterAssignedListView.setVisibility(View.GONE);
                    requesterBiddingListView.setVisibility(View.GONE);
                    requesterBiddingListView.setVisibility(View.VISIBLE);

                    newTaskList.clear();
                    for(Task task:providerTaskList){
                        if(task.getStatus().equals("assigned")){
                            newTaskList.add(task);
                        }
                    }
                    fourGridsProviderAdapter = new FourGridsProviderAdapter(getApplicationContext(),newTaskList);
                    requesterAssignedListView.setAdapter(fourGridsProviderAdapter);

                    /*
                    // for clicking each item in listview
                    requesterAssignedListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent intent = new Intent(MyTaskActivity.this,DetailTaskActivity.class);
                            intent.putExtra("index",i);
                            intent.putExtra("mode",2);
                            startActivity(intent);
                        }
                    });
                    */


                }
            });




        }
    }


}
