package com.example.yanghanwen.taskmanagementmonster;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailTaskActivity extends AppCompatActivity {

    int mode;   // the input mode

    private DetailTaskModel detailTaskModel;    // model for this activity

    public static final int DETAIL_BID = 1;     // requesCode of result return from bid activity

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

    private ListView listViewBids;

    private ArrayList<Bid> bidList;
    private ArrayAdapter<Bid> adapter;

    /**
     * Setup the DetailTaskActivity
     *
     * @param savedInstanceState
     */
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

        // create the corresponding task model for different mode
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

        listViewBids = (ListView) findViewById(R.id.detailListView);

        changeButton = (Button) findViewById(R.id.buttonDetail);
        declineButton = (Button) findViewById(R.id.buttonDetail2);

        updateView();

        // if change/modified button clicked, do correspond action
        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mode == 1) {

                    String textBid = editBid.getText().toString();
                    detailTaskModel.changeButtonAction(textBid);

                    //finish();
                    updateView();
                }

                //
                else if (mode == 2) {
                    Log.i("mode=2","mode=2");
                    String textBid = editBid.getText().toString();
                    detailTaskModel.changeButtonAction(textBid);

                    updateView();
                }

                //
                else if (mode == 3) {
                    Log.i("mode=3","mode=3");
                    // requester modeify his task title
                    String textTitle = editTitle.getText().toString();
                    detailTaskModel.changeButtonAction(textTitle);
                    Intent intentService = new Intent(getApplicationContext(),TaskIntentService.class);
                    intentService.putExtra("mode","2");
                    startService(intentService);

                    updateView();
                }

            }
        });

        // if decline button clicked, do corresponding action
        declineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mode == 1) {

                    detailTaskModel.declineButtonAction("");

                    updateView();
                }

                // if provider decline his bid, finish the activity
                else if (mode == 2) {

                    detailTaskModel.declineButtonAction("");

                    finish();
                }

                else if (mode == 3) {
                    Log.i("modified description","3");

                    // provider modify his task description
                    String textDescription = editDescription.getText().toString();
                    detailTaskModel.declineButtonAction(textDescription);
                    Intent intentService = new Intent(getApplicationContext(),TaskIntentService.class);
                    intentService.putExtra("mode","3");
                    startService(intentService);

                    updateView();
                }
            }
        });

        // if click a item in the ListView
        listViewBids.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                // start the Detail bid task to view detail and further operation on it
                Intent intent = new Intent(DetailTaskActivity.this,
                        DetailBidActivity.class);

                String provider = detailTaskModel.getProvider(i);
                Double amount = detailTaskModel.getAmount(i);

                String bidAmount = Double.toString(amount);

                intent.putExtra("position", i);
                intent.putExtra("provider", provider);
                intent.putExtra("amount", bidAmount);

                startActivityForResult(intent, DETAIL_BID);
            }
        });

    }

    /**
     * show the view in the current value and status of the task
     */
    private void updateView() {

        viewTitle.setText( detailTaskModel.getTitle() );
        viewUsername.setText( detailTaskModel.getRequester() );
        viewStatus.setText( detailTaskModel.getStatus() );
        viewDescription.setText( detailTaskModel.getDescription() );

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

        int listVisibility = detailTaskModel.visibilityListView();
        listViewBids.setVisibility( listVisibility );

        // show ListView of bids if it required
        if ( listVisibility == View.VISIBLE ) {

            bidList = detailTaskModel.getBidsList();

            adapter = new ArrayAdapter<Bid>(this,
                    android.R.layout.simple_list_item_1, bidList);

            listViewBids.setAdapter(adapter);
        }

    }

    // https://stackoverflow.com/questions/10407159/how-to-manage-startactivityforresult-on-android
    // 2018-3-15

    /**
     * get the return result from the DetailBidActivity to decide further opertion on bid
     *
     * @param requesCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requesCode, int resultCode, Intent data) {

        super.onActivityResult(requesCode, resultCode, data);

        // if there is a result from DetailBidActivity
        if (requesCode == DETAIL_BID) {

            if (resultCode == RESULT_OK) {

                // get the position of bid and the operation on it
                int returnAction = data.getIntExtra("result", 0);
                int bidPosition = data.getIntExtra("position", 0);

                // assigned the task to the bid
                if (returnAction == 1) {

                    detailTaskModel.assignBid(bidPosition);

                }

                // decline the bid
                else if (returnAction == 2) {

                    detailTaskModel.declineBid(bidPosition);

                }

                // update the view to the modified task
                updateView();

            }

        }

    }

}