package com.example.yanghanwen.taskmanagementmonster;

import android.view.View;

/**
 * Created by superfan1995 on 2018-03-10.
 */

public class DetailTaskSearchModel extends DetailTaskModel {

    private Boolean hasBid;
    private Boolean userBidded;
    private Double userbid;

    public DetailTaskSearchModel (String title, String requestor) {

        super(title, requestor);

        hasBid = super.task.hasBid();

        userbid = super.task.getUserAmount(super.username);

        if (userbid == null) {

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

            return "$ " + userbid.toString();
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

    public void changeButtonAction (String newValue) {

        userbid = Double.parseDouble(newValue);

        if (userBidded) {

            super.task.modifyBid(super.username, userbid);
        }

        else {

            super.task.createNewBid(super.username, userbid);
        }

        // here update the new task
        MainActivity.mockTest = task;
        // end of update

        hasBid = Boolean.TRUE;

        userBidded =Boolean.TRUE;
    }

    public void declineButtonAction (String newValue) {

        super.task.declineBid(super.username);

        // here update the new task
        MainActivity.mockTest = task;
        // end of update

        hasBid = super.task.hasBid();

        userbid = null;

        userBidded = Boolean.FALSE;

    }

}
