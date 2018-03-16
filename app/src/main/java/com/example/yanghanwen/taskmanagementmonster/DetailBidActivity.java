package com.example.yanghanwen.taskmanagementmonster;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailBidActivity extends AppCompatActivity {

    private DetailBidModel detailBidModel;

    TextView viewProvider;
    TextView viewAmount;

    Button assignButton;
    Button declineButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_bid);

        Bundle extras = getIntent().getExtras();

        if (extras == null) {

            // raise exception for error occured
            finish();
        }

        int position = extras.getInt("position");
        String provider = extras.getString("provider");
        String amount = extras.getString("amount");

        detailBidModel = new DetailBidModel(position, provider, amount);

        viewProvider = (TextView) findViewById(R.id.viewBidProvider);
        viewAmount = (TextView) findViewById(R.id.viewBidAmount);

        assignButton = (Button) findViewById(R.id.bidAssignButton);
        declineButton = (Button) findViewById(R.id.bidDeclineButton);

        viewProvider.setText( detailBidModel.getProvider() );
        viewAmount.setText( detailBidModel.getAmount() );

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
