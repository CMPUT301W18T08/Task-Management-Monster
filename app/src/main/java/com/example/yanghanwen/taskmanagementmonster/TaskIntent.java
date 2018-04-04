package com.example.yanghanwen.taskmanagementmonster;

import android.app.IntentService;
import android.content.Intent;
import android.content.IntentSender;
import android.support.annotation.Nullable;

/**
 * Created by songxixuan on 2018-04-03.
 */

public class TaskIntentService extends IntentService {
    private ElasticSearch.IsExistTask isExistTask;  // elastic search used to check if task exist
    private ElasticSearch.AddTask addTask;          // elastic search used to add task


    public TaskIntentService(){
        super("TaskIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent){
        while (true) {
            if (connectionCheck.isNetWorkAvailable(getApplicationContext())){
                for (Task task : TaskList.getTasks()){
                    addTask.execute(task);
                }
                break;
            }
        }
    }
};
