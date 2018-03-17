package com.example.yanghanwen.taskmanagementmonster;

import android.view.View;

import java.util.ArrayList;

/**
 * Created by superfan1995 on 2018-03-10.
 */

public class DetailTaskProviderModel extends DetailTaskModel {

    Boolean assigned;

    public DetailTaskProviderModel (String title, String requestor) {

        super(title, requestor);

        if (super.task.getStatus() == "assigned") {

            assigned = Boolean.TRUE;
        }

        else {

            assigned = Boolean.FALSE;
        }

    }

    public String getBidInfo() {

        if (assigned) {

            return "Accepted Bid:";
        }

        else {

            return "Current Lowest Bid:";
        }
    }

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

    public String getMyBidInfo() {

        if (assigned) {

            return "";
        }

        else{

            return "Current My Bid:";
        }
    }

    public String getMyBid() {

        if (assigned) {

            return "";
        }

        else {

            Double myBid = super.task.getUserAmount(super.username);
            return "$ " + myBid.toString();
        }
    }

    public String getButtonText1() {

        if (assigned) {

            return "";
        }

        else {

            return "Modify My Bid:";
        }
    }

    public String getButtonText2() {

        if (assigned) {

            return "";
        }

        else {

            return "Return My Bid";
        }
    }

    public ArrayList<Bid> getBidsList() {

        return null;
    }

    public int visibilityBidInfo() {

        return View.VISIBLE;
    }

    public int visibilityBidLowest() {

        return View.VISIBLE;
    }

    public int visibilityMyBidInfo() {

        if (assigned) {

            return View.GONE;
        }

        else {
            return View.VISIBLE;
        }
    }

    public int visibilityMyBid() {

        if (assigned) {

            return View.GONE;
        }

        else {
            return View.VISIBLE;
        }
    }

    public int visibilityEdit() {

        if (assigned) {

            return View.GONE;
        }

        else {
            return View.VISIBLE;
        }
    }

    public int visibilityEditTitle() {

        return View.GONE;
    }

    public int visibilityEditDescription() {

        return View.GONE;
    }

    public int visibilityListView() {

        return View.GONE;
    }

    public int visibilityChangeButton() {

        if (assigned) {

            return View.GONE;
        }

        else {

            return View.VISIBLE;
        }
    }

    public int visibilityDeclineButton() {

        if (assigned) {

            return View.GONE;
        }

        else {

            return View.VISIBLE;
        }
    }

    public void changeButtonAction (String newValue) {

        Double userbid = Double.parseDouble(newValue);

        super.task.modifyBid(super.username, userbid);

        // update task by elastic search
        super.taskUpdate();
    }

    public void declineButtonAction (String newValue) {

        super.task.declineBid(super.username);

        // update task by elastic search
        super.taskUpdate();
    }

}
