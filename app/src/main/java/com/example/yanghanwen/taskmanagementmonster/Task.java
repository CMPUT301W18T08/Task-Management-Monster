package com.example.yanghanwen.taskmanagementmonster;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by superfan1995 on 2018-02-26.
 */


 /** Object class of task
  *
  * Contain the information and operation method of the current task.
  *
  * @version 1.0
  */
public class Task {

    private String username;        // the username of task creator
    private String taskname;        // the name of the task
    private String status;          // current status of the task
    private String description;     // description of the task
    private ArrayList<Bid> bids;    // list of all bids on this task
    private Bitmap imageMap;

     /**
      * Create an empty task object.
      *
      * This constructor is used for elastic search.
      */
    public Task () {

    }

     /**
      * Create a task object by using the input
      *
      * @param username string name of the task creator
      * @param taskname string name of the task
      * @param description string description of task
      */
    public Task (String username, String taskname , String description) {

        this.username = username;
        this.taskname = taskname;
        this.description = description;

        // set the initial status as "requested"
        status = "requested";

        // initialize the list of bids object
        bids = new ArrayList<Bid>();

        imageMap = null;
    }

     /**
      * Return the username
      *
      * @return username of this task
      */
    public String getUsername () {

        return this.username;
    }

     /**
      * Return the taskname
      *
      * @return taskname of this task
      */
    public String getTaskname () {

        return taskname;
    }

     /**
      * Return the status
      *
      * @return current status of this task
      */
    public String getStatus () {

        return this.status;
    }

     /**
      * Return description
      *
      * @return description of this task
      */
    public String getDescription () {

        return this.description;
    }

     /**
      * Change current taskname to the new value
      *
      * @param taskname the new taskname of this task
      */
    public void setTaskname (String taskname) {

        this.taskname = taskname;
    }

     /**
      * Change current status to the new value
      *
      * @param status the new status of this task
      */
    public void setStatus (String status) {

        this.status = status;
    }

     /**
      * Change description of this task
      *
      * @param description the new description of this task
      */
    public void setDescription (String description) {

        this.description = description;
    }

     /**
      * Create a new bid object and add it into the bid list of this task
      *
      * If current status is requested, then change the status to bidded as there are a bid
      * on this task.
      *
      * @param bidder username of user that bid this task
      * @param amount amount of bid that user want to bid
      */
    public void createNewBid(String bidder, Double amount) {

        Bid bid = new Bid(bidder, amount);
        bids.add(bid);

        if (this.status.equals("requested")) {

            this.setStatus("bidded");
        }

    }

     /**
      * Judge this task has at least one bid, and return a boolean True is it has at least one
      * bid, else return false
      *
      * @return
      */
    public Boolean hasBid() {

        int size = bids.size();

        if (size == 0) {
            return Boolean.FALSE;
        }
        else {
            return Boolean.TRUE;
        }
    }

     /**
      * Return an ArrayList of bids for function that list all bids of this task in the listview.
      *
      * @return The bids list of this task
      */
    public ArrayList<Bid> getBidList() {

        return bids;
    }

     /**
      * By using a input username, get the user's bid amount on this task.
      *
      * If the user haven't bid on this task, then return null.
      *
      * @param bidder a user's username
      * @return the Double value of user's bid on this task
      */
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

     /**
      * Get the lowest bid amount in all bids of this task
      *
      * @return the lowest bid amount of this task
      */
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

     /**
      * Change the one of user's bid amount on this task
      *
      * @param bidder the username of modified bid's user
      * @param newAmount the new bid amount of the user's bid
      */
    public void modifyBid(String bidder, Double newAmount) {

        if ( this.hasBid() ) {

            int maxSize = bids.size();

            for (int i = 0; i < maxSize; i = i + 1) {

                Bid bid = bids.get(i);

                if (bid.getUserName().equals(bidder)) {

                    bid.setAmount(newAmount);
                    break;
                }
            }
        }
    }

     /**
      * Decline a user's bid on this task
      *
      * If after decline there are no bid on this task, reset the status to requested.
      *
      * @param bidder the username of the declined task's user
      */
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

     /**
      * Delete all bids on this task and reset the status to requested
      */
    public void emptyBids() {

        bids.clear();

        this.setStatus("requested");
    }

     /**
      * Return a bid in the specified position in the bids list
      *
      * @param position the position of the bid in the bids arrayList
      * @return The bid object at the specified position
      */
    public Bid getBid(int position) {

        Bid bid = null;

        if ( hasBid() ) {

            bid = bids.get(position);
        }

        return bid;

    }

     /**
      * Assigned the task to a user
      *
      * All the other bids on this task will be deleted, only the assigned user's bid
      * will remain in the bids list.
      *
      * @param username the username of assigned user
      */
    public void setAssigned (String username) {

        Double amount = getUserAmount(username);

        this.emptyBids();

        this.createNewBid(username, amount);

        this.setStatus("assigned");
    }

     /**
      * Set the task to done status.
      */
    public void setDone() {

        this.setStatus("done");
    }

     /**
      * return a string summarize the general information of the task
      *
      * @return an String used to describe the task in listView
      */
    public String toString() {
        return username + "\n" + taskname + "\n" + description + "\n" + status + "\n" + bids;
    }

    public void setImageMap(Bitmap bitmap) {

        this.imageMap = bitmap;
    }

    public Bitmap getImageMap() {

        return this.imageMap;
    }

    public void deleteImageMap() {

        this.imageMap = null;
    }

     public Boolean hasImageMap() {

         if (this.imageMap == null) {

             return Boolean.FALSE;
         }
         else {

             return Boolean.TRUE;
         }
     }

}