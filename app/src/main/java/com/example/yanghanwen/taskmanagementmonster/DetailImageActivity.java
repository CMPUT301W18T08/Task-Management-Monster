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

public class DetailImageActivity extends AppCompatActivity {

    private ImageView imageView;

    private Button selectButton;
    private Button storeButton;
    private Button deleteButton;

    private int mode;

    private static final int PICK_IMAGE = 100;

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

        mode = extras.getInt("mode");

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

                storeBitmap();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deleteBitmap();
            }
        });

    }

    // https://www.youtube.com/watch?v=OPnusBmMQTw
    // 2018/03/26
    private void openGallery() {

        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    private void initialize () {

        if (mode == 0) {

            if (DetailTaskActivity.detailTaskModel.hasImage()) {

                // here we get bitmap image

                // end of have image map
            }

            selectButton.setVisibility(View.GONE);
            storeButton.setVisibility(View.GONE);
            deleteButton.setVisibility(View.GONE);
        }

        else if (mode == 1) {

            if (NewTaskActivity.newTaskModel.hasBitmap() ) {

                imageMap = NewTaskActivity.newTaskModel.getBitmap();
                imageView.setImageBitmap(imageMap);
            }

            else {

                storeButton.setVisibility(View.GONE);
                deleteButton.setVisibility(View.GONE);
            }
        }

        else if (mode == 2) {

            if (DetailTaskActivity.detailTaskModel.hasImage()) {

                // here we get bitmap image

                // end of have image map
            }

            else {

                storeButton.setVisibility(View.GONE);
                deleteButton.setVisibility(View.GONE);
            }
        }
    }

    private void storeBitmap() {

        if (imageMap == null) {

            Toast.makeText(getApplicationContext(),
                    "Error: image not found",
                    Toast.LENGTH_SHORT).show();
        }

        else if (mode == 1) {

            NewTaskActivity.newTaskModel.setBitmap(imageMap);
            finish();
        }

        else if (mode == 2) {

            // store the new image to the task

            finish();
        }
    }

    private void deleteBitmap() {

        if (mode == 1) {

            NewTaskActivity.newTaskModel.deleteBitmap();
            finish();
        }

        else if (mode == 2) {

            DetailTaskActivity.detailTaskModel.deleteImage();
            finish();
        }
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

                    imageView.setImageBitmap(imageMap);

                    storeButton.setVisibility(View.VISIBLE);
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
