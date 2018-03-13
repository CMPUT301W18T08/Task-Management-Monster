package com.example.yanghanwen.taskmanagementmonster;

import android.widget.EditText;

/**
 * Created by superfan1995 on 2018-03-09.
 */

public class NewTaskModel {

    private String username;

    public NewTaskModel () {

        // will change according to the main activity
        this.username = MainActivity.user.getUserName();
        // end

    }

    public void createNewTask (String taskname, String description) {

        Task task = new Task(this.username, taskname, description);

        // test
        // here we add the new task, current for mock only
        MainActivity.mockTest2 = task;
        // end of test
    }

}
