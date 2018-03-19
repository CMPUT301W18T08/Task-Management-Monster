package com.example.yanghanwen.taskmanagementmonster;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by superfan1995 on 2018-03-09.
 */

/**
 * MVC model for the Detail task activity, compute and return required data.
 *
 * @version 1.0
 */

public abstract class DetailTaskModel {

    //private ElasticSearch.GetTask getTask;          // elastic search object used to get task
    //private ElasticSearch.AddTask addTask;          // elastic search object used to add task
    //private ElasticSearch.DeleteTask deleteTask;    // elastic search object used to delete task

    protected String username;      // username of user who is viewing the activity
    protected Task task;            // task need to be operated on

    /**
     * Construct a model instance for the DetailTaskActivity
     *
     * The task will get by elastic search by using the input parameter title and requester
     * to search for the corresponding task.
     *
     * The username will be get by using the MainActivity's method.
     *
     * @param title the title / taskname of task that need show detail information
     * @param requester the username of user who is the requester of the task
     */
    public DetailTaskModel (String title, String requester) {

        this.username = MainActivity.mainModel.getUsername();

        ElasticSearch.GetTask getTask = new ElasticSearch.GetTask();

        getTask.execute(requester + title);

        try {
            this.task = getTask.get();                              /* Task found */
        }
        catch (Exception e) {

            Log.d("error", "fail to get the task");     /* Error as task not found */
        }

    }

    /**
     * Get the string showed on detailBidInformation TextView of activity_detail_task.
     *
     * Depend on the subclass to provided the required output string.
     *
     * This string will be used to describe the lowest bid of the current bid or the detail
     * information about the current bid, such as this task has no bid.
     *
     * @return String showed on detailBidInformation TextView
     */
    public abstract String getBidInfo();

    /**
     * Get the string showed on detailBidLowest TextView of activity_detail_task
     *
     * Depend on the subclass to provided the required output string.
     *
     * This string generally provide the lowest amount of the current bid.
     *
     * @return String showed on detailBidLowest TextView
     */
    public abstract String getBidLowest();

    /**
     * Get the string showed on detailMyBid TextView of activity_detail_task
     *
     * Depend on the subclass to provided the required output string.
     *
     * This string generally describe the information about the current user's bid on the
     * task that are currently viewed.
     *
     * @return String showed on detailMyBid TextView
     */
    public abstract String getMyBidInfo();

    /**
     * Get the string showed on viewDetailMyBid TextView of activity_detail_task
     *
     * Depend on the subclass to provided the required output string.
     *
     * This string generally describe the information about the current user's bid of this task.
     *
     * @return String showed on viewDetailMyBid TextView
     */
    public abstract String getMyBid();

    /**
     * Return the Arraylist contain all the bid Object of the bid on this task.
     *
     * This method is proved to the requester when the task is in the bidded status.
     *
     * This method is used to print all the bids of this task on a ListView for the requester to
     * further operated on.
     *
     * @return ArrayList contain all Bid object related to this task
     */
    public abstract ArrayList<Bid> getBidsList();

    /**
     *  Return the text showed on the buttonDetail
     *
     *  Depend on the subclass to provided the required output text.
     *
     *  This button is generally used to modify / assigned / bidded or other 'positive' action
     *  on the current task.
     *
     * @return Text showed on the buttonDetail
     */
    public abstract String getButtonText1();

    /**
     *  Return the text showed on the buttonDetail2
     *
     *  Depend on the subclass to provided the required output text.
     *
     *  This button is generally used to decline or delete other 'negative' action
     *  on the current task.
     *
     * @return Text showed on the buttonDetail2
     */
    public abstract String getButtonText2();

    /**
     * Return the visibility of the detailBidInformation
     *
     * Depend on the subclass to provided the required output.
     *
     * @return an int represent the visibility of the detailBidInformation
     */
    public abstract int visibilityBidInfo();

    /**
     * Return the visibility of the detailBidLowest
     *
     * Depend on the subclass to provided the required output.
     *
     * @return an int represent the visibility of the detailBidLowest
     */
    public abstract int visibilityBidLowest();

    /**
     * Return the visibility of the detailMyBid
     *
     * Depend on the subclass to provided the required output.
     *
     * @return an int represent the visibility of the detailMyBid
     */
    public abstract int visibilityMyBidInfo();

