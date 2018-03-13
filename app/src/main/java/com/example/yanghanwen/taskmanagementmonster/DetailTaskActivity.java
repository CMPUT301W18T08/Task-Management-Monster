package com.example.yanghanwen.taskmanagementmonster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DetailTaskActivity extends AppCompatActivity {

    int mode;

    private DetailTaskModel detailTaskModel;

    private TextView viewTitle;
    private TextView viewUsername;
    private TextView viewStatus;
    private TextView viewDescription;
    private TextView viewBidInfo;
    private TextView viewBidLowest;
    private TextView viewBidMyBidInfo;
    private TextView viewBidMyBid;

    private EditText editBid;
    private EditText editTitle;
    private EditText editDescription;

    private Button changeButton;
    private Button declineButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_task);

        Bundle extras = getIntent().getExtras();

        if (extras == null) {

            // raise exception for error occured
            finish();
        }

        // Retrive income data
        mode = extras.getInt("mode");

        String title = extras.getString("title");
        String requester = extras.getString("requester");

        if (mode == 1) {

            detailTaskModel = new DetailTaskSearchModel(title, requester);
        }

        else if (mode == 2) {

            detailTaskModel = new DetailTaskProviderModel(title, requester);
        }

        else if (mode == 3) {

            detailTaskModel =new DetailTaskRequestorModel(title, requester);
        }

        else {

            // raise exception for error occured
            finish();
        }

        viewTitle = (TextView) findViewById(R.id.viewDetailTitle);
        viewUsername = (TextView) findViewById(R.id.viewDetailUser);
        viewStatus = (TextView) findViewById(R.id.viewDetailStatus);
        viewDescription = (TextView) findViewById(R.id.viewDetailDescription);
        viewBidInfo = (TextView) findViewById(R.id.detailBidInformation);
        viewBidLowest = (TextView) findViewById(R.id.detailBidLowest);
        viewBidMyBidInfo = (TextView) findViewById(R.id.detailMyBid);
        viewBidMyBid = (TextView) findViewById(R.id.viewDetailMyBid);

        editBid = (EditText) findViewById(R.id.editTextDetail);
        editTitle = (EditText) findViewById(R.id.editTextDetailTitle);
        editDescription = (EditText) findViewById(R.id.editTextDetailDescription);

        changeButton = (Button) findViewById(R.id.buttonDetail);
        declineButton = (Button) findViewById(R.id.buttonDetail2);

        updateView();

        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mode == 1) {

                    String textBid = editBid.getText().toString();
                    detailTaskModel.changeButtonAction(textBid);

                    updateView();
                }

                else if (mode == 2) {

                    String textBid = editBid.getText().toString();
                    detailTaskModel.changeButtonAction(textBid);

                    updateView();
                }

                else if (mode == 3) {

                    String textTitle = editTitle.getText().toString();
                    detailTaskModel.changeButtonAction(textTitle);

                    updateView();
                }

            }
        });

        declineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mode == 1) {

                    detailTaskModel.declineButtonAction("");

                    updateView();
                }

                else if (mode == 2) {

                    detailTaskModel.declineButtonAction("");

                    finish();
                }

                else if (mode == 3) {

                    String textDescription = editDescription.getText().toString();
                    detailTaskModel.declineButtonAction(textDescription);

                    updateView();
                }
            }
        });

    }

    private void updateView() {

        viewTitle.setText( detailTaskModel.getTitle() );
        viewUsername.setText( detailTaskModel.getRequester() );
        viewStatus.setText( detailTaskModel.getStatus() );
        viewDescription.setText( detailTaskModel.getDescrption() );

        viewBidInfo.setText( detailTaskModel.getBidInfo() );
        viewBidInfo.setVisibility( detailTaskModel.visibilityBidInfo() );

        viewBidLowest.setText( detailTaskModel.getBidLowest() );
        viewBidLowest.setVisibility( detailTaskModel.visibilityBidLowest() );

        viewBidMyBidInfo.setText( detailTaskModel.getMyBidInfo() );
        viewBidMyBidInfo.setVisibility( detailTaskModel.visibilityMyBidInfo() );

        viewBidMyBid.setText( detailTaskModel.getMyBid() );
        viewBidMyBid.setVisibility( detailTaskModel.visibilityMyBid() );

        editBid.setVisibility( detailTaskModel.visibilityEdit() );
        editTitle.setVisibility( detailTaskModel.visibilityEditTitle() );
        editDescription.setVisibility( detailTaskModel.visibilityEditDescription() );

        changeButton.setText( detailTaskModel.getButtonText1() );
        changeButton.setVisibility( detailTaskModel.visibilityChangeButton() );

        declineButton.setText( detailTaskModel.getButtonText2() );
        declineButton.setVisibility( detailTaskModel.visibilityDeclineButton() );

    }

}
