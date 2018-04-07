package com.example.yanghanwen.taskmanagementmonster;

import java.util.ArrayList;

/**
 * Created by Terrence on 05/04/2018.
 */

public class DeleteTaskList {
    private ArrayList<Task>tasks;
    public ArrayList<Task>getTasks(){return tasks;}
    private static final DeleteTaskList ourInstance = new DeleteTaskList();

    public static DeleteTaskList getInstance() {
        return ourInstance;
    }

    private DeleteTaskList() {tasks = new ArrayList<Task>();
    }
}
