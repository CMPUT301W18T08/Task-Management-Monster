package com.example.yanghanwen.taskmanagementmonster;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class DetailImageActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button selectButton;

    private static final int PICK_IMAGE = 100;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_image);

        imageView = (ImageView) findViewById(R.id.detialImageView);
        selectButton = (Button) findViewById(R.id.imageSelectButton);

        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openGallery();
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
                imageView.setImageURI(imageUri);

                // upload the image

                // upload image end
            }
        }
    }

}
