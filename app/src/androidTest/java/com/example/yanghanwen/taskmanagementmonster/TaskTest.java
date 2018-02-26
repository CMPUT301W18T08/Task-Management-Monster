package com.example.yanghanwen.taskmanagementmonster;


import android.test.ActivityInstrumentationTestCase2;

import java.util.ArrayList;

/**
 * Created by superfan1995 on 2018-02-25.
 */

public class TaskTest extends ActivityInstrumentationTestCase2 {

    public TaskTest() {
        super(MainActivity.class); // this definitely not right, just for example
    }

    public void testName() throws Exception {
    }

    public void testGetTaskInfo(){

        ArrayList<Task> tasks = new ArrayList<Task>();
        ArrayList<Bid> bids = new ArrayList<Bid>();
        Bid bid = new Bid("username", "180");
        bids.add(bid);
        Task task = new Task("task1","this is task 1","bidded", bids);
        tasks.add(task);  //assume add function works fine
        assertTrue(tasks.getTask(0).getTitle == "task1");
        assertTrue(tasks.getTask(0).getDescription == "this is task 1");
        assertTrue(tasks.getTask(0).getStatus == "bidded");
        assertTrue(tasks.getTask(0).getLowestBid == "180");
    }

    public void testBidTask(){

        ArrayList<Bid> bids = new ArrayList<Bid>();
        Bid bid = new Bid("userid","180");
        bids.add(bid);
        Task task1 = new Task("task1","this is task 1","bidded", bids);
        Bid bid2 = new Bid("userid1", "220");
        task1.addBid(bid2);
        assertEquals(task1.getBids()[1].getid(),"userid1");
    }

    public void testSearchTask(){

        ArrayList<Task> tasks = new ArrayList<Task>();
        ArrayList<Bid> bids1 = new ArrayList<Bid>();
        Bid bid = new Bid("userid","180");
        bids1.add(bid);
        Task task1 = new Task("task1","this is task 1","bidded", bids1);
        tasks.add(task1);
        ArrayList<Bid> bids2 = new ArrayList<Bid>();
        Bid bid1 = new Bid("userid1","220");
        bids2.add(bid1);
        Task task2 = new Task("task2","this is task 2","bidded", bids2);
        tasks.add(task2);
        Task task = tasks.searchTask("task1");
        Task task3 = tasks.searchTask("task 1");
        assertEquals(task.getTitle(),"task1");
        assertEquals(task3.getTitle(), "task1");

    }

    public void testSetBidStatus(){

        ArrayList<Bid> bids = new ArrayList<Bid>();
        Task task = new Task("task1", "this is task 1", "requested", bids);
        task.setBidStatus("bidded");
        assertEquals(task.getStatus(),"bidded");
    }

    public void testsetTaskName(){
        String username = "Tom";
        String taskname = "task1";
        String description = "this is a message";

        Task task = new Task(username, taskname, description);
        task.setTaskName(name2);
        assertTrue(task.getTaskName() == username);
    }
    public void testsetStatus(){
        String username = "Tom";
        String taskname = "task1";
        String description = "this is a message";
        String status2 = "bidded";

        Task task = new Task(username, taskname, description);
        task.setStatus(status2);
        assertTrue(task.getTaskStatus() == status2);
    }

    public void testsetDescription(){
        String username = "Tom";
        String taskname = "task1";
        String description = "this is a message";
        String description2 = "this is a message 111";

        Task task = new Task(username, taskname, description);
        task.setDescription(description2);
        assertTrue(task.getTaskDescription() == description2);
    }

    public void testNewBid() {

        String username = "Tom";
        String taskname = "task1";
        String description = "this is a message";

        Task task = new Task(username, taskname, description);

        String bider1 = "B1";
        Double amount1 = 1.0;

        task.newBid(bider1, amount1);

        assertTrue(task.hasBid(bider1));
        assertTrue(task.getUserBid(bider1) == amount1);
    }

    public void testHasBid() {

        String username = "Tom";
        String taskname = "task1";
        String description = "this is a message";

        Task task = new Task(username, taskname, description);

        String bider1 = "B1";
        String bider2 = "B2";
        Double amount1 = 1.0;

        task.newBid(bider1, amount1);

        assertTrue(task.hasBid(bider1));
        assertFalse(task.hasBid(bider2));
    }

    public void testGetUserBid() {

        String username = "Tom";
        String taskname = "task1";
        String description = "this is a message";

        Task task = new Task(username, taskname, description);

        String bider1 = "B1";
        Double amount1 = 1.0;

        task.newBid(bider1, amount1);

        assertTrue(task.getUserBid(bider1) == amount1);
    }

    public void testDeclineBid() {

        String username = "Tom";
        String taskname = "task1";
        String description = "this is a message";

        Task task = new Task(username, taskname, description);

        String bider1 = "B1";
        Double amount1 = 1.0;

        task.newBid(bider1, amount1);
        task.declineBid(bider1);

        assertFalse(task.hasBid(bider1));
    }

    public void testModifyBid() {

        String username = "Tom";
        String taskname = "task1";
        String description = "this is a message";

        Task task = new Task(username, taskname, description);

        String bider1 = "B1";
        Double amount1 = 1.0;
        Double amount2 = 2.0;

        task.newBid(bider1, amount1);
        task.modifyBid(bider1, amount2);

        assertTrue(task.getUserBid(bider1) == amount2);
    }

    public void testGetLowestBid() {

        String username = "Tom";
        String taskname = "task1";
        String description = "this is a message";

        Task task = new Task(username, taskname, description);

        String bider1 = "B1";
        String bider2 = "B2";
        String bider3 = "B3";

        Double amount1 = 1.0;
        Double amount2 = 5.0;
        Double amount3 = 2.0;

        task.newBid(bider1, amount1);
        task.newBid(bider2, amount2);
        task.newBid(bider3, amount3);

        assertTrue(task.getLowestBid() == amount1);

        Task task2 = new Task(username, taskname, description);

        task.newBid(bider1, amount3);
        task.newBid(bider2, amount2);
        task.newBid(bider3, amount3);

        assertTrue(task.getLowestBid() == amount3);
    }

}
