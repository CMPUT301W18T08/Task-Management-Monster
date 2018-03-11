package com.example.yanghanwen.taskmanagementmonster;

/**
 * Created by superfan1995 on 2018-03-10.
 */

public class DetailTaskSearchModel extends DetailTaskModel {

    private Boolean userBidded;

    public DetailTaskSearchModel (String title, String requestor) {

        super(title, requestor);

        Double userBid = super.task.getUserAmount(super.username);

        if (userBid == null ) {

            userBidded = Boolean.FALSE;
        }

        else {

            userBidded = Boolean.TRUE;
        }
        
    }

    public String getBidInfo() {

    }


    public String getBidLowest();
    public String getMyBidInfo();
    public String getMyBid();

    public String getButtonText1();
    public String getButtonText2();

    public int visibilityBidInfo();
    public int visibilityBidLowest();
    public int visibilityMyBidInfo();
    public int visibilityMyBid();
    public int visibilityEdit();
    public int visibilityButton1();
    public int visibilityButton2();

}
