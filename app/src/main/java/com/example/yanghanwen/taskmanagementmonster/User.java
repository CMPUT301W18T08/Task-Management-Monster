package com.example.yanghanwen.taskmanagementmonster;

/**
 Created by yangwenhan on 2018/2/21.
 */

/**
 * Object class of user
 *
 * Contain the information of the current user.
 *
 * @version 1.0
 */

public class User {

    private String userName;    // username of user
    private String email;       // email of user
    private String phoneNum;    // phoneNum of user

    /**
     * Create an empty user object.
     *
     * This constructor is used for elastic search.
     */
    public User () {

    }

    /**
     * Create a user object by using the input
     *
     * @param name username of user
     * @param email the email of user
     * @param phoneNum the phoneNum of user
     */
    public User(String name, String email, String phoneNum) {
        this.userName = name;
        this.email = email;
        this.phoneNum = phoneNum;
    }

    /**
     * Return the username of user
     *
     * @return the username of user
     */
    public String getUserName() {

        return this.userName;
    }

    /**
     * Return the email of user
     *
     * @return the email of user
     */
    public String getEmail() {

        return email;
    }

    /**
     * Set the email of user to a new input value
     *
     * @param email the new email of the user
     */
    public void setEmail(String email) {

        this.email = email;
    }

    /**
     * Return the phone number of the user
     *
     * @return the phone number of the user
     */
    public String getPhoneNum() {
        return this.phoneNum;
    }

    /**
     * Set the Phone number of user to a new input value
     *
     * @param phoneNum the new phone number of the user
     */
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
