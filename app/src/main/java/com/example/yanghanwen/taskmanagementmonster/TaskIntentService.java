package com.example.yanghanwen.taskmanagementmonster;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import android.os.Handler;
import android.widget.Toast;

/**
 * Created by Terrence on 03/04/2018.
 */

public class TaskIntentService extends IntentService{

    private static final String TAG = "Task intent service";

    private ConnectionCheck connectionCheck;

    private Handler iServiceHandler;

    private String username;
    private String description;
    private String title;
    private Task mTask;

    public TaskIntentService(){
        super("TaskIntentService");
    }


    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        iServiceHandler = new Handler();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        Log.i(TAG,"Service Started");
        if (connectionCheck.isNetWorkAvailable(getApplicationContext())){

            Log.i(TAG,"connected");
        }else{
            Log.i(TAG,"notConnected");
            iServiceHandler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(),"Oops,data will upload once connected",
                            Toast.LENGTH_SHORT).show();

                }
            });
        }

        while (true) {
            if (connectionCheck.isNetWorkAvailable(getApplicationContext())){
                /**if (TaskList.getInstance().getTasks().size() == 0) {
                    break;
                }
                Log.d("current task is what?","Value" + TaskList.getInstance().getTasks().get(0));
                Task task = TaskList.getInstance().getTasks().get(0);
                addTask.execute(task);
                TaskList.getInstance().getTasks().remove(0);*/
                for(Task task : TaskList.getInstance().getTasks()){
                    Log.d("task in for loop","Value" + task);
                    username = task.getUsername();
                    title = task.getTaskname();
                    description = task.getDescription();
                    Log.d("userName",""+username);
                    Log.d("title",""+title);
                    Log.d("description",""+description);
                    mTask = new Task(username,title,description);
                    ElasticSearch.AddTask addTask = new ElasticSearch.AddTask();
                    addTask.execute(mTask);
                }
                TaskList.getInstance().getTasks().clear();
                break;

            }
        }
    }
}
