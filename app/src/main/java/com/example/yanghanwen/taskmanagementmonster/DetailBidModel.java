package com.example.yanghanwen.taskmanagementmonster;

/**
 * Created by superfan1995 on 2018-03-13.
 */

public class DetailBidModel {

    private int position;
    private String provider;
    private String amount;

    public DetailBidModel(int position, String provider, String amount) {

        this.position = position;
        this.provider = provider;
        this.amount = amount;
    }

    public int getPosition() {

        return position;
    }

    public String getProvider() {

        return provider;
    }

    public String getAmount() {

        return amount;
    }

}
