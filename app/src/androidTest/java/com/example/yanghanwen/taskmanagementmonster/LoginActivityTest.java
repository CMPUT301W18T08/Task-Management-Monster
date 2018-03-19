package com.example.yanghanwen.taskmanagementmonster;

import android.support.v7.app.AppCompatActivity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import com.robotium.solo.Solo;

/**
 * Created by Terrence on 2018/3/18.
 */

public class LoginActivityTest extends ActivityInstrumentationTestCase2<LoginActivity> {
    private Solo solo;
    private String testName;

    public LoginActivityTest(){super(LoginActivity.class);}

    public void setUp()throws Exception{
        solo = new Solo(getInstrumentation(),getActivity());
    }

    public void testStart()throws Exception{
        AppCompatActivity activity = getActivity();
    }

    public void testLogin(){
        LoginActivity activity = (LoginActivity)solo.getCurrentActivity();
        solo.assertCurrentActivity("Wrong Activity",LoginActivity.class);
        testName = "test";
        solo.enterText((EditText)solo.getView(R.id.id),testName);
        solo.clickOnButton("Login");
        solo.assertCurrentActivity("Wrong Activity",MainActivity.class);

        // keep going
        solo.clickOnButton("Create New Task");
        solo.assertCurrentActivity("Wrong Activity",NewTaskActivity.class);

        solo.enterText((EditText)solo.getView(R.id.editTextNewTitle),"testTask");
        assertTrue(solo.waitForText("testTask"));
        solo.enterText((EditText)solo.getView(R.id.editTextNewDescription),"testTask");
        assertTrue(solo.waitForText("testTask"));
        solo.clickOnButton("Create");
        solo.assertCurrentActivity("Wrong Activity",MainActivity.class);

    }





    public void testRegister(){
        solo.clickOnButton("Register");
        solo.assertCurrentActivity("Wrong Activity",RegisterActivity.class);
    }

}