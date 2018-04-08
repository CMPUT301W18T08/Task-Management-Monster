package com.example.yanghanwen.taskmanagementmonster;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * MyTaskActivity is used for showing the ListView when user want to view his Tasks
 * the listview has to kinds:
 * for requester listview, there will be a filter to show bidding task or assigned task
 * if choose bidding task, list view shows status as "bidding",title,lowest bid
 * if choose assigned task,list view shows status as "assigned",its provider username,title,accepted bid
 *
 * for provider listview, there will be a filter to show bidding task or assigned task
 * if choose bidding task, list view shows status as "bidding",title, task requester username,lowest bid so far,my bid
 * if choose assigned task, list view shows status as "assigned",title,its task requester username,my accepted bid
 *
 * layout : activity_my_task.xml
 *
 * @author Tianyi Liang
 * @version 1.0
 */
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

    /**
     *
     * Executed when the activity starts
     * It creates three listview to store whole Task List, bidding task list, and assigned task list
     * It creates two button as filter
     *
     * @param savedInstanceState
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_task);

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
        currentUsername = MainActivity.mainModel.getUsername();


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

            // for clicking each items in listview
            requesterListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    Task task = taskList.get(i);
                    String title = task.getTaskname();
                    String requester = task.getUsername();

                    Intent intent = new Intent(MyTaskActivity.this,DetailTaskActivity.class);
                    intent.putExtra("mode",3);
                    intent.putExtra("title", title);
                    intent.putExtra("requester", requester);
                    startActivity(intent);
                }
            });

            // when clicking the bidding button
            biddingBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    requesterListView.setVisibility(View.GONE);
                    requesterAssignedListView.setVisibility(View.GONE);
                    requesterBiddingListView.setVisibility(View.GONE);
                    requesterBiddingListView.setVisibility(View.VISIBLE);

                    newTaskList.clear();
                    for(Task task : taskList ){

                        if (task.getStatus().equals("bidded")){
                            Log.i("getStatus","got bidded");
                            newTaskList.add(task);

                        }
                    }
                    Log.d("tasklist",newTaskList.get(1).getTaskname());

                    threeGridsAdapter = new ThreeGridsAdapter(getApplicationContext(),newTaskList);

                    requesterBiddingListView.setAdapter(threeGridsAdapter);


                    // for clicking each items in listview
                    requesterBiddingListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            Task task = newTaskList.get(i);
                            String title = task.getTaskname();
                            String requester = task.getUsername();

                            Intent intent = new Intent(MyTaskActivity.this, DetailTaskActivity.class);
                            intent.putExtra("mode",3);
                            intent.putExtra("title", title);
                            intent.putExtra("requester", requester);
                            startActivity(intent);
                        }
                    });

                }
            });

            // when clicking the assigned button
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

                    // for clicking each items in listview
                    requesterAssignedListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            Task task = newTaskList.get(i);
                            String title = task.getTaskname();
                            String requester = task.getUsername();

                            Intent intent = new Intent(MyTaskActivity.this, DetailTaskActivity.class);
                            intent.putExtra("mode",3);
                            intent.putExtra("title", title);
                            intent.putExtra("requester", requester);
                            startActivity(intent);
                        }
                    });

                }
            });

        }


        else if (OperationType.equals("pro")){

            // ElasticSearch to get whole tasksList in db
            ElasticSearch.GetTasks getTasks = new ElasticSearch.GetTasks();
            String query = "{\"query\":{\"match\":{\"bids.userName\":{\"query\":\""+currentUsername+"\"}}}}";
            getTasks.execute(query);

            try{
                wholeTaskList = getTasks.get();

            }catch (Exception e){
                Log.i("Error", "Failed to get the tasks from the async object");
            }


            // get the TaskList I bidded,i.e. my provider list
            for(Task task : wholeTaskList){

                providerTaskList.add(task);

            }

            adapter = new TwoGridsAdapter(getApplicationContext(),providerTaskList);
            //although it says it is requestorListView, but it actually is providedListView
            requesterListView.setAdapter(adapter);


            // for clicking each item in listview
            requesterListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    Task task = providerTaskList.get(i);
                    String title = task.getTaskname();
                    String requester = task.getUsername();

                    Intent intent = new Intent(MyTaskActivity.this,DetailTaskActivity.class);
                    intent.putExtra("mode",2);
                    intent.putExtra("title", title);
                    intent.putExtra("requester", requester);
                    startActivity(intent);
                }
            });

            // when click bidding button
            biddingBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    requesterListView.setVisibility(View.GONE);
                    requesterAssignedListView.setVisibility(View.GONE);
                    requesterBiddingListView.setVisibility(View.GONE);
                    requesterBiddingListView.setVisibility(View.VISIBLE);

                    newTaskList.clear();
                    for(Task task:providerTaskList){
                        if(task.getStatus().equals("bidded")){
                            newTaskList.add(task);
                        }
                    }
                    fiveGridsAdapter = new FiveGridsAdapter(getApplicationContext(),newTaskList);
                    requesterBiddingListView.setAdapter(fiveGridsAdapter);

                    // for clicking each item in listview
                    requesterBiddingListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            Task task = newTaskList.get(i);
                            String title = task.getTaskname();
                            String requester = task.getUsername();

                            Intent intent = new Intent(MyTaskActivity.this,DetailTaskActivity.class);
                            intent.putExtra("mode",2);
                            intent.putExtra("title", title);
                            intent.putExtra("requester", requester);
                            startActivity(intent);
                        }
                    });

                }
            });

            // when click assigned button
            assignedBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    requesterListView.setVisibility(View.GONE);
                    requesterAssignedListView.setVisibility(View.GONE);
                    requesterBiddingListView.setVisibility(View.GONE);
                    requesterAssignedListView.setVisibility(View.VISIBLE);

                    newTaskList.clear();
                    for(Task task:providerTaskList){
                        if(task.getStatus().equals("assigned")){
                            newTaskList.add(task);
                        }
                    }
                    fourGridsProviderAdapter = new FourGridsProviderAdapter(getApplicationContext(),newTaskList);
                    requesterAssignedListView.setAdapter(fourGridsProviderAdapter);

                    // for clicking each item in listview
                    requesterAssignedListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            Task task = newTaskList.get(i);
                            String title = task.getTaskname();
                            String requester = task.getUsername();

                            Intent intent = new Intent(MyTaskActivity.this,DetailTaskActivity.class);
                            intent.putExtra("mode",2);
                            intent.putExtra("title", title);
                            intent.putExtra("requester", requester);
                            startActivity(intent);
                        }
                    });


                }
            });

        }
    }


}
