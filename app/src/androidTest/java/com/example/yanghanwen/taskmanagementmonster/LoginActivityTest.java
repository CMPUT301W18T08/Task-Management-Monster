package com.example.yanghanwen.taskmanagementmonster;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.robotium.solo.Solo;

import java.util.List;

/**
 * Created by songxixuan on 2018-03-18.
 */

public class LoginActivityTest extends ActivityInstrumentationTestCase2<LoginActivity> {
    private Solo solo;

    public LoginActivityTest() {
        super(LoginActivity.class);
    }

    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
    }

    public void testStart() throws Exception {
        Activity activity = getActivity();
    }

    public void testLogin() {
        solo.assertCurrentActivity("Wrong Activity", LoginActivity.class);
        solo.enterText((EditText) solo.getView(R.id.id), "test");
        solo.clickOnButton("login");

        solo.assertCurrentActivity("new activity", MainActivity.class);
        solo.clickOnButton("newTaskButton");
        solo.assertCurrentActivity("new activity", NewTaskActivity.class);
        solo.enterText((EditText) solo.getView(R.id.editTextNewTitle), "Hello Word");
        assertTrue(solo.waitForText("Hello World"));
        solo.enterText((EditText) solo.getView(R.id.editTextNewTitle), "Hello Word");
        assertTrue(solo.waitForText("Hello World"));
        solo.enterText((EditText) solo.getView(R.id.editTextNewDescription), "test");
        assertTrue(solo.waitForText("test"));
        solo.clickOnButton("CREATE");
        solo.assertCurrentActivity("new activity", MainActivity.class);
        solo.clickOnButton("REQUESTER MY TASK");
        solo.assertCurrentActivity("new activity", MyTaskActivity.class);
        ListView list1 = (ListView) solo.getView(R.id.RequesterTask);
        View view1 = list1.getChildAt(0);
        solo.clickOnView(view1);

    }
}
