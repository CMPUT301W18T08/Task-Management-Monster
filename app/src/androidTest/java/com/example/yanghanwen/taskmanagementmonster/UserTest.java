package com.example.yanghanwen.taskmanagementmonster;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by yanghanwen on 2018-02-26.
 */

public class UserTest extends ActivityInstrumentationTestCase2 {
    public UserTest () {
        super(MainActivity.class);
    }

    public void testUsernameGet() {
        String username = "cmput301";
//        int uid = 123;
        String email = "cmput301@ualberta.ca";
        String pno = "123456789";
//        String pwd = "cmput301winter18";

        User user =  new User(username, email, pno);

        assertTrue(user.getUserName() == username);
    }

    public void testEmailGet() {
        String username = "cmput301";
//        int uid = 123;
        String email = "cmput301@ualberta.ca";
        String pno = "123456789";
//        String pwd = "cmput301winter18";

        User user =  new User(username, email, pno);

        assertTrue(user.getEmail() == email);

    }

    public void testPhoneNumGet() {
        String username = "cmput301";
//        int uid = 123;
        String email = "cmput301@ualberta.ca";
        String pno = "123456789";
//        String pwd = "cmput301winter18";

        User user =  new User(username, email, pno);

        assertTrue(user.getPhoneNum() == pno);
    }

    public void testUsernameSet() {
        String username = "cmput301";
        String username1 = "winter2018";
//        int uid = 123;
        String email = "cmput301@ualberta.ca";
        String pno = "123456789";
//        String pwd = "cmput301winter18";

        User user =  new User(username, email, pno);
        user.setUserName(username1);

        assertTrue(user.getUserName() == username1);

    }

    public void testEmailSet() {
        String username = "cmput301";
//        int uid = 123;
        String email = "cmput301@ualberta.ca";
        String email1 = "winter2018@123.com";
        String pno = "123456789";
//        String pwd = "cmput301winter18";

        User user =  new User(username, email, pno);
        user.setEmail(email1);

        assertTrue(user.getEmail() == email1);
    }

    public void testPhoneNumSet() {
        String username = "cmput301";
        String username1 = "winter2018";
//        int uid = 123;
        String email = "cmput301@ualberta.ca";
        String pno = "123456789";
        String pno1 = "4567890";
//        String pwd = "cmput301winter18";

        User user =  new User(username, email, pno);
        user.setPhoneNum(pno1);

        assertTrue(user.getPhoneNum() == pno1);

    }
}