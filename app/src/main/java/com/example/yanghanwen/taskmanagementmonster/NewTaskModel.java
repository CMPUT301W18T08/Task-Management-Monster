package com.example.yanghanwen.taskmanagementmonster;

import android.widget.EditText;

/**
 * Created by superfan1995 on 2018-03-09.
 */

public class NewTaskModel {

    private int tid;
    private String username;

    public void NewTaskModel ( ) {

        this.tid = newTid();

        // will change according to the main activity
        this.username = MainActivity.user.getUserName();

        //editTitle = (EditText) findViewById(R.id.editTextNewTitle);
        //editDescription = (EditText) findViewById(R.id.editTextNewDescription);
    }

    public void createNewTask (String taskname, String description) {

        Task task = new Task(tid, username, taskname , description);

        // will change to the main activity / will upload the task into the database
        MainActivity.tasks.add(task);
    }

    // not yet finished
    private int newTid () {

        return 1;
    }

}
