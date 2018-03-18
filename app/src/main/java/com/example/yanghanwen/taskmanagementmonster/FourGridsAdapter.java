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

public class FourGridsAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Task> mTaskList;

    public FourGridsAdapter(Context mContext,ArrayList<Task>mTaskList){
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
        View v = View.inflate(mContext,R.layout.mytask_adpter_view_layout_4grids,null);

        TextView taskTitle4 = (TextView)v.findViewById(R.id.taskTitle4);
        TextView taskStatus4 = (TextView)v.findViewById(R.id.taskStatus4);
        TextView taskUsername4 = (TextView)v.findViewById(R.id.taskUsername4);
        TextView taskBid4 = (TextView)v.findViewById(R.id.taskBid4);

        taskTitle4.setText(mTaskList.get(i).getTaskname());
        taskStatus4.setText(mTaskList.get(i).getStatus());
        taskUsername4.setText(mTaskList.get(i).getBid(0).getUserName());
        taskBid4.setText(Double.toString(mTaskList.get(i).getBid(0).getAmount()));


        return v;
    }
}
