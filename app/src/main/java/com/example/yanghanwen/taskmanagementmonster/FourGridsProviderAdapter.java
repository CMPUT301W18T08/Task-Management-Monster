package com.example.yanghanwen.taskmanagementmonster;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Terrence on 2018/3/17.
 */


 /** This is a four grids adapter to adapt the arraylist into Listview
  * works for showing task title and task status and task lowest bid in ListView and task's username
  */
public class FourGridsProviderAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Task> mTaskList;

    public FourGridsProviderAdapter(Context mContext,ArrayList<Task>mTaskList){
        this.mContext = mContext;
        this.mTaskList = mTaskList;
    }
    @Override
    public int getCount() {
        return mTaskList.size();
    }

    @Override
    public Object getItem(int i) {
        return mTaskList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(mContext,R.layout.mytask_adpter_view_layout_4grids_provider,null);

        TextView taskTitle = (TextView)v.findViewById(R.id.taskTitle);
        TextView taskStatus = (TextView)v.findViewById(R.id.taskStatus);
        TextView taskUsername = (TextView)v.findViewById(R.id.taskUsername);
        TextView myAcceptedBid = (TextView)v.findViewById(R.id.myAcceptedBid);

        taskTitle.setText(mTaskList.get(i).getTaskname());
        taskStatus.setText(mTaskList.get(i).getStatus());
        taskUsername.setText(mTaskList.get(i).getUsername());
        myAcceptedBid.setText(Double.toString(mTaskList.get(i).getBid(0).getAmount()));


        return v;
    }
}