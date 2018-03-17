package com.example.yanghanwen.taskmanagementmonster;

import java.util.ArrayList;

/**
 * Created by Terrence on 2018/3/17.
 */

public class TaskList {
    private ArrayList<Task>tasks = new ArrayList<Task>();

    public TaskList(){

    }

    public Task getTask(int i){
        return tasks.get(i);
    }

    public boolean hasTask(Task task){return tasks.contains(task);}

    public void add(Task task){
        tasks.add(task);
    }

    public void delete(Task task){
        tasks.remove(task);
    }
}
