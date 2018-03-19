package com.example.yanghanwen.taskmanagementmonster;


import android.test.ActivityInstrumentationTestCase2;

<<<<<<< HEAD

=======
>>>>>>> a8dde2062fe62b353b13558f3797d4f95427a731
/**
 * Created by superfan1995 on 2018-02-25.
 */

public class TaskTest extends ActivityInstrumentationTestCase2 {

    public TaskTest() {
        super(MainActivity.class); // this definitely not right, just for example
    }

    public void testGetUsername () {

        int tid = 2;
        String username = "Tom";
        String taskname = "task1";
        String description = "this is a message";

        Task task = new Task(username, taskname, description);

        assertTrue(task.getUsername() == username);
    }

    public void testGetTaskname () {

        int tid = 2;
        String username = "Tom";
        String taskname = "task1";
        String description = "this is a message";

        Task task = new Task(username, taskname, description);

        assertTrue(task.getTaskname() == taskname);
    }

    public void testGetStatus () {

        int tid = 2;
        String username = "Tom";
        String taskname = "task1";
        String description = "this is a message";

        Task task = new Task(username, taskname, description);

        assertTrue(task.getStatus() == "requested");
    }

    public void testGetDescription () {

        int tid = 2;
        String username = "Tom";
        String taskname = "task1";
        String description = "this is a message";

        Task task = new Task(username, taskname, description);

        assertTrue(task.getDescription() == description);
    }

    public void testSetTaskName(){

        int tid = 2;
        String username = "Tom";
        String username2 = "Tom";
        String taskname = "task1";
        String description = "this is a message";

        Task task = new Task(username, taskname, description);
        task.setTaskname(username2);
        assertTrue(task.getTaskname() == username2);
    }

    public void testSetStatus(){

        int tid = 2;
        String username = "Tom";
        String taskname = "task1";
        String description = "this is a message";
        String status2 = "bidded";

        Task task = new Task(username, taskname, description);
        task.setStatus(status2);
        assertTrue(task.getStatus() == status2);
    }

    public void testSetDescription(){

        int tid = 2;
        String username = "Tom";
        String taskname = "task1";
        String description = "this is a message";
        String description2 = "this is a message 111";

        Task task = new Task(username, taskname, description);
        task.setDescription(description2);
        assertTrue(task.getDescription() == description2);
    }

    public void testHasBid() {

        int tid = 2;
        String username = "Tom";
        String taskname = "task1";
        String description = "this is a message";

        Task task = new Task(username, taskname, description);

        String bider1 = "B1";
        Double amount1 = 1.0;

        assertFalse(task.hasBid());

        task.createNewBid(bider1, amount1);

        assertTrue(task.hasBid());
    }

    public void testNewBid() {

        int tid = 2;
        String username = "Tom";
        String taskname = "task1";
        String description = "this is a message";

        Task task = new Task(username, taskname, description);

        String bidder1 = "B1";
        Double amount1 = 1.0;

        task.createNewBid(bidder1, amount1);

        assertTrue(task.hasBid());
        assertTrue(task.getUserAmount(bidder1).compareTo(amount1) == 0);
    }

    public void testGetUserBid() {

        int tid = 2;
        String username = "Tom";
        String taskname = "task1";
        String description = "this is a message";

        Task task = new Task(username, taskname, description);

        String bidder1 = "B1";
        Double amount1 = 1.0;

        String bidder2 = "B2";
        Double amount2 = 2.0;

        task.createNewBid(bidder1, amount1);

        assertTrue(task.getUserAmount(bidder1).compareTo(amount1) == 0);
    }

    public void testDeclineBid() {

        int tid = 2;
        String username = "Tom";
        String taskname = "task1";
        String description = "this is a message";

        Task task = new Task(username, taskname, description);

        String bider1 = "B1";
        Double amount1 = 1.0;

        task.createNewBid(bider1, amount1);
        task.declineBid(bider1);

        assertFalse(task.hasBid());
    }

    public void testModifyBid() {

        int tid = 2;
        String username = "Tom";
        String taskname = "task1";
        String description = "this is a message";

        Task task = new Task(username, taskname, description);

        String bider1 = "B1";
        Double amount1 = 1.0;
        Double amount2 = 2.0;

        task.createNewBid(bider1, amount1);
        task.modifyBid(bider1, amount2);

        assertTrue(task.getUserAmount(bider1).compareTo(amount2) == 0);
    }

    public void testGetLowestBid() {

        int tid1 = 2;
        int tid2 = 3;
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

        task.createNewBid(bider1, amount1);
        task.createNewBid(bider2, amount2);
        task.createNewBid(bider3, amount3);

        assertTrue(task.getLowestBid().compareTo(amount1) == 0);

        Task task2 = new Task(username, taskname, description);

        task2.createNewBid(bider1, amount3);
        task2.createNewBid(bider2, amount2);
        task2.createNewBid(bider3, amount3);

        assertTrue(task2.getLowestBid().compareTo(amount3) == 0);

    }

}
