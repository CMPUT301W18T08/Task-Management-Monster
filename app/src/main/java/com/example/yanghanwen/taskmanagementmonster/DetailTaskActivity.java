package com.example.yanghanwen.taskmanagementmonster;

import android.graphics.YuvImage;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DetailTaskActivity extends AppCompatActivity {

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

    private Button actionButton1;
    private Button actionButton2;

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
        int mode = extras.getInt("mode");

        String title = extras.getString("title");
        String requestor = extras.getString("requestor");

        if (mode == 1) {

            detailTaskModel = new DetailTaskSearchModel(title, requestor);
        }

        else if (mode == 2) {

            detailTaskModel = new DetailTaskProviderModel(title, requestor);
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

        actionButton1 = (Button) findViewById(R.id.buttonDetail);
        actionButton2 = (Button) findViewById(R.id.buttonDetail2);

        viewTitle.setText( detailTaskModel.getTitle() );
        viewUsername.setText( detailTaskModel.getRequestor() );
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

        actionButton1.setText( detailTaskModel.getButtonText1() );
        actionButton1.setVisibility( detailTaskModel.visibilityButton1() );

        actionButton2.setText( detailTaskModel.getButtonText2() );
        actionButton2.setVisibility( detailTaskModel.visibilityButton2() );

        /*


        // mode 1: provider search task
        if (mode == 1) {

            actionButton1.setText("Bid This Task");

            if (status == "requested") {

                viewBidInfo = (TextView) findViewById(R.id.detailBidInformation);
                viewBidInfo.setText("Task currently have no bid.");

                findViewById(R.id.detailBidLowest).setVisibility(View.GONE);
                findViewById(R.id.detailMyBid).setVisibility(View.GONE);
                findViewById(R.id.viewDetailMyBid).setVisibility(View.GONE);
                findViewById(R.id.buttonDetail2).setVisibility(View.GONE);

            }

            else  {

                lowestBid = task.getLowestBid();
                myBid = task.getUserAmount(current_user);

                viewBidInfo = (TextView) findViewById(R.id.detailBidInformation);
                viewBidLowest = (TextView) findViewById(R.id.detailBidLowest);
                viewBidMyBidInfo = (TextView) findViewById(R.id.detailMyBid);

                viewBidInfo.setText("Current Lowest Bid");
                viewBidLowest.setText(lowestBid.toString());

                if (myBid == null) {

                    viewBidMyBidInfo.setText("User not bid this task yet");

                    findViewById(R.id.viewDetailMyBid).setVisibility(View.GONE);
                }

                else {

                    actionButton1.setText("Change My Bid");

                    viewBidMyBid = (TextView) findViewById(R.id.viewDetailMyBid);

                    viewBidMyBidInfo.setText("Current My Bid");
                    viewBidMyBid.setText(myBid.toString());
                }

            }

        }

        // mode 2: provider task detail
        else if (mode == 2) {

            actionButton1.setText("Change My Bid");
            actionButton2.setText("Decline My Bid");

            lowestBid = task.getLowestBid();
            myBid = task.getUserAmount(current_user);

            viewBidInfo = (TextView) findViewById(R.id.detailBidInformation);
            viewBidMyBid = (TextView) findViewById(R.id.viewDetailMyBid);
            viewBidLowest = (TextView) findViewById(R.id.detailBidLowest);
            viewBidMyBidInfo = (TextView) findViewById(R.id.detailMyBid);

            viewBidMyBidInfo.setText("Current My Bid");
            viewBidMyBid.setText(myBid.toString());
            viewBidInfo.setText("Current Lowest Bid");
            viewBidLowest.setText(lowestBid.toString());

        }

        // end of processing different mode

        actionButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myBid = task.getUserAmount(MainActivity.user.getUserName());

                editBid = (EditText) findViewById(R.id.editTextDetail);

                String textBid = editBid.getText().toString();
                newBid = Double.parseDouble(textBid);

                if (myBid == null) {

                    task.createNewBid(current_user, newBid);

                }

                else {

                    task.modifyBid(current_user, newBid);
                }

                finish();

            }
        });

        actionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                task.declineBid(current_user);

                finish();
            }
        });

        */

    }

}
