package com.example.yanghanwen.taskmanagementmonster;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;

public class DetailImageActivity extends AppCompatActivity {

    /*
    private Bitmap imageMap;
    */

    private String mode;
    private int position;

    private static final int PICK_IMAGE = 100;

    private Button selectButton;
    private Button storeButton;
    private Button deleteButton;

    private ImageView imageView;

    private Bitmap imageMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_image);

        Bundle extras = getIntent().getExtras();

        if (extras == null) {

            Toast.makeText(getApplicationContext(),
                    "Error: receive no input",
                    Toast.LENGTH_SHORT).show();

            finish();
        }

        mode = extras.getString("mode");
        position = extras.getInt("position");

        imageView = (ImageView) findViewById(R.id.detialImageView);

        selectButton = (Button) findViewById(R.id.imageSelectButton);
        storeButton = (Button) findViewById(R.id.imageStoreButton);
        deleteButton = (Button) findViewById(R.id.imageDeleteButton);

        initialize();

        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openGallery();
            }
        });

        storeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                storeImage();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deleteImage();
            }
        });


    }

    private void initialize() {

        if (mode.equals("new")) {

            deleteButton.setVisibility(View.GONE);
            storeButton.setVisibility(View.GONE);
        }

        else if (mode.equals("newView")) {

            imageMap = NewTaskActivity.newTaskModel.getImage(position);
            imageView.setImageBitmap(imageMap);

            selectButton.setVisibility(View.GONE);
            storeButton.setVisibility(View.GONE);
        }

        else if (mode.equals("viewOnly")) {

            imageMap = DetailTaskActivity.detailTaskModel.getImage(position);
            imageView.setImageBitmap(imageMap);

            selectButton.setVisibility(View.GONE);
            deleteButton.setVisibility(View.GONE);
            storeButton.setVisibility(View.GONE);
        }

        else if (mode.equals("myTask")) {

            deleteButton.setVisibility(View.GONE);
            storeButton.setVisibility(View.GONE);
        }

        else if (mode.equals("myTaskView")) {

            imageMap = DetailTaskActivity.detailTaskModel.getImage(position);
            imageView.setImageBitmap(imageMap);

            selectButton.setVisibility(View.GONE);
            storeButton.setVisibility(View.GONE);
        }
    }

    private void storeImage() {

        if (imageMap == null) {

            Toast.makeText(getApplicationContext(),
                    "Error: image not found",
                    Toast.LENGTH_SHORT).show();
        }

        else if (mode.equals("new")) {

            NewTaskActivity.newTaskModel.addImage(imageMap);

            Intent returnIntent = new Intent();
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }

        else if (mode.equals("myTask")) {

            DetailTaskActivity.detailTaskModel.addImage(imageMap);

            Intent returnIntent = new Intent();
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }
    }

    private void deleteImage() {

        if (mode.equals("newView")) {

            NewTaskActivity.newTaskModel.deleteImage(position);

            Intent returnIntent = new Intent();
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }

        else if (mode.equals("myTaskView")) {

            DetailTaskActivity.detailTaskModel.deleteImage(position);

            Intent returnIntent = new Intent();
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }
    }

    // https://www.youtube.com/watch?v=OPnusBmMQTw
    // 2018/03/26
    private void openGallery() {

        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requesCode, int resultCode, Intent data) {

        super.onActivityResult(requesCode, resultCode, data);
        if (requesCode == PICK_IMAGE) {

            if (resultCode == RESULT_OK) {

                Uri imageUri = data.getData();

                // https://stackoverflow.com/questions/38352148/get-image-from-the-gallery-and-show-in-imageview
                // 2018-03-30
                try {

                    // this will also used to check byte count (but will wait until test data)
                    InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    imageMap = BitmapFactory.decodeStream(imageStream);

                    int bytecount = imageMap.getByteCount();

                    if (bytecount > 65536) {

                        Toast.makeText(getApplicationContext(),
                                "Error: image larger than 65536 byte.",
                                Toast.LENGTH_SHORT).show();
                    }

                    else {

                        imageView.setImageBitmap(imageMap);
                        storeButton.setVisibility(View.VISIBLE);
                    }
                }

                catch (Exception e) {

                    Toast.makeText(getApplicationContext(),
                            "Error: unable compile the image",
                            Toast.LENGTH_SHORT).show();
                }
            }
        }
    }



}
