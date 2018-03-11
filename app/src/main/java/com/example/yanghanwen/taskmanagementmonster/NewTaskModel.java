package com.example.yanghanwen.taskmanagementmonster;

/**
 * Created by Terrence on 2018/3/10.
 */

public class NewTaskModel {

    private int tid;
    private String username;

    public void NewTaskModel(){

        this.tid = newTid();

        this.username = MainActivity.user.getUserName();
    }

    public void createNewTask(String taskname, String description){

        Task task = new Task(tid,username,taskname,description);

        MainActivity.tasks.add(task);
    }

    private int newTid(){
        return 1;
    }
}
