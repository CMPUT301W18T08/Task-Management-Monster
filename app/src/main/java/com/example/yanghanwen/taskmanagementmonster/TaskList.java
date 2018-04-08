package com.example.yanghanwen.taskmanagementmonster;

import java.util.ArrayList;

/**
 * Created by Terrence on 2018/3/17.
 */

public class TaskList {
    private ArrayList<Task> tasks;

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    private static TaskList ourInstance = new TaskList();

    public static TaskList getInstance() {
        return ourInstance;
    }

    private TaskList() {
        tasks = new ArrayList<Task>();
    }
}

