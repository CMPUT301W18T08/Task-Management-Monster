package com.example.yanghanwen.taskmanagementmonster;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Terrence on 03/04/2018.
 */

public class TaskIntentService extends IntentService{

    private static final String TAG = "Task intent service";

    private ConnectionCheck connectionCheck;



    public TaskIntentService(){
        super("TaskIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        Log.i(TAG,"Service Started");
        if (connectionCheck.isNetWorkAvailable(getApplicationContext())){

            Log.i(TAG,"connected");
        }else{
            Log.i(TAG,"notConnected");
        }
        Log.d("intentService task","Value" + TaskList.getInstance().getTasks().get(0));



    }
}
