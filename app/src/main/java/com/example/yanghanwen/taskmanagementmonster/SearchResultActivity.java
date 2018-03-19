package com.example.yanghanwen.taskmanagementmonster;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import com.example.yanghanwen.taskmanagementmonster.Task;


/*
 *
 *  * Copyright © 2018 Hanwen Yang, CMPUT301, University of Alberta - All Rights Reserved.
 *  * You may use, distribute or modify this code under terms and conditions of Code of Student Behavior at
 *  *  University of Alberta.
 *  * You can find a copy of the license in this project, otherwise please contact at
 *  *   hyang4@ualberta.ca
 *
 *
 */


/**
 * SearchResultActivity is used for searching a
 * task (or tasks) as a role of provider, it will provide
 * all the tasks and provider is able to either filter tasks to
 * whatever he want through four different status, and can also
 * search a task(s) by entering keywords of username.
 *
 * layout: activity_search_result.xml
 *
 * @author Hanwen Yang
 * @version 2.0.0
 */
public class SearchResultActivity extends AppCompatActivity {

    private ListView listView;
    private EditText editText;
    private Button button;
    private int tid;
    private String username;
    private String taskname;
    private String description;
    private long firstPressed;
    public ArrayList<Task> taskList = new ArrayList<>();
    public ArrayList<Task> allTaskList = new ArrayList<>();
    public ArrayAdapter<Task> adapter;
    ArrayList<Task> tasks = new ArrayList<>();


    /**
     * Firstly executed when code starts going
     * mainly used for getting all tasks that are
     * available in elasticsearch server
     *
     *
     * @param savedInstanceState
     * @throws ArrayIndexOutOfBoundsException
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) throws ArrayIndexOutOfBoundsException {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        listView = (ListView) findViewById(R.id.Search_result);
        editText = (EditText) findViewById(R.id.discover_search);
        button = (Button) findViewById(R.id.recover_button);
        String keyWord = editText.getText().toString();

        Task task_test = new Task();
        ElasticSearch.GetTask getTask = new ElasticSearch.GetTask();
        ElasticSearch.GetTasks getTasks = new ElasticSearch.GetTasks();
        ElasticSearch.IsExistTask isExistTask = new ElasticSearch.IsExistTask();



        //getting tasks from elasticsearch server
        String qUsername = "";

        getTasks.execute(qUsername);


        try {
            tasks = getTasks.get();
            Log.i("getting something new", tasks.toString());
        } catch(Exception e) {
            Log.d("test!!!!!!!!!!", "something wrong");
        }

        initList();



        //hiding keyboard from popping up
        //which could be annoying
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);


        editText.setOnClickListener(new View.OnClickListener() {

            /**
             * show keyboard once edittext
             * has been clicked
             *
             *
             * @param view
             */
            @Override
            public void onClick(View view) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
            }
        });




        editText.addTextChangedListener(new TextWatcher() {

            /**
             * Constructing a edittext listener
             * to track changes of text, when user
             * starts to enter keyword, the program
             * starts to search
             *
             *
             * @param charSequence
             * @param i
             * @param i1
             * @param i2
             */
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    TaskSearch();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    TaskSearch();
            }

            /**
             * after text changes do searching again
             * to make it faster
             *
             *
             * @param editable
             */

            @Override
            public void afterTextChanged(Editable editable) {
                    TaskSearch();

            }
        });



        button.setOnClickListener(new View.OnClickListener() {

            /**
             * setting recover all button action
             * when button is clicked, recover all elements
             * that had been removed to let user do multiple
             * times of search
             *
             *
             * @param view
             */
            @Override
            public void onClick(View view) {

                adapter.clear();
                for(int j = 0; j < allTaskList.size(); j++) {
                    taskList.add(allTaskList.get(j));
                }
                adapter.notifyDataSetChanged();
            }
        });
    }



    /**
     * setting backbutton action:
     * press once to recover all elements that had been removed(same functionality as recover button)
     * do a double press within 3000 milliseconds to return to the last activity
     */
    @Override
    public void onBackPressed() {

        adapter.clear();
        for(int j = 0; j < allTaskList.size(); j++) {
            taskList.add(allTaskList.get(j));
        }
        adapter.notifyDataSetChanged();

        if(System.currentTimeMillis() - firstPressed < 3000) {
            super.onBackPressed();//if do a double click within 3000 milliseconds, back to previous activity
        } else {
            Toast.makeText(SearchResultActivity.this, "Press again to quit", Toast.LENGTH_SHORT).show();
            firstPressed = System.currentTimeMillis();
        }
    }


    /**
     * searching task action: remove all
     * tasks that are unsatisfied
     */
    public void TaskSearch() {

        String keyWord = editText.getText().toString();
        for(int i = 0; i < taskList.size(); i++) {
            if(!(taskList.get(i).getUsername().toLowerCase().contains(keyWord.toLowerCase()))) {
                taskList.remove(taskList.get(i));
            }
        }
        adapter.notifyDataSetChanged();
    }


    /**
     * initialize taskList, setting adapter, adding all tasks to listview
     * meanwhile add all same tasks to allTaskList for
     * recovering purpose
     */
    public void initList() {

        for(int k = 0; k < tasks.size(); k++) {
            taskList.add(tasks.get(k));
        }

        for(int i = 0; i < taskList.size(); i++) {
            allTaskList.add(taskList.get(i));
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, taskList);
        listView.setAdapter(adapter);
    }


    /**
     * Initializing filter menu
     *
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        return true;
    }


    /**
     * Filter action, is able to filter in four different ways:
     * bidded, assigned, requested or done
     *
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) { // filter by bidded task
            case R.id.item1:
                int k = 0;
                while(k < taskList.size()) {
                    if(!taskList.get(k).getStatus().equals("bidded")) {
                        taskList.remove(taskList.get(k));
                        k = -1;
                    }
                    adapter.notifyDataSetChanged();
                    k++;
                }
                item.setVisible(false);
                break;

            case R.id.item2:
                int a = 0;
                while(a < taskList.size()) { // filter by requested task
                    if(!taskList.get(a).getStatus().equals("requested")) {
                        taskList.remove(taskList.get(a));
                        a = -1;
                    }
                    a++;
                }
                adapter.notifyDataSetChanged();
                break;

            case R.id.item3:
                int b = 0;
                while(b < taskList.size()) { // filter by assigned task
                    if(!taskList.get(b).getStatus().equals("assigned")) {
                        taskList.remove(taskList.get(b));
                        b = -1;
                    }
                    b++;
                }
                adapter.notifyDataSetChanged();
                break;

            case R.id.item4:
                int c = 0;
                while(c < taskList.size()) { // filter by done task
                    if(!taskList.get(c).getStatus().equals("done")) {
                        taskList.remove(taskList.get(c));
                        c = -1;
                    }
                    c++;
                }
                adapter.notifyDataSetChanged();
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
