package com.example.yanghanwen.taskmanagementmonster;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class DetailImageActivity extends AppCompatActivity {

    private ImageView imageView;

    //private Button selectButton;

    private static final int PICK_IMAGE = 200;
    private int mode;

    private Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_image);

        Bundle extras = getIntent().getExtras();

        if (extras == null) {

            Log.d("error", "receive no input");
            // raise exception for error occured
            finish();
        }

        // mode currently three status: 0: view only, 1: add new, 2: already has image
        mode = extras.getInt("image");

        imageView = (ImageView) findViewById(R.id.detialImageView);

        Button selectButton = (Button) findViewById(R.id.imageSelectButton);
        final Button storeButton = (Button) findViewById(R.id.imageStoreButton);
        Button deleteButton = (Button) findViewById(R.id.imageDeleteButton);

        /*
        if (mode == 0) {

            storeButton.setVisibility(View.GONE);
            deleteButton.setVisibility(View.GONE);
        }
        */

        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openGallery();

                //storeButton.setVisibility(View.VISIBLE);
            }
        });

        storeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (imageUri == null) {

                    Toast.makeText(getApplicationContext(),
                            "Error: no image selected",
                            Toast.LENGTH_SHORT).show();
                }
                else {

                    Intent returnIntent = new Intent();
                    returnIntent.setData(imageUri);
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                }
            }
        });
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

                // show the chosen image from gallery
                imageUri = data.getData();

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),
                            imageUri);

                    int byteCount = bitmap.getByteCount();

                    if (byteCount > 65536) {

                        Toast.makeText(getApplicationContext(),
                                "Error: Image larger than 65536 byte",
                                Toast.LENGTH_SHORT).show();
                    }
                    else {

                        imageView.setImageURI(imageUri);
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
