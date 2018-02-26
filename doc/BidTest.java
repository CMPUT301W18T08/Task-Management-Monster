package com.example.newapplication;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by superfan1995 on 2018-02-23.
 */

public class BidTest extends ActivityInstrumentationTestCase2 {

    public BidTest() {
        super(MainActivity.class); // this definitely not right, just for example
    }

    public void testGetUid() {

        int uid = 15;
        double amount = 0;

        Bid bid = new Bid(uid, amount);
]
        assertTrue(bid.getUid() == uid);
    }

    public void testGetAmount() {

        int uid = 1;
        double amount1 = 15;
        double amount2 = 20.111;

        Bid bid1 = new Bid(uid, amount1);
        Bid bid2 = new Bid(uid, amount2);

        assertTrue(bid1.getAmount() == amount1);
        assertTrue(bid2.getAmount() == amount2);
    }

    public void testSetAmount() {

        int uid = 1;
        double amount1 = 15.1;
        double amount2 = 16.1;

        Bid bid = new Bid(uid, amount1);
        bid.setAmount(amount2);

        assertTrue(bid.getAmount() == amount2);
    }

}
