package com.example.yanghanwen.taskmanagementmonster;

import android.view.View;

import java.util.ArrayList;

/**
 * Created by superfan1995 on 2018-03-12.
 */

/**
 *
 */
public class DetailTaskRequestorModel extends DetailTaskModel {

    String status;

    /**
     *
     * @param title
     * @param requestor
     */
    public DetailTaskRequestorModel(String title, String requestor) {

        super(title, requestor);

        status = super.task.getStatus();
    }

    /**
     *
     * @return
     */
    public String getBidInfo() {

        if (status.equals("requested")) {

            return "Task currently has no bid";
        }

        else if (status.equals("bidded")) {

            return "Current Lowest Bid:";
        }

        else {

            return "Task Provider:";
        }

    }

    /**
     *
     * @return
     */
    public String getBidLowest() {

        if (status.equals("requested")) {

            return "";
        }

        else if (status.equals("bidded")) {

            Double lowestBid = super.task.getLowestBid();
            return "$ " + lowestBid.toString();
        }

        else {

            Bid bid = super.task.getBid(0);
            return bid.getUserName();
        }

    }

    /**
     *
     * @return
     */
    public String getMyBidInfo() {

        if (status.equals("requested")) {

            return "";
        }

        else if (status.equals("bidded")) {

            return "";
        }

        else {

            return "Accepted Bid";
        }
    }

    /**
     *
     * @return
     */
    public String getMyBid() {

        if (status.equals("requested")) {

            return "";
        }

        else if (status.equals("bidded")) {

            return "";
        }

        else {

            Bid bid = super.task.getBid(0);

            String assignUser = bid.getUserName();

            Double myBid = super.task.getUserAmount(assignUser);
            return "$ " + myBid.toString();
        }
    }

    /**
     *
     * @return
     */
    public String getButtonText1() {

        if (status.equals("requested")) {

            return "Modify Title";
        }

        else if (status.equals("assigned")) {

            return "Task Complete";
        }

        else {

            return "";
        }
    }

    /**
     *
     * @return
     */
    public String getButtonText2() {

        if (status.equals("requested")) {

            return "Modify Description";
        }

        else if (status.equals("assigned")) {

            return "Task Incomplete";
        }

        else {

            return "";
        }
    }

    /**
     *
     * @return
     */
    public ArrayList<Bid> getBidsList() {

        return super.task.getBidList();
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

        if (status.equals("requested")) {

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
    public int visibilityMyBidInfo() {

        if (status.equals("requested")) {

            return View.GONE;
        }

        else if (status.equals("bidded")) {

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

        {

            if (status.equals("requested")) {

                return View.GONE;
            }

            else if (status.equals("bidded")) {

                return View.GONE;
            }

            else {

                return View.VISIBLE;
            }

        }
    }

    /**
     *
     * @return
     */
    public int visibilityEdit() {

        return View.GONE;
    }

    public int visibilityEditTitle() {

        if (status.equals("requested")) {

            return View.VISIBLE;
        }

        else {

            return View.GONE;
        }
    }

    /**
     *
     * @return
     */
    public int visibilityEditDescription() {

        if (status.equals("requested")) {

            return View.VISIBLE;
        }

        else {

            return View.GONE;
        }
    }

    /**
     *
     * @return
     */
    public int visibilityChangeButton() {

        if (status.equals("requested")) {

            return View.VISIBLE;
        }

        else if (status.equals("assigned")) {

            return View.VISIBLE;
        }

        else {

            return View.GONE;
        }
    }

    /**
     *
     * @return
     */
    public int visibilityDeclineButton()  {

        if (status.equals("requested")) {

            return View.VISIBLE;
        }

        else if (status.equals("assigned")) {

            return View.VISIBLE;
        }

        else {

            return View.GONE;
        }
    }

    /**
     *
     * @return
     */
    public int visibilityListView() {

        if (status.equals("bidded")) {

            return View.VISIBLE;
        }

        else {

            return View.GONE;
        }
    }

    /**
     *
     * @param newValue The new value of the change
     */
    public void changeButtonAction(String newValue) {

        if (status.equals("requested")) {

            super.taskModifed(newValue);
        }

        else if (status.equals("assigned")) {

            super.task.setDone();

            status = "done";

            // update task by elastic search
            super.taskUpdate();
        }
    }

    /**
     *
     * @param newValue the new value of the change
     */
    public void declineButtonAction(String newValue) {

        if (status.equals("requested")) {

            super.task.setDescription(newValue);
        }

        else if (status.equals("assigned")) {

            super.task.emptyBids();

            status = "requested";
        }

        // update task by elastic search
        super.taskUpdate();
    }

    /**
     * Decline the bid at the certain position, and then update to the new status
     *
     * @param position the position of the bid in Arraylist bids
     */
    @Override
    public void declineBid(int position) {

        super.declineBid(position);

        status = super.getStatus();
    }

    /**
     * Assigned the bid at the certain position, then update to the new status
     *
     * @param position the position of the bid in Arraylist bids
     */
    @Override
    public void assignBid(int position) {

        super.assignBid(position);

        status = super.getStatus();
    }

}