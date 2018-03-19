package com.example.yanghanwen.taskmanagementmonster;

import android.support.v7.app.AppCompatActivity;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.robotium.solo.Solo;

/**
 * Created by Terrence on 2018/3/18.
 */

/**
 * test from login
 * go to add new task
 * then view my requesting task
 * then go to see the detail
 * then go back
 * go to see my provide task
 * then go to see the detail
 */
public class LoginActivityTest extends ActivityInstrumentationTestCase2<LoginActivity> {
    private Solo solo;
    private String testName;
    private String email;
    private String phoNum;

    public LoginActivityTest(){super(LoginActivity.class);}

    public void setUp()throws Exception{
        solo = new Solo(getInstrumentation(),getActivity());
    }

    public void testStart()throws Exception{
        AppCompatActivity activity = getActivity();
    }


    /**
     * test login
     * test add new task
     * test view my requesting task
     * test click list view item and see the detail
     */
    public void testLogin(){
        LoginActivity activity = (LoginActivity)solo.getCurrentActivity();
        solo.assertCurrentActivity("Wrong Activity",LoginActivity.class);
        testName = "test";
        solo.enterText((EditText)solo.getView(R.id.id),testName);
        solo.clickOnButton("Login");
        solo.assertCurrentActivity("Wrong Activity",MainActivity.class);

        // keep going
        // Create new task
        solo.clickOnButton("Create New Task");
        solo.assertCurrentActivity("Wrong Activity",NewTaskActivity.class);

        solo.enterText((EditText)solo.getView(R.id.editTextNewTitle),"testTask");
        assertTrue(solo.waitForText("testTask"));
        solo.enterText((EditText)solo.getView(R.id.editTextNewDescription),"testTask");
        assertTrue(solo.waitForText("testTask"));
        solo.clickOnButton("Create");
        solo.assertCurrentActivity("Wrong Activity",MainActivity.class);

        // go to see my request task
        solo.clickOnButton("Requester My Task");
        solo.assertCurrentActivity("Wrong Activity",MyTaskActivity.class);


        // click one item in list view, go to detail task activity.
        ListView mlist = (ListView)solo.getView(R.id.RequesterTask);
        View mView = mlist.getChildAt(0);
        solo.clickOnView(mView);
        solo.assertCurrentActivity("Wrong Activity",DetailTaskActivity.class);
        solo.goBack();
        solo.goBack();
        solo.assertCurrentActivity("Wrong Activity",MainActivity.class);

        solo.clickOnButton("Search New Task");
        solo.assertCurrentActivity("WrongActivity",SearchActivity.class);
        ListView listView = (ListView)solo.getView(R.id.Search_result);
        Task task = (Task)listView.getItemAtPosition(0);
        String taskname = task.getTaskname();
        solo.clickInList(0);
        solo.assertCurrentActivity("Wrong Activity",DetailTaskActivity.class);
        assertTrue(solo.waitForText(taskname,1,3000));
        solo.goBack();
        solo.assertCurrentActivity("Wrong Activity",SearchActivity.class);

    }

    public void testRegister(){
        solo.clickOnButton("Register");
        solo.assertCurrentActivity("Wrong Activity",RegisterActivity.class);
    }

    public void testSearch(){
        LoginActivity activity = (LoginActivity)solo.getCurrentActivity();
        solo.assertCurrentActivity("Wrong Activity",LoginActivity.class);
        testName = "test";
        solo.enterText((EditText)solo.getView(R.id.id),testName);
        solo.clickOnButton("Login");
        solo.assertCurrentActivity("Wrong Activity",MainActivity.class);

        solo.clickOnButton("Search New Task");
        solo.assertCurrentActivity("WrongActivity",SearchActivity.class);

        // for now only list all tasks,click one item in the list view
        ListView mlist = (ListView)solo.getView(R.id.Search_result);
        View mView = mlist.getChildAt(0);
        solo.clickOnView(mView);
        solo.assertCurrentActivity("Wrong Activity",DetailTaskActivity.class);

    }

    public void testModifyProfile(){
        LoginActivity activity = (LoginActivity)solo.getCurrentActivity();
        solo.assertCurrentActivity("Wrong Activity",LoginActivity.class);
        testName = "test";
        solo.enterText((EditText)solo.getView(R.id.id),testName);
        solo.clickOnButton("Login");
        solo.assertCurrentActivity("Wrong Activity",MainActivity.class);

        solo.clickOnButton("My Profile");
        solo.assertCurrentActivity("WrongActivity",EditprofileActivity.class);

        email = "test@gmail.com";
        phoNum = "7801111111";

        solo.enterText((EditText)solo.getView(R.id.profileEmail),email);
        solo.enterText((EditText)solo.getView(R.id.profilePhoneNum),phoNum);

        solo.clickOnButton("Finish");
        solo.assertCurrentActivity("WrongActivity",MainActivity.class);
    }


    /* public void testDataPass(){
         LoginActivity activity = (LoginActivity)solo.getCurrentActivity();
         solo.assertCurrentActivity("Wrong Activity",LoginActivity.class);
         testName = "test";
         solo.enterText((EditText)solo.getView(R.id.id),testName);
         solo.clickOnButton("Login");
         solo.assertCurrentActivity("Wrong Activity",MainActivity.class);
         solo.clickOnButton("Search New Task");
         solo.assertCurrentActivity("WrongActivity",SearchActivity.class);
         ListView listView = (ListView)solo.getView(R.id.Search_result);
         Task task = (Task)listView.getItemAtPosition(0);
         String taskname = task.getTaskname();
         solo.clickInList(0);
         solo.assertCurrentActivity("Wrong Activity",DetailTaskActivity.class);
         assertTrue(solo.waitForText(taskname,1,3000));
         solo.goBack();
         solo.assertCurrentActivity("Wrong Activity",SearchActivity.class);
     }*/
    @Override
    public void tearDown()throws Exception{

        solo.finishOpenedActivities();

    }

}