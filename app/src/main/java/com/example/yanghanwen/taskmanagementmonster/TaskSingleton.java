package com.example.yanghanwen.taskmanagementmonster;

import java.util.ArrayList;

/**
 * Created by Terrence on 2018/3/13.
 */

public class TaskSingleton {

    private ArrayList<Task>taskArrayList;
    public ArrayList<Task>getTaskArrayList(){return taskArrayList; }
    private static final TaskSingleton ourInstance = new TaskSingleton();

    public static TaskSingleton getInstance() {
        return ourInstance;
    }

    private TaskSingleton() {taskArrayList = new ArrayList<>();}
}
