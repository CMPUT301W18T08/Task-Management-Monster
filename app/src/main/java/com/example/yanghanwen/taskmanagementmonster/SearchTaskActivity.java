package com.example.yanghanwen.taskmanagementmonster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class SearchTaskActivity extends AppCompatActivity {

    private EditText searchKeyWord;
    private int tid;
    private String username;
    private String taskname;
    private String description;
    public ArrayList<Task> taskList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_task);

        Task task = new Task(tid, username, taskname, description);
        Task task1 = new Task(tid, username, taskname, description);
        Task task2 = new Task(tid, username, taskname, description);
        Task task3 = new Task(tid, username, taskname, description);
        Task task4 = new Task(tid, username, taskname, description);
        Task task5 = new Task(tid, username, taskname, description);
        Task task6 = new Task(tid, username, taskname, description);
        Task task7 = new Task(tid, username, taskname, description);
        Task task8 = new Task(tid, username, taskname, description);

        task.setTaskname("This is task 1");
        task.setStatus("Bidded");
        task.setDescription("This task has been bidded");
        task1.setTaskname("task 2");
        task1.setStatus("bidded");
        task1.setDescription("This task has been bidded");
        task2.setTaskname("task 3");
        task2.setStatus("Bidded");
        task2.setDescription("This task has been bidded");
        task3.setTaskname("task 4");
        task3.setStatus("Bidded");
        task3.setDescription("This task has been bidded");
        task4.setTaskname("task 5");
        task4.setStatus("Bidded");
        task4.setDescription("This task has been bidded");
        task5.setTaskname("task 6");
        task5.setStatus("Bidded");
        task5.setDescription("This task has been bidded");
        task6.setTaskname("task 7");
        task6.setStatus("Bidded");
        task6.setDescription("This task has been bidded");
        task7.setTaskname("task 8");
        task7.setStatus("Bidded");
        task7.setDescription("This task has been bidded");
        task8.setTaskname("task 9");
        task8.setStatus("Bidded");
        task8.setDescription("This task has been bidded");

        taskList.add(task);
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        taskList.add(task4);
        taskList.add(task5);
        taskList.add(task6);
        taskList.add(task7);
        taskList.add(task8);

        Button searchButton = (Button) findViewById(R.id.search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchKeyWord = (EditText) findViewById(R.id.typeInSearch);
                final String KeyWord = searchKeyWord.getText().toString();
                for(int i = 0; i < taskList.size(); i++) {
                    if((taskList.get(i).getTaskname()).contains(KeyWord)) {
                        Intent intent = new Intent(SearchTaskActivity.this, SearchResultActivity.class);
                        intent.putExtra("index", i);
                        setResult(RESULT_OK);
                        startActivity(intent);
                    }
                }
            }
        });
    }
}
