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

public class FiveGridsAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Task> mTaskList;

    public FiveGridsAdapter(Context mContext,ArrayList<Task>mTaskList){
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
        View v = View.inflate(mContext,R.layout.mytask_adpter_view_layout_5grids,null);

        TextView taskTitle5 = (TextView)v.findViewById(R.id.taskTitle5);
        TextView taskStatus5 = (TextView)v.findViewById(R.id.taskStatus5);
        TextView taskUsername5 = (TextView)v.findViewById(R.id.taskUsername5);
        TextView taskLowestBid5 = (TextView)v.findViewById(R.id.taskLowestBid5);
        TextView taskMyBid5 = (TextView)v.findViewById(R.id.taskMyBid5);

        taskTitle5.setText(mTaskList.get(i).getTaskname());
        taskStatus5.setText(mTaskList.get(i).getStatus());
        taskLowestBid5.setText(mTaskList.get(i).getLowestBidString());
        taskUsername5.setText(mTaskList.get(i).getUsername());
        taskMyBid5.setText(Double.toString(mTaskList.get(i).getUserAmount(MyTaskActivity.currentUsername)));


        return v;
    }
}
