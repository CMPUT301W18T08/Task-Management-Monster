package com.example.yanghanwen.taskmanagementmonster;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

/**
 * The activity control the main view of the app
 */
public class MainActivity extends AppCompatActivity {

    public static MainModel mainModel;  // model of this activity

    private FloatingActionButton newTaskButton;       // button to create new task
    private ImageButton searchButton;
    private ImageButton search_around;// button to search a task
    private Button providerButton;      // button to see my task as provider
    private Button requesterButton;     // button to see my task as requester
    private Button profileButton;       // button to see my profile
    private DrawerLayout mdrawerlayout;
    private long firstPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Intent intent = getIntent();

        String username = intent.getStringExtra("username");

        // create the model by given the username
        mainModel = new MainModel(username);

        newTaskButton = (FloatingActionButton) findViewById(R.id.newTaskButton);
        //searchButton = (ImageButton) findViewById(R.id.mainSearch);
        providerButton = (Button) findViewById(R.id.providerButton);
        requesterButton = (Button) findViewById(R.id.requesterButton);
        profileButton = (Button) findViewById(R.id.profileButton);

        mdrawerlayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        search_around = (ImageButton) findViewById(R.id.search_location);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(null);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);

        ActionBar actionbar = getSupportActionBar();

        if(actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeAsUpIndicator(R.drawable.icon_list_24dp);
        }


        newTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,
                        NewTaskActivity.class);

                startActivity(intent);
            }
        });


        search_around.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LocationServiceActivity.class);

                startActivity(intent);
            }
        });

        //TODO switch...case instead of click Listener

        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.profileButton:
                        Intent intent = new Intent(MainActivity.this,
                                EditprofileActivity.class);

                        startActivity(intent);
                        break;

                    case R.id.providerButton:
                        Intent intent1 = new Intent(MainActivity.this, MyTaskActivity.class);

                        // set the type as provider
                        intent1.putExtra("type", "pro");

                        startActivity(intent1);
                        break;

                    case R.id.requesterButton:
                        Intent intent2 = new Intent(MainActivity.this, MyTaskActivity.class);

                        // set the type as requester
                        intent2.putExtra("type", "req");

                        startActivity(intent2);
                        break;

                    default:
                }
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {

        if(System.currentTimeMillis() - firstPressed < 3000) {
            super.onBackPressed();//if do a double click within 3000 milliseconds, back to previous activity
        } else {
            Toast.makeText(MainActivity.this, "Press again to quit", Toast.LENGTH_SHORT).show();
            firstPressed = System.currentTimeMillis();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {

            case android.R.id.home:
                mdrawerlayout.openDrawer(GravityCompat.START);
                break;

            case R.id.search_item:

                Intent intent = new Intent(MainActivity.this, SearchActivity.class);

                startActivity(intent);
                break;

            default:
        }
        return true;
        }
    }

