package com.example.yanghanwen.taskmanagementmonster;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLng;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Created by superfan1995 on 2018-03-09.
 */

/**
 * MVC model for the create task activity, compute and return required data.
 *
 * @version 1.0
 */
public class NewTaskModel {

    // this is not good, will be change in the future

    private ElasticSearch.IsExistTask isExistTask;  // elastic search used to check if task exist
    private ElasticSearch.AddTask addTask;          // elastic search used to add task

    private final String username;                  // the username of the task creator

    private ArrayList<String> imagesBase64;

    /**
     * Construct a model instance for the NewTaskActivity.
     *
     * get the current user's username, as well as creating the necessary elastic search
     * object to add the task to the database.
     */
    public NewTaskModel () {

        this.username = MainActivity.mainModel.getUsername();

        this.isExistTask = new ElasticSearch.IsExistTask();
        this.addTask = new ElasticSearch.AddTask();

        this.imagesBase64 = new ArrayList<String>();
    }

    /**
     * Create a new task by input a taskname string and a description string
     *
     * @param taskname the task name of new task
     * @param description the description of new task
     */
    public void createNewTask (String taskname, String description, LatLng coordinate) {

        /*if (existTask(taskname)) {

            Log.d("message", "task already exist");

            // if the task already exist, don't create it
        }*/
        //else {

        Task task = new Task(this.username, taskname, description, coordinate);
        task.setImagesBase64(imagesBase64);
        TaskList.getInstance().getTasks().add(task);
        try{
            Log.d("now the 2nd task is ","Value" + TaskList.getInstance().getTasks().get(1));
        }catch (Exception e){
            Log.d("the 1st task is ","Value" + TaskList.getInstance().getTasks().get(0).getCoordinate());
            Log.d("the 1st task image","Value"+TaskList.getInstance().getTasks().get(0).getImagesBase64());
        }

        //ElasticSearch.AddTask addTask = new ElasticSearch.AddTask();
        //addTask.execute(task);

        Log.d("message", "task successfully created");

            // create new task
        //}

    }

    /**
     * Check weather or not a task already exist
     *
     * @param taskname the taskname of the task
     * @return boolean true or false
     */
    public boolean existTask (String taskname) {

        isExistTask.execute(this.username + taskname);

        try {
            if (isExistTask.get()) {
                return Boolean.TRUE;
            }
            else {
                return Boolean.FALSE;
            }
        }
        catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    public ArrayList<String> getImageMessages () {

        int i = this.imagesBase64.size();
        int j = 0;

        ArrayList<String> messages = new ArrayList<String>();

        while (j < i) {

            messages.add("Images " + (j + 1));

            j = j + 1;
        }

        return messages;
    }

    public void deleteAllImages() {

        this.imagesBase64 = new ArrayList<String>();
    }

    public void addImage(Bitmap imageMap) {

        byte[] imageByteArray = BitmapToByteArray(imageMap);
        String imageBase64 = byteArrayToBase64(imageByteArray);

        this.imagesBase64.add(imageBase64);
    }

    public Bitmap getImage(int position) {

        String imageBase64 = this.imagesBase64.get(position);

        byte[] imageByteArray = Base64ToByteArray(imageBase64);
        Bitmap imageMap = byteArrayToBitmap(imageByteArray);

        return imageMap;
    }

    public void deleteImage(int position) {

        this.imagesBase64.remove(position);
    }

    private byte[] BitmapToByteArray(Bitmap imageMap) {

        // https://stackoverflow.com/questions/13758560/android-bitmap-to-byte-array-and-back-skimagedecoderfactory-returned-null
        // 2018-4-3
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        imageMap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        return byteArray;
    }

    private String byteArrayToBase64(byte[] imageByteArray) {

        // https://stackoverflow.com/questions/13562429/how-many-ways-to-convert-bitmap-to-string-and-vice-versa
        // 2018-4-4
        String imageBase64 = Base64.encodeToString(imageByteArray, Base64.NO_WRAP);

        return imageBase64;
    }

    private Bitmap byteArrayToBitmap(byte[] imageByteArray) {

        // https://stackoverflow.com/questions/13562429/how-many-ways-to-convert-bitmap-to-string-and-vice-versa
        // 2018-4-4
        Bitmap imagemap = BitmapFactory.decodeByteArray(imageByteArray, 0,
                imageByteArray.length);

        return imagemap;
    }

    private byte[] Base64ToByteArray(String imageBase64) {

        // https://stackoverflow.com/questions/13562429/how-many-ways-to-convert-bitmap-to-string-and-vice-versa
        // 2018-4-4
        byte[] imageByteArray = Base64.decode(imageBase64, Base64.DEFAULT);

        return imageByteArray;
    }

}
