package com.example.yangwenhan.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ViewTaskDetail extends AppCompatActivity {

    private String taskName;
    private TextView showName;
    private EditText title_key;
    private String search_key;
    private Task task;
    private Button search_bt;
    private Button delete_bt;
    private Button edit_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task_detail);

        showName = (TextView)findViewById(R.id.title);
        title_key = (EditText)findViewById(R.id.task_key);
        search_bt = (Button)findViewById(R.id.search);
        delete_bt = (Button)findViewById(R.id.delete);
        edit_bt = (Button)findViewById(R.id.edit);

        search_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                taskName = title_key.getText().toString();
                search_key = intent.getStringExtra("username")+taskName;
                Log.d("task is", search_key);
                if (existedTask(search_key)){
                    ElasticSearch.GetTask getTask = new ElasticSearch.GetTask();
                    getTask.execute(search_key);
                    try{
                        task = getTask.get();
                        Log.i("print something","should print information");
                        showName.setText(task.getStatus());
                    }
                    catch (Exception e) {
                        Log.i("Error", "Fail to connect to server");
                    }
                }else {
                    Log.i("Error", "Unable to get task object");
                }
            }
        });

        delete_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                taskName = title_key.getText().toString();
                search_key = intent.getStringExtra("username")+taskName;
                if (existedTask(search_key)){
                    ElasticSearch.DeleteTask deleteTask = new ElasticSearch.DeleteTask();
                    deleteTask.execute(task);
                    finish();
//                    try{
//                        task = deleteTask.get();
//                        Log.i("print something","should print information");
//                        showName.setText(task.getStatus());
//                    }
//                    catch (Exception e) {
//                        Log.i("Error", "Fail to connect to server");
//                    }
                }else {
                    Log.i("Error", "Unable to get task object");
                }
            }
        });

        edit_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewTaskDetail.this,EditTask.class);
                intent.putExtra("taskID",search_key);
                startActivity(intent);

            }
        });


//        if (existedTask(search_key)){
//            ElasticSearch.GetTask getTask = new ElasticSearch.GetTask();
//            getTask.execute(search_key);
//            try{
//                task = getTask.get();
//                Log.i("print something","should print information");
//            }
//            catch (Exception e) {
//                Log.i("Error", "Fail to connect to server");
//            }
//        }else{
//            Log.i("Error","Unable to get task object");
//        }


    }
    public boolean existedTask(String taskID) {
        ElasticSearch.IsExistTask isExistTask = new ElasticSearch.IsExistTask();
        isExistTask.execute(taskID);
        try {
            if (isExistTask.get()){
                Log.d("it exist!!!",taskID);
                return true;
            }
            else{
                Log.d("unidentified task",taskID);
                return false;
            }}
        catch (Exception e){
            return false;
        }


    }
}
