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

        String username = "Tom";
        String taskname = "task1";
        String description = "this is a message";

        Task tasks = new Task(username, taskname, description);

        assertTrue(tasks.getTid() == 1);
        assertTrue(tasks.getTitle() == "task1");
        assertTrue(tasks.getDescription() == "this is task 1");
        assertTrue(tasks.getStatus() == "bidded");
    }

    public void testsetTaskName(){
        String username = "Tom";
        String username2 = "Tom";
        String taskname = "task1";
        String description = "this is a message";

        Task task = new Task(username, taskname, description);
        task.setTaskName(username2);
        assertTrue(task.getTaskName() == username2);
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
