package com.example.yanghanwen.taskmanagementmonster;

/**
 * Created by superfan1995 on 2018-03-09.
 */

public class DetailTaskModel {

    private int mode;
    private int tid;
    private String username;

    private String title;
    private String requestor;
    private String status;
    private String description;

    private Task task;

    public DetailTaskModel(int mode, int tid) {

        this.mode = mode;
        this.tid = tid;

        // will be change
        // will change according to the main activity
        this.username = MainActivity.user.getUserName();

        // Here we get the task attribute

        // Here get tasks array length
        // tasksLength = MainActivity.tasks.size()
        int tasksLength = 1;

        for (int i = 0; i < tasksLength; i = i + 1) {

            this.task = MainActivity.tasks.get(i);

            if (task.getTid() == tid) {

                break;
            }
        }
        // end of will be change

    }

    public String getTitle() {

        return this.task.getTaskname();
    }

    public String getRequestor() {

        return this.task.getUsername();
    }

    public String getStatus() {

        return this.task.getStatus();
    }

    public String getDescription () {

        return this.task.getDescription();
    }

    public void addNewBid() {


    }

}
