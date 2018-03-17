package com.example.yanghanwen.taskmanagementmonster;

/**
 * Created by superfan1995 on 2018-03-16.
 */

import java.util.ArrayList;

public class UserList {

    private ArrayList<User> users = new ArrayList<>();

    public UserList(){

    }

    public void addUser(User user){
        users.add(user);
    }

    public boolean hasUser(User user){
        return users.contains(user);
    }

    public User getUser(User user_arg){
        for (User user : users) {
            if (user.equals(user_arg))
                return user_arg;
        }
        return null;
    }

    public void deleteUser(User user){
        users.remove(user);
    }

    public int countUser(){
        return users.size();
    }

    public boolean existedUser(User user_arg){
        for (User user:users){
            if (user.getUserName().equals(user_arg.getUserName())){
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }
}
