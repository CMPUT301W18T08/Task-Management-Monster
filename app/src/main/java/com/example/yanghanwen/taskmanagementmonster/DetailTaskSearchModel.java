package com.example.yanghanwen.taskmanagementmonster;

import android.view.View;

import java.util.ArrayList;

/**
 * Created by superfan1995 on 2018-03-10.
 */

public class DetailTaskSearchModel extends DetailTaskModel {

    private Boolean hasBid;
    private Boolean userBidded;
    private Double userBid;

    public DetailTaskSearchModel (String title, String requestor) {

        super(title, requestor);

        hasBid = super.task.hasBid();

        userBid = super.task.getUserAmount(super.username);

        if (userBid == null) {

            userBidded = Boolean.FALSE;
        }

        else {

            userBidded = Boolean.TRUE;
        }
    }

    public String getBidInfo() {

        if ( hasBid ) {

            return "Current Lowest Bid:";
        }

        else {

            return "Task currently has no bid";
        }
    }

    public String getBidLowest() {

        if ( hasBid ) {

            return "$ " + super.task.getLowestBid().toString();
        }

        else {

            return "";
        }
    }

    public String getMyBidInfo() {

        if ( userBidded ) {

            return "Current My Bid:";
        }

        else {

            return "";
        }
    }

    public String getMyBid() {

        if ( userBidded ) {

            return "$" + userBid.toString();
        }
        else {

            return "";
        }
    }

    public String getButtonText1() {

        if ( userBidded ) {

            return "Modify My Bid";
        }

        else {

            return "Bid This Task";
        }
    }

    public String getButtonText2() {

        if ( userBidded ) {

            return "Decline My Bid";
        }

        else {

            return "";
        }
    }

    public ArrayList<Bid> getBidsList() {

        return null;
    }

    public int visibilityBidInfo() {

        return  View.VISIBLE;
    }

    public int visibilityBidLowest() {

        if ( hasBid ) {

            return View.VISIBLE;
        }

        else {

            return View.GONE;
        }
    }

    public int visibilityMyBidInfo() {

        if ( userBidded )  {

            return View.VISIBLE;
        }

        else {

            return View.GONE;
        }
    }

    public int visibilityMyBid() {

        if ( userBidded ) {

            return View.VISIBLE;
        }

        else {

            return View.GONE;
        }
    }

    public int visibilityEdit() {

        return View.VISIBLE;
    }

    public int visibilityEditTitle() {

        return View.GONE;
    }

    public int visibilityEditDescription() {

        return View.GONE;
    }

    public int visibilityChangeButton() {

        return View.VISIBLE;
    }

    public int visibilityDeclineButton() {

        if ( userBidded ) {

            return View.VISIBLE;
        }

        else {

            return View.GONE;
        }
    }

    public int visibilityListView() {

        return View.GONE;
    }

    public void changeButtonAction (String newValue) {

        userBid = Double.parseDouble(newValue);

        if (userBidded) {

            super.task.modifyBid(super.username, userBid);
        }

        else {

            super.task.createNewBid(super.username, userBid);
        }

        // update task by elastic search
        super.taskUpdate();

        hasBid = Boolean.TRUE;

        userBidded =Boolean.TRUE;
    }

    public void declineButtonAction (String newValue) {

        super.task.declineBid(super.username);

        // update task by elastic search
        super.taskUpdate();

        hasBid = super.task.hasBid();

        userBid = null;

        userBidded = Boolean.FALSE;
    }

}