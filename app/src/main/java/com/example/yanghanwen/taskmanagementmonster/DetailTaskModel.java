package com.example.yanghanwen.taskmanagementmonster;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by superfan1995 on 2018-03-09.
 */

public abstract class DetailTaskModel {

    protected ElasticSearch.GetTask getTask;
    protected ElasticSearch.UpdateTask updateTask;

    protected String username;
    protected Task task;

    public DetailTaskModel (String title, String requestor) {

        this.getTask = new ElasticSearch.GetTask();

        this.username = MainActivity.mainModel.getUsername();

        this.getTask.execute(requestor + title);

        try {
            this.task = getTask.get();
        }
        catch (Exception e) {

            Log.d("error", "fail to get the task");
        }

    }

    public abstract String getBidInfo();
    public abstract String getBidLowest();
    public abstract String getMyBidInfo();
    public abstract String getMyBid();
    public abstract ArrayList<Bid> getBidsList();

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
    public abstract int visibilityListView();

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

    public String getProvider(int position) {

        Bid bid = task.getBid(position);
        return bid.getUserName();
    }

    public Double getAmount(int position) {

        Bid bid = task.getBid(position);
        return bid.getAmount();
    }

    public void assignBid(int position) {

        Bid bid = task.getBid(position);

        task.setAssigned(bid.getUserName());

        // update task by elastic search
        taskUpdate();
    }

    public void declineBid(int position) {

        Bid bid = task.getBid(position);

        task.declineBid(bid.getUserName());

        // update task by elastic search
        taskUpdate();
    }

    public void taskUpdate() {

        this.updateTask.execute(this.task);
    }

}
