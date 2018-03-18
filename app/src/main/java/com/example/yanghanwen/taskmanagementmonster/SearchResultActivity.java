package com.example.yanghanwen.taskmanagementmonster;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ListView;
import java.util.ArrayList;
import com.example.yanghanwen.taskmanagementmonster.Task;

public class SearchResultActivity extends AppCompatActivity {

    private ListView listView;
    private EditText editText;
    private Button button;
    private int tid;
    private String username;
    private String taskname;
    private String description;
    public ArrayList<Task> taskList = new ArrayList<>();
    public ArrayList<Task> allTaskList = new ArrayList<>();
    public ArrayAdapter<Task> adapter;
    ArrayList<Task> tasks = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) throws ArrayIndexOutOfBoundsException {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        listView = (ListView) findViewById(R.id.Search_result);
        editText = (EditText) findViewById(R.id.discover_search);
        button = (Button) findViewById(R.id.recover_button);
        String keyWord = editText.getText().toString();

        Task task_test = new Task();
        ElasticSearch.GetTask getTask = new ElasticSearch.GetTask();
        ElasticSearch.GetTasks getTasks = new ElasticSearch.GetTasks();
        ElasticSearch.IsExistTask isExistTask = new ElasticSearch.IsExistTask();

        //getTask.execute("nullm");


        /*try {
            task_test = getTask.get();
            Log.i("print something",task_test.getTaskname());

        } catch(ArrayIndexOutOfBoundsException e) {
                Log.d("test", "out of bound");
        } catch (Exception e) {
            Log.i("Error", "Fail to connect to server" + e);
        }*/

        String qUsername = "";

        getTasks.execute(qUsername);


        try {
            tasks = getTasks.get();
            Log.i("getting something new", tasks.toString());
        } catch(Exception e) {
            Log.d("test!!!!!!!!!!", "something wrong");
        }

        initList();

        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);

        /**
         * Prevent keyboard from popping up
         */
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    TaskSearch();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.clear();
                for(int j = 0; j < allTaskList.size(); j++) {
                    taskList.add(allTaskList.get(j));
                }
                adapter.notifyDataSetChanged();
            }
        });

        /*setResult(RESULT_OK);
        final int getIndex = intent.getIntExtra("index", 0);
        Log.d("test", "getting index: " + getIndex);
        Task task_add = taskList.get(getIndex);
        taskList.add(task_add);
        adapter.notifyDataSetChanged();*/  //basic method
    }

    public void TaskSearch() {
        String keyWord = editText.getText().toString();
        for(int i = 0; i < taskList.size(); i++) {
            if(!(taskList.get(i).getUsername().toLowerCase().contains(keyWord.toLowerCase()))) {
                taskList.remove(taskList.get(i));
            }
        }
        adapter.notifyDataSetChanged();
    }

    /*@Override  //method of searchView, detail fixing needed
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem item = menu.findItem(R.id.Search_result);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }*/

    public void initList() {

        /*Task task = new Task(tid, username, taskname, description);
        Task task1 = new Task(tid, username, taskname, description);
        Task task2 = new Task(tid, username, taskname, description);
        Task task3 = new Task(tid, username, taskname, description);
        Task task4 = new Task(tid, username, taskname, description);
        Task task5 = new Task(tid, username, taskname, description);
        Task task6 = new Task(tid, username, taskname, description);
        Task task7 = new Task(tid, username, taskname, description);
        Task task8 = new Task(tid, username, taskname, description);

        task.setTaskname("ebay");
        task.setStatus("Bidded");
        task.setDescription("This task has been bidded");
        task1.setTaskname("uber");
        task1.setStatus("Requested");
        task1.setDescription("This task has been requested");
        task2.setTaskname("taobao");
        task2.setStatus("Assigned");
        task2.setDescription("This task has been assigned");
        task3.setTaskname("KFC");
        task3.setStatus("Bidded");
        task3.setDescription("This task has been bidded");
        task4.setTaskname("McDonald");
        task4.setStatus("Bidded");
        task4.setDescription("This task has been bidded");
        task5.setTaskname("Panda");
        task5.setStatus("Done");
        task5.setDescription("This task has been done");
        task6.setTaskname("Chicken");
        task6.setStatus("Assigned");
        task6.setDescription("This task has been assigned");
        task7.setTaskname("noodle");
        task7.setStatus("Bidded");
        task7.setDescription("This task has been bidded");
        task8.setTaskname("rice");
        task8.setStatus("Requested");
        task8.setDescription("This task has been requested");

        taskList.add(task);
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        taskList.add(task4);
        taskList.add(task5);
        taskList.add(task6);
        taskList.add(task7);
        taskList.add(task8);*/

        for(int k = 0; k < tasks.size(); k++) {
            taskList.add(tasks.get(k));
        }

        for(int i = 0; i < taskList.size(); i++) {
            allTaskList.add(taskList.get(i));
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, taskList);
        listView.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.item1:
                int k = 0;
                while(k < taskList.size()) {
                    if(!taskList.get(k).getStatus().equals("bidded")) {
                        taskList.remove(taskList.get(k));
                        k = -1;
                    }
                    adapter.notifyDataSetChanged();
                    k++;
                }
                break;

            case R.id.item2:
                int a = 0;
                while(a < taskList.size()) {
                    if(!taskList.get(a).getStatus().equals("requested")) {
                        taskList.remove(taskList.get(a));
                        a = -1;
                    }
                    a++;
                }
                adapter.notifyDataSetChanged();
                break;

            case R.id.item3:
                int b = 0;
                while(b < taskList.size()) {
                    if(!taskList.get(b).getStatus().equals("assigned")) {
                        taskList.remove(taskList.get(b));
                        b = -1;
                    }
                    b++;
                }
                adapter.notifyDataSetChanged();
                break;

            case R.id.item4:
                int c = 0;
                while(c < taskList.size()) {
                    if(!taskList.get(c).getStatus().equals("done")) {
                        taskList.remove(taskList.get(c));
                        c = -1;
                    }
                    c++;
                }
                adapter.notifyDataSetChanged();
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
