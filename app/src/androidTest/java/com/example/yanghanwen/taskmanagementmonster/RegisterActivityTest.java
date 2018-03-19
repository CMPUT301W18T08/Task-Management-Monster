package com.example.yanghanwen.taskmanagementmonster;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.EditText;


import com.robotium.solo.Solo;

import org.junit.Test;

/**
 * Created by Terrence on 2018/3/18.
 */

public class RegisterActivityTest extends ActivityInstrumentationTestCase2<RegisterActivity> {
    private Solo solo;
    private String testName;

    public RegisterActivityTest(){
        super(RegisterActivity.class);
    }


    public void setUp() throws Exception{
        solo = new Solo(getInstrumentation(),getActivity());
    }

    public void testStart()throws Exception{
        AppCompatActivity activity = getActivity();
    }


    public void testRegister(){

        RegisterActivity registerActivity = (RegisterActivity) solo.getCurrentActivity();
        solo.assertCurrentActivity("Wrong Activity",RegisterActivity.class);

        testName = "test";
        solo.enterText((EditText) solo.getView(R.id.name),testName);
        solo.clickOnButton("Save");

        SystemClock.sleep(5000);

        ElasticSearch.IsExist isExist = new ElasticSearch.IsExist();
        isExist.execute(testName);

        try{
            assertTrue(isExist.get());
        }catch (Exception e){
            Log.i("Error", "Fail to register");
        }
    }

}