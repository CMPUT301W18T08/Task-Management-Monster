package com.example.yanghanwen.taskmanagementmonster;

import java.util.ArrayList;

/**
 * Created by superfan1995 on 2018-02-26.
 */

public class Task {

    private String username;
    private String taskname;
    private String status;
    private String description;
    private ArrayList<Bid> bids;

    public Task () {

    }

    public Task (String username, String taskname , String description) {

        this.username = username;
        this.taskname = taskname;
        this.description = description;

        status = "requested";
        bids = new ArrayList<Bid>();
    }

    public String getUsername () {

        return this.username;
    }

    public String getTaskname () {

        return taskname;
    }

    public String getStatus () {

        return this.status;
    }

    public String getDescription () {

        return this.description;
    }

    public void setTaskname (String taskname) {

        this.taskname = taskname;
    }

    public void setStatus (String status) {

        this.status = status;
    }

    public void setDescription (String description) {

        this.description = description;
    }

    public void createNewBid(String bidder, Double amount) {

        Bid bid = new Bid(bidder, amount);
        bids.add(bid);

        if (this.status.equals("requested")) {

            this.setStatus("bidded");
        }

    }

    public Boolean hasBid() {

        int size = bids.size();

        if (size == 0) {
            return Boolean.FALSE;
        }
        else {
            return Boolean.TRUE;
        }
    }

    public ArrayList<Bid> getBidList() {

        return bids;
    }

    public Double getUserAmount(String bidder) {

        Double userAmount = null;

        if ( this.hasBid() ) {

            int maxSize = bids.size();

            for (int i = 0; i < maxSize; i = i + 1) {

                Bid bid = bids.get(i);

                if (bid.getUserName().equals(bidder) ) {

                    userAmount = bid.getAmount();
                    break;
                }
            }
        }

        return userAmount;
    }

    public Double getLowestBid() {

        Double result = null;

        if ( this.hasBid() ) {

            int maxSize = bids.size();

            for (int i = 0; i < maxSize; i = i + 1) {

                Bid bid = bids.get(i);

                if (result == null) {
                    result = bid.getAmount();
                }

                if (bid.getAmount() < result) {
                    result = bid.getAmount();
                }
            }

        }

        return result;

    }

    public void modifyBid(String bidder, Double amount) {

        if ( this.hasBid() ) {

            int maxSize = bids.size();

            for (int i = 0; i < maxSize; i = i + 1) {

                Bid bid = bids.get(i);

                if (bid.getUserName().equals(bidder)) {

                    bid.setAmount(amount);
                    break;
                }
            }
        }
    }

    public void declineBid(String bidder) {

        if ( this.hasBid() ) {

            int maxSize = bids.size();

            for (int i = 0; i < maxSize; i = i + 1) {

                Bid bid = bids.get(i);

                if (bid.getUserName().equals(bidder)) {

                    bids.remove(bid);
                    break;
                }
            }
        }

        if (!this.hasBid()) {

            this.setStatus("requested");
        }
    }

    public void emptyBids() {

        bids.clear();

        this.setStatus("requested");
    }

    public Bid getBid(int position) {

        Bid bid = null;

        if ( hasBid() ) {

            bid = bids.get(position);
        }

        return bid;

    }

    public void setAssigned (String username) {

        Double amount = getUserAmount(username);

        this.emptyBids();

        this.createNewBid(username, amount);

        this.setStatus("assigned");
    }

    public void setDone() {

        this.setStatus("done");
    }

    public String toString() {
        return username + "\n" + taskname + "\n" + description + "\n" + status + "\n" + bids;
    }

}