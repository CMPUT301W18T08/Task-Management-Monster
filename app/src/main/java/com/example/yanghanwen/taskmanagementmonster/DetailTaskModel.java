package com.example.yanghanwen.taskmanagementmonster;

/**
 * Created by superfan1995 on 2018-03-09.
 */

public abstract class DetailTaskModel {

    protected String username;

    protected Task task;

    public DetailTaskModel (String title, String requestor) {

        // get the username from user (will change)
        this.username = MainActivity.user.getUserName();
        // end

        // get the task attribute, current for test only

        task = MainActivity.mockTest;

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
    public abstract int visibilityEditTitle();
    public abstract int visibilityEditDescription();
    public abstract int visibilityChangeButton();
    public abstract int visibilityDeclineButton();

    public abstract void changeButtonAction(String newValue);
    public abstract void declineButtonAction(String newValue);

    public String getTitle () {

        return task.getTaskname();
    }

    public String getRequester () {

        return task.getUsername();
    }

    public String getStatus ()  {

        return task.getStatus();
    }

    public String getDescrption () {

        return task.getDescription();
    }

}
