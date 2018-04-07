package com.example.yanghanwen.taskmanagementmonster;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.SystemClock;
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
    private String mode;



    public TaskIntentService(){
        super("TaskIntentService");
    }

    public void displayMessage(String message){

        final String msg = message;
        iServiceHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),msg,
                        Toast.LENGTH_SHORT).show();

            }
        });
    }


    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        iServiceHandler = new Handler();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        mode = intent.getStringExtra("mode");
        Log.d("current mode",intent.getStringExtra("mode"));

        Log.i(TAG,"Service Started");
        if (connectionCheck.isNetWorkAvailable(getApplicationContext())){

            Log.i(TAG,"connected");
        }else{
            Log.i(TAG,"notConnected");


        }

        while (true) {
            Log.d("what is the mode ",""+mode);
            if (connectionCheck.isNetWorkAvailable(getApplicationContext())){
                /**if (TaskList.getInstance().getTasks().size() == 0) {
                    break;
                }
                Log.d("current task is what?","Value" + TaskList.getInstance().getTasks().get(0));
                Task task = TaskList.getInstance().getTasks().get(0);
                addTask.execute(task);
                TaskList.getInstance().getTasks().remove(0);*/

                try {
                    ElasticSearch.DeleteTask deleteTask = new ElasticSearch.DeleteTask();
                    Task mTask = DeleteTaskList.getInstance().getTasks().get(0);
                    ElasticSearch.DeleteTask deleteTask1 = new ElasticSearch.DeleteTask();

                    Log.i("delet","1st");
                    deleteTask.execute(mTask);
                    Log.i("delete","2nd");
                    deleteTask1.execute(mTask);
                    Log.i("delete","2nd pass");

                    DeleteTaskList.getInstance().getTasks().clear();
                }catch (Exception e){
                    Log.i("delete for update","have nothing to delete");
                }

                if(mode == "create"){
                    Log.i("offline ","create mode");
                    for(Task task : TaskList.getInstance().getTasks()){
                        Log.d("current task",""+task);
                        if(taskExsit(task.getUsername(),task.getTaskname())){
                            Log.i("create a conflict task","");
                            /*for(int i = 0; i < 4; i++){
                                displayMessage("Oops,task name is conflicted.Please try another one.");
                            }*/

                        }else{
                            ElasticSearch.AddTask addTask = new ElasticSearch.AddTask();
                            addTask.execute(task);
                        }
                    }
                    TaskList.getInstance().getTasks().clear();
                    break;
                }else{
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

                        ElasticSearch.AddTask addTask1 = new ElasticSearch.AddTask();
                        addTask1.execute(mTask);


                    }
                    TaskList.getInstance().getTasks().clear();
                    break;
                }
            }
        }
    }

    public boolean taskExsit(String username, String taskname){
        ElasticSearch.IsExistTask isExistTask = new ElasticSearch.IsExistTask();
        isExistTask.execute(username + taskname);
        try{
            if(isExistTask.get()){
                return Boolean.TRUE;
            } else {return Boolean.FALSE;}
        }catch (Exception e){
            return Boolean.FALSE;
        }
    }


}