    /**
     * Return the visibility of the viewDetailMyBid
     *
     * Depend on the subclass to provided the required output.
     *
     * @return an int represent the visibility of the viewDetailMyBid
     */
    public abstract int visibilityMyBid();

    /**
     * Return the visibility of the editTextDetail
     *
     * Depend on the subclass to provided the required output.
     *
     * @return an int represent the visibility of the editTextDetail
     */
    public abstract int visibilityEdit();

    /**
     * Return the visibility of the editTextDetailTitle
     *
     * This EditText is used to get the new title of this task when the requester want to
     * modify his own task in requested status.
     *
     * Depend on the subclass to provided the required output.
     *
     * @return an int represent the visibility of the editTextDetailTitle
     */
    public abstract int visibilityEditTitle();

    /**
     * Return the visibility of the editTextDetailDescription
     *
     * This EditText is used to get the new Description of this task when the requester want to
     * modify his own task in requested status.
     *
     * Depend on the subclass to provided the required output.
     *
     * @return an int represent the visibility of the editTextDetailDescription
     */
    public abstract int visibilityEditDescription();

    /**
     * Return the visibility of the buttonDetail
     *
     * Depend on the subclass to provided the required output.
     *
     * @return an int represent the visibility of the buttonDetail
     */
    public abstract int visibilityChangeButton();

    /**
     * Return the visibility of the buttonDetail2
     *
     * Depend on the subclass to provided the required output.
     *
     * @return an int represent the visibility of the buttonDetail2
     */
    public abstract int visibilityDeclineButton();

    /**
     * Return the visibility of the detailListView
     *
     * This ListView is used to list the bids on this task when requester operate on his own
     * task in bidded status.
     *
     * Depend on the subclass to provided the required output.
     *
     * @return an int represent the visibility of the detailListView
     */
    public abstract int visibilityListView();

    /**
     * Act a specified action defined by individual subclass when the first button are being used
     *
     * @param newValue The new value of the change
     */
    public abstract void changeButtonAction(String newValue);

    /**
     * Act a specified action defined by individual subclass when the second button are being used
     *
     * @param newValue the new value of the change
     */
    public abstract void declineButtonAction(String newValue);

    /**
     * Return the title of the current task
     *
     * @return title of current task
     */
    public String getTitle () {

        return task.getTaskname();
    }

    /**
     * Return the username of user who create the current task
     *
     * @return username of current task
     */
    public String getRequester () {

        return task.getUsername();
    }

    /**
     * Return the status of the current task
     *
     * @return status of current task
     */
    public String getStatus ()  {

        return task.getStatus();
    }

    /**
     * Return the description of current task
     *
     * @return description of current task
     */
    public String getDescription () {

        return task.getDescription();
    }

    /**
     * Return the username of bidder of bid at the certain position of the Arraylist contain
     * all bid of this task
     *
     * @param position the position of the bid in Arraylist bids
     * @return the username of the bid at that position
     */
    public String getProvider(int position) {

        Bid bid = task.getBid(position);
        return bid.getUserName();
    }

    /**
     * Return the double amount of bid at the certain position of the Arraylist contain
     * all bid of this task
     *
     * @param position the position of the bid in Arraylist bids
     * @return the bid amount of the bid at that position
     */
    public Double getAmount(int position) {

        Bid bid = task.getBid(position);
        return bid.getAmount();
    }

    /**
     * Assigned the task to the bid at the certain position of the Arraylist contain
     * all bid of this task
     *
     * @param position the position of the bid in Arraylist bids
     */
    public void assignBid(int position) {

        Bid bid = task.getBid(position);

        task.setAssigned(bid.getUserName());

        // update task by elastic search
        taskUpdate();
    }

    /**
     * Decline the bid at the certain position of the Arraylist contain all bid of this task
     *
     * @param position the position of the bid in Arraylist bids
     */
    public void declineBid(int position) {

        Bid bid = task.getBid(position);

        task.declineBid(bid.getUserName());

        // update task by elastic search
        taskUpdate();
    }

    /**
     * Updata the task to the database used the elastic search
     */
    public void taskUpdate() {

        ElasticSearch.AddTask addTask = new ElasticSearch.AddTask();
        addTask.execute(this.task);
    }

}