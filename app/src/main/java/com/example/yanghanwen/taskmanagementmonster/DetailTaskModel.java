package com.example.yanghanwen.taskmanagementmonster;

/**
 * Created by superfan1995 on 2018-03-09.
 */

public abstract class DetailTaskModel {

    protected String username;

    //private String title;
    //private String requestor;
    //private String status;
    //private String description;

    protected Task task;

    public DetailTaskModel (String title, String requestor) {

        // get the username from user (will change)
        this.username = MainActivity.user.getUserName();
        // end

        // get the task attribute
        int tasksLength = 1;

        for (int i = 0; i < tasksLength; i = i + 1) {

            this.task = MainActivity.tasks.get(i);

            if (task.getTaskname() == title && task.getUsername() == requestor) {

                break;
            }
        }
        // end of will be change

    }

    public abstract String getBidInfo();
    public abstract String getBidLowest();
    public abstract String getMyBidInfo();
    public abstract String getMyBid();

    public abstract String getButtonText1();
    public abstract String getButtonText2();

    public abstract int visibilityBidInfo();
    public abstract int visibilityBidLowest();
    public abstract int visibilityMyBidInfo();
    public abstract int visibilityMyBid();
    public abstract int visibilityEdit();
    public abstract int visibilityButton1();
    public abstract int visibilityButton2();

    public String getTitle () {

        return task.getTaskname();
    }

    public String getRequestor () {

        return task.getUsername();
    }

    public String getStatus ()  {

        return task.getStatus();
    }

    public String getDescrption () {

        return task.getDescription();
    }

}
