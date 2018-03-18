package com.example.yanghanwen.taskmanagementmonster;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Detail bid activity, control the view of showing information of individual activity
 *
 * @version 1.0
 */

public class DetailBidActivity extends AppCompatActivity {

    private DetailBidModel detailBidModel;  // the model of this activity

    TextView viewProvider;  // TextView of provider's username
    TextView viewAmount;    // TextView of provider's bidded amount

    Button assignButton;    // Button of assign the task to this bid
    Button declineButton;   // Button of decline of this bid

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_bid);

        Bundle extras = getIntent().getExtras();

        if (extras == null) {

            Log.d("error", "receive no input");
            // raise exception for error occured
            finish();
        }

        // get the input value from the intent
        int position = extras.getInt("position");
        String provider = extras.getString("provider");
        String amount = extras.getString("amount");

        // create new model for this activity
        detailBidModel = new DetailBidModel(position, provider, amount);

        // set the TextView and button for control
        viewProvider = (TextView) findViewById(R.id.viewBidProvider);
        viewAmount = (TextView) findViewById(R.id.viewBidAmount);

        assignButton = (Button) findViewById(R.id.bidAssignButton);
        declineButton = (Button) findViewById(R.id.bidDeclineButton);

        // Show the value on the TextView
        viewProvider.setText( detailBidModel.getProvider() );
        viewAmount.setText( detailBidModel.getAmount() );

        // assigned button clicked, return the result mode as 1 and position of bid
        assignButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent returnIntent = new Intent();
                returnIntent.putExtra("result", 1);
                returnIntent.putExtra("position", detailBidModel.getPosition());
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });

        // decline button clicked, return the result mode as 2 and position of bid
        declineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent returnIntent = new Intent();
                returnIntent.putExtra("result", 2);
                returnIntent.putExtra("position", detailBidModel.getPosition());
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });

    }

}
