package com.example.yanghanwen.taskmanagementmonster;

import android.util.Log;

import android.util.Log;
import android.widget.Toast;

/**
 * Created by superfan1995 on 2018-03-09.
 */

/**
 * MVC model for the create task activity, compute and return required data.
 *
 * @version 1.0
 */
public class NewTaskModel {

    // this is not good, will be change in the future

    private ElasticSearch.IsExistTask isExistTask;  // elastic search used to check if task exist
    private ElasticSearch.AddTask addTask;          // elastic search used to add task

    private final String username;                  // the username of the task creator
    //public TaskList taskQueue;
    /**
     * Construct a model instance for the NewTaskActivity.
     *
     * get the current user's username, as well as creating the necessary elastic search
     * object to add the task to the database.
     */
    public NewTaskModel () {

        this.username = MainActivity.mainModel.getUsername();

        this.isExistTask = new ElasticSearch.IsExistTask();
        this.addTask = new ElasticSearch.AddTask();
    }

    /**
     * Create a new task by input a taskname string and a description string
     *
     * @param taskname the task name of new task
     * @param description the description of new task
     */
    public void createNewTask (String taskname, String description) {

        /*if (existTask(taskname)) {
            Log.d("message", "sstask already exist");
            // if the task already exist, don't create it
        }*/
        //else {
        Log.i("create ","create new task");
        Task task = new Task(this.username, taskname, description);

            //addTask.execute(task);
        TaskList.getInstance().getTasks().add(task);
        try {
            Log.d("now the task is ", "Value" + TaskList.getInstance().getTasks().get(1));
        }catch (Exception e){
            Log.d("the 2nd task is","Value"+ TaskList.getInstance().getTasks().get(0));
        }
        Log.d("message", "task successfully created");

            // create new task
        //}

    }

    /**
     * Check weather or not a task already exist
     *
     * @param taskname the taskname of the task
     * @return boolean true or false
     */
    public boolean existTask (String taskname) {

        isExistTask.execute(this.username + taskname);

        try {
            if (isExistTask.get()) {
                return Boolean.TRUE;
            }
            else {
                return Boolean.FALSE;
            }
        }
        catch (Exception e) {
            return Boolean.FALSE;
        }
    }

}