package com.example.yanghanwen.taskmanagementmonster;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * SearchResultActivity is used for searching a
 * task (or tasks) as a role of provider, it will provide
 * all the tasks and provider is able to either filter tasks to
 * whatever he want through four different status, and can also
 * search a task(s) by entering keywords of username.
 *
 * layout: activity_search_result.xml
 *
 * @version 1.0
 */
public class SearchActivity extends AppCompatActivity {

    private ListView listView;
    private EditText editText;
    private Button button;
    private FloatingActionButton discover;

    private long firstPressed;

    public ArrayList<Task> taskList = new ArrayList<>();
    public ArrayList<Task> allTaskList = new ArrayList<>();
    public ArrayAdapter<Task> adapter;
    ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Firstly executed when code starts going
     * mainly used for getting all tasks that are
     * available in elasticsearch server
     *
     * @param savedInstanceState
     * @throws ArrayIndexOutOfBoundsException
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) throws ArrayIndexOutOfBoundsException {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ActionBar bar = getSupportActionBar();

        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#E47833")));


        listView = (ListView) findViewById(R.id.Search_result);
        editText = (EditText) findViewById(R.id.discover_search);
        button = (Button) findViewById(R.id.recover_button);
        String keyWord = editText.getText().toString();

        Task task_test = new Task();
        ElasticSearch.GetTask getTask = new ElasticSearch.GetTask();
        ElasticSearch.GetTasks getTasks = new ElasticSearch.GetTasks();
        ElasticSearch.IsExistTask isExistTask = new ElasticSearch.IsExistTask();

        //getting tasks from elasticsearch server
        String qUsername = "";

        getTasks.execute(qUsername);


        try {
            tasks = getTasks.get();
            Log.i("getting something new", tasks.toString());
        } catch(Exception e) {
            Log.d("test!!!!!!!!!!", "something wrong");
        }

        //hiding keyboard from popping up
        //which could be annoying
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

            /**
             * Constructing a edittext listener
             * to track changes of text, when user
             * starts to enter keyword, the program
             * starts to search
             *
             *
             * @param charSequence
             * @param i
             * @param i1
             * @param i2
             */
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                TaskSearch();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                TaskSearch();
            }

            /**
             * after text changes do searching again
             * to make it faster
             *
             *
             * @param editable
             */

            @Override
            public void afterTextChanged(Editable editable) {
                TaskSearch();

            }
        });

        button.setOnClickListener(new View.OnClickListener() {

            /**
             * setting recover all button action
             * when button is clicked, recover all elements
             * that had been removed to let user do multiple
             * times of search
             *
             *
             * @param view
             */
            @Override
            public void onClick(View view) {
                adapter.clear();
                for(int j = 0; j < allTaskList.size(); j++) {
                    taskList.add(allTaskList.get(j));
                }
                adapter.notifyDataSetChanged();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Task task = taskList.get(i);
                String title = task.getTaskname();
                String requester = task.getUsername();

                Intent intent = new Intent(SearchActivity.this,
                        DetailTaskActivity.class);

                intent.putExtra("mode", 1);
                intent.putExtra("title", title);
                intent.putExtra("requester", requester);

                startActivity(intent);
            }
        });

        discover = (FloatingActionButton) findViewById(R.id.discoverButton);

        discover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTaskCoordinates();
            }
        });
    }

    /**
     * passing an arrayList of coordinates of existing tasks
     */
    public void getTaskCoordinates() {

        ArrayList<LatLng> coor = new ArrayList<>();
        ArrayList<String> taskName = new ArrayList<>();
        ArrayList<String> status = new ArrayList<>();

        for (int i = 0; i < taskList.size(); i++) {

            if (taskList.get(i).getCoordinate() != null) {
                coor.add(taskList.get(i).getCoordinate());
                taskName.add(taskList.get(i).getTaskname());
                status.add(taskList.get(i).getStatus());
            } else {
                continue;
            }
        }
        Intent intent = new Intent(SearchActivity.this, SearchScreenLocationActivity.class);
        intent.putParcelableArrayListExtra("coordinates", coor);
        intent.putStringArrayListExtra("taskname", taskName);
        intent.putStringArrayListExtra("status", status);
        startActivity(intent);
    }

    /**
     * setting backbutton action:
     * press once to recover all elements that had been removed(same functionality as recover button)
     * do a double press within 3000 milliseconds to return to the last activity
     */
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        adapter.clear();
        for(int j = 0; j < allTaskList.size(); j++) {
            taskList.add(allTaskList.get(j));
        }
        adapter.notifyDataSetChanged();

        if(System.currentTimeMillis() - firstPressed < 3000) {
            super.onBackPressed();
        } else {
            Toast.makeText(SearchActivity.this, "Press again to quit", Toast.LENGTH_SHORT).show();
            firstPressed = System.currentTimeMillis();
        }
    }

    /**
     * searching task action: remove all
     * tasks that are unsatisfied
     */
    public void TaskSearch() {
        String keyWord = editText.getText().toString();
        for(int i = 0; i < taskList.size(); i++) {
            if(!(taskList.get(i).getUsername().toLowerCase().contains(keyWord.toLowerCase()))) {
                taskList.remove(taskList.get(i));
            }
        }
        adapter.notifyDataSetChanged();
    }

    /**
     * initialize taskList, setting adapter, adding all tasks to listview
     * meanwhile add all same tasks to allTaskList for
     * recovering purpose
     */
    public void initList() {

        for(int k = 0; k < tasks.size(); k++) {
            taskList.add(tasks.get(k));
        }

        for(int i = 0; i < taskList.size(); i++) {
            allTaskList.add(taskList.get(i));
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, taskList);
        listView.setAdapter(adapter);
    }

    /**
     * Initializing filter menu
     *
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        return true;
    }

    /**
     * Filter action, is able to filter in four different ways:
     * bidded, assigned, requested or done
     *
     *
     * @param item
     * @return
     */
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
