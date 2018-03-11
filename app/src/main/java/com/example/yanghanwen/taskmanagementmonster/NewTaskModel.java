package com.example.yanghanwen.taskmanagementmonster;

import android.widget.EditText;

/**
 * Created by superfan1995 on 2018-03-09.
 */

public class NewTaskModel {

    private String username;

    public void NewTaskModel ( ) {

        // will change according to the main activity
        this.username = MainActivity.user.getUserName();
        // end

    }

    public void createNewTask (String taskname, String description) {

        Task task = new Task(username, taskname , description);

        // will change to the main activity / will upload the task into the database
        MainActivity.tasks.add(task);
        // end
    }


}
