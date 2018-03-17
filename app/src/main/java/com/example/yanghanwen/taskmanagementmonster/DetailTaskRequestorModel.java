package com.example.yanghanwen.taskmanagementmonster;

import android.view.View;

import java.util.ArrayList;

/**
 * Created by superfan1995 on 2018-03-12.
 */

public class DetailTaskRequestorModel extends DetailTaskModel {

    String status;

    public DetailTaskRequestorModel(String title, String requestor) {

        super(title, requestor);

        status = super.task.getStatus();
    }

    public String getBidInfo() {

        if (status == "requested") {

            return "Task currently has no bid";
        }

        else if (status == "bidded") {

            return "Current Lowest Bid:";
        }

        else {

            return "Task Provider:";
        }

    }

    public String getBidLowest() {

        if (status == "requested") {

            return "";
        }

        else if (status == "bidded") {

            Double lowestBid = super.task.getLowestBid();
            return "$ " + lowestBid.toString();
        }

        else {

            Bid bid = super.task.getBid(0);
            return bid.getUserName();
        }

    }

    public String getMyBidInfo() {

        if (status == "requested") {

            return "";
        }

        else if (status == "bidded") {

            return "";
        }

        else {

            return "Accepted Bid";
        }
    }

    public String getMyBid() {

        if (status == "requested") {

            return "";
        }

        else if (status == "bidded") {

            return "";
        }

        else {

            Bid bid = super.task.getBid(0);

            String assignUser = bid.getUserName();

            Double myBid = super.task.getUserAmount(assignUser);
            return "$ " + myBid.toString();
        }
    }

    public String getButtonText1() {

        if (status == "requested") {

            return "Modify Title";
        }

        else if (status == "assigned") {

            return "Task Complete";
        }

        else {

            return "";
        }
    }

    public String getButtonText2() {

        if (status == "requested") {

            return "Modify Description";
        }

        else if (status == "assigned") {

            return "Task Incomplete";
        }

        else {

            return "";
        }
    }

    public ArrayList<Bid> getBidsList() {

        return super.task.getBidList();
    }

    public int visibilityBidInfo() {

        return View.VISIBLE;

    }

    public int visibilityBidLowest() {

        if (status == "requested") {

            return View.GONE;
        }

        else {

            return View.VISIBLE;
        }
    }

    public int visibilityMyBidInfo() {

        if (status == "requested") {

            return View.GONE;
        }

        else if (status == "bidded") {

            return View.GONE;
        }

        else {

            return View.VISIBLE;
        }
    }

    public int visibilityMyBid() {

        {

            if (status == "requested") {

                return View.GONE;
            }

            else if (status == "bidded") {

                return View.GONE;
            }

            else {

                return View.VISIBLE;
            }

        }
    }

    public int visibilityEdit() {

        return View.GONE;
    }

    public int visibilityEditTitle() {

        if (status == "requested") {

            return View.VISIBLE;
        }

        else {

            return View.GONE;
        }
    }

    public int visibilityEditDescription() {

        if (status == "requested") {

            return View.VISIBLE;
        }

        else {

            return View.GONE;
        }
    }

    public int visibilityChangeButton() {

        if (status == "requested") {

            return View.VISIBLE;
        }

        else if (status == "assigned") {

            return View.VISIBLE;
        }

        else {

            return View.GONE;
        }
    }

    public int visibilityDeclineButton()  {

        if (status == "requested") {

            return View.VISIBLE;
        }

        else if (status == "assigned") {

            return View.VISIBLE;
        }

        else {

            return View.GONE;
        }
    }

    public int visibilityListView() {

        if (status == "bidded") {

            return View.VISIBLE;
        }

        else {

            return View.GONE;
        }
    }

    public void changeButtonAction(String newValue) {

        if (status == "requested") {

            super.task.setTaskname(newValue);
        }

        else if (status == "assigned") {

            super.task.setDone();

            status = "done";
        }

        // update task by elastic search
        super.taskUpdate();
    }

    public void declineButtonAction(String newValue) {

        if (status == "requested") {

            super.task.setDescription(newValue);
        }

        else if (status == "assigned") {

            super.task.emptyBids();

            status = "requested";
        }

        // update task by elastic search
        super.taskUpdate();
    }


    @Override
    public void declineBid(int position) {

        super.declineBid(position);

        status = super.getStatus();
    }

    @Override
    public void assignBid(int position) {

        super.assignBid(position);

        status = super.getStatus();
    }

}
