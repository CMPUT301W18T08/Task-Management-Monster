package com.example.yanghanwen.taskmanagementmonster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static User user;

    // test
    public static Task mockTest;
    public static Task mockTest2;

    private Button testNew;
    private Button testRe;
    private TextView testView1;
    private TextView testView2;
    private TextView testView3;
    private TextView testView4;
    private TextView testView5;
    // test

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // for test only

        user = new User("Jack", "j@ualberta.ca", "111 222 3333");

        String username = "Jack";
        String taskname = "Cleaning";
        String Description = "Do Cleaning";

        mockTest = new Task(username, taskname, Description);

        Double amount = 17.00;

        mockTest.createNewBid(user.getUserName(), amount);
        mockTest.createNewBid("mock1", 15.00);
        mockTest.createNewBid("mock2", 20.00);

        //mockTest.setAssigned(user.getUserName());

        testNew = (Button) findViewById(R.id.buttonTestNes);
        testRe = (Button) findViewById(R.id.buttonTestRe);

        testView1 = (TextView) findViewById(R.id.textViewMainTest1);
        testView2 = (TextView) findViewById(R.id.textViewMainTest2);
        testView3 = (TextView) findViewById(R.id.textViewMainTest3);
        testView4 = (TextView) findViewById(R.id.textViewMainTest4);
        testView5 = (TextView) findViewById(R.id.textViewMainTest5);

        testNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // new test test
                // Intent intent = new Intent(MainActivity.this, NewTaskActivity.class);
                // startActivity(intent);

                Intent intent = new Intent(MainActivity.this, DetailTaskActivity.class);

                intent.putExtra("mode", 3);
                intent.putExtra("title", mockTest.getTaskname());
                intent.putExtra("requester", mockTest.getUsername());

                startActivity(intent);

            }
        });
        // for test only

        testRe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                testView1.setText(mockTest.getTaskname());
                testView2.setText(mockTest.getUsername());
                testView3.setText(mockTest.getStatus());
                testView4.setText(mockTest.getDescription());

                if (mockTest.getUserAmount(user.getUserName()) != null ) {
                    testView5.setText(mockTest.getUserAmount(user.getUserName()).toString());
                }

                else {

                    testView5.setText("No bid");
                }
            }
        });

    }
}
