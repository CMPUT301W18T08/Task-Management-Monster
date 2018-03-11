package com.example.yanghanwen.taskmanagementmonster;

import android.view.View;

/**
 * Created by superfan1995 on 2018-03-10.
 */

public class DetailTaskProviderModel extends DetailTaskModel {

    public DetailTaskProviderModel (String title, String requestor) {

        super(title, requestor);
    }

    public String getBidInfo() {

        return "Current My Bid";
    }

    public String getBidLowest() {

        Double lowestBid = super.task.getLowestBid();

        return lowestBid.toString();
    }

    public String getMyBidInfo() {

        return "Current My Bid";
    }

    public String getMyBid() {

        Double myBid = super.task.getUserAmount(super.username);

        return myBid.toString();
    }

    public String getButtonText1() {

        return "Modify My Bid";
    }

    public String getButtonText2() {

        return "Return My Bid";
    }

    public int visibilityBidInfo() {

        return View.VISIBLE;
    }

    public int visibilityBidLowest() {

        return View.VISIBLE;
    }

    public int visibilityMyBidInfo() {

        return View.VISIBLE;
    }

    public int visibilityMyBid()  {

        return View.VISIBLE;
    }

    public int visibilityEdit()  {

        return View.VISIBLE;
    }


    public int visibilityButton1()  {

        return View.VISIBLE;
    }

    public int visibilityButton2()  {

        return View.VISIBLE;
    }

}
