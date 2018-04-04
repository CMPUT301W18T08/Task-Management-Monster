package com.example.yanghanwen.taskmanagementmonster;

import android.app.IntentService;
import android.content.Intent;
import android.content.IntentSender;
import android.support.annotation.Nullable;

import com.sun.source.util.TaskListener;

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
                if (TaskListt.getInstance().getTasks().size().equal(0)) {
                    break;
                }

                addTask.execute(TaskList.getInstance().getTasks().get(0));
                TaskListt.getInstance().getTasks().remove(0);

            }
        }
    }
};
