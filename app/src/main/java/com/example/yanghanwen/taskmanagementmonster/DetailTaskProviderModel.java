package com.example.yanghanwen.taskmanagementmonster;

import android.view.View;

import java.util.ArrayList;

/**
 * Created by superfan1995 on 2018-03-10.
 */


/**
 *
 *
 * @version 1.0
 */
public class DetailTaskProviderModel extends DetailTaskModel {

    Boolean assigned;   // where this task has assigned to the user

    /**
     * Construct a model instance for the DetailTaskActivity that are used to view the detail
     * of task that we bid as provider.
     *
     * if the task's status is assigned, then this task is assigned to the user.
     *
     * @param title the title / taskname of task that need show detail information
     * @param requestor the username of user who is the requester of the task
     */
    public DetailTaskProviderModel (String title, String requestor) {

        super(title, requestor);

        if (super.task.getStatus().equals("assigned")) {

            assigned = Boolean.TRUE;
        }

        else {

            assigned = Boolean.FALSE;
        }

    }

    /**
     * Get the string showed on detailBidInformation TextView of activity_detail_task.
     *
     * If the task already assigned, then print accepted bid, else the user's lowest bid.
     *
     * @return String showed on detailBidInformation TextView
     */
    public String getBidInfo() {

        if (assigned) {

            return "Accepted Bid:";
        }

        else {

            return "Current Lowest Bid:";
        }
    }

    /**
     * Return user's lowest bid, if the task is assigned it will also the user's
     * accpeted bid
     *
     * @return String showed on detailBidLowest TextView
     */
    public String getBidLowest() {

        if (assigned) {

            Double myBid = super.task.getUserAmount(super.username);
            return "$ " + myBid.toString();
        }

        else {

            Double lowestBid = super.task.getLowestBid();
            return "$ " + lowestBid.toString();
        }

    }

    /**
     * Get the string showed on detailMyBid TextView of activity_detail_task, will only show
     * if the task is assigned
     *
     * @return String showed on detailMyBid TextView
     */
    public String getMyBidInfo() {

        if (assigned) {

            return "";
        }

        else{

            return "Current My Bid:";
        }
    }

    /**
     * Return the user's bid on this task, will only show when the task is assigned
     *
     * @return the user's bid in string
     */
    public String getMyBid() {

        if (assigned) {

            return "";
        }

        else {

            Double myBid = super.task.getUserAmount(super.username);
            return "$ " + myBid.toString();
        }
    }

    /**
     *  Return the text showed on the buttonDetail, will only show the option of modify
     *  the bid if task not yet assigned.
     *
     * @return modify the task string on button
     */
    public String getButtonText1() {

        if (assigned) {

            return "";
        }

        else {

            return "Modify My Bid";
        }
    }

    /**
     * Return the text showed on the buttonDetail2, will only show the option of decline
     * the bid if task not yet assigned.
     *
     * @return decline the task string on button
     */
    public String getButtonText2() {

        if (assigned) {

            return "";
        }

        else {

            return "Decline My Bid";
        }
    }

    /**
     * This function is not work for this model
     *
     * @return null
     */
    public ArrayList<Bid> getBidsList() {

        return null;
    }

    /**
     *
     * @return
     */
    public int visibilityBidInfo() {

        return View.VISIBLE;
    }

    /**
     *
     * @return
     */
    public int visibilityBidLowest() {

        return View.VISIBLE;
    }

    /**
     *
     * @return
     */
    public int visibilityMyBidInfo() {

        if (assigned) {

            return View.GONE;
        }

        else {
            return View.VISIBLE;
        }
    }

    /**
     *
     * @return
     */
    public int visibilityMyBid() {

        if (assigned) {

            return View.GONE;
        }

        else {
            return View.VISIBLE;
        }
    }

    /**
     *
     * @return
     */
    public int visibilityEdit() {

        if (assigned) {

            return View.GONE;
        }

        else {
            return View.VISIBLE;
        }
    }

    /**
     *
     * @return
     */
    public int visibilityEditTitle() {

        return View.GONE;
    }

    /**
     *
     * @return
     */
    public int visibilityEditDescription() {

        return View.GONE;
    }

    /**
     *
     * @return
     */
    public int visibilityListView() {

        return View.GONE;
    }

    /**
     *
     * @return
     */
    public int visibilityChangeButton() {

        if (assigned) {

            return View.GONE;
        }

        else {

            return View.VISIBLE;
        }
    }

    /**
     *
     * @return
     */
    public int visibilityDeclineButton() {

        if (assigned) {

            return View.GONE;
        }

        else {

            return View.VISIBLE;
        }
    }

    /**
     *
     * @param newValue The new value of the change
     */
    public void changeButtonAction (String newValue) {

        Double userbid = Double.parseDouble(newValue);

        super.task.modifyBid(super.username, userbid);

        // update task by elastic search
        super.taskUpdate();
    }

    /**
     *
     * @param newValue the new value of the change
     */
    public void declineButtonAction (String newValue) {

        super.task.declineBid(super.username);

        // update task by elastic search
        super.taskUpdate();
    }

}