package com.example.yanghanwen.taskmanagementmonster;

import android.util.Log;

/**
 * Created by superfan1995 on 2018-03-09.
 */

public class NewTaskModel {

    private ElasticSearch.IsExistTask isExistTask;
    private ElasticSearch.AddTask addTask;

    private final String username;

    public NewTaskModel () {

        this.username = MainActivity.mainModel.getUsername();

        this.isExistTask = new ElasticSearch.IsExistTask();
        this.addTask = new ElasticSearch.AddTask();

    }

    public void createNewTask (String taskname, String description) {

        if (existTask(taskname)) {

            Log.d("message", "task already exist");
        }
        else {

            Task task = new Task(this.username, taskname, description);

            addTask.execute(task);

            Log.d("message", "task successfully created");
        }

    }

    public boolean existTask (String taskname) {

        isExistTask.execute(this.username + taskname);

        try {
            if (isExistTask.get()) {
                return Boolean.TRUE;
            }
            else {
                return Boolean.FALSE;
            }
        }
        catch (Exception e) {
            return Boolean.FALSE;
        }
    }

}
