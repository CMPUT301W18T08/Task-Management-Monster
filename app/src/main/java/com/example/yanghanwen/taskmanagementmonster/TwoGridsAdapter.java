package com.example.yanghanwen.taskmanagementmonster;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by Terrence on 2018/3/13.
 */

public class TwoGridsAdapter extends ArrayAdapter<Task> {
    private ArrayList<Task>list = new ArrayList<>();
    public TwoGridsAdapter(@NonNull Context context, ArrayList<Task>tasks) {
        super(context, R.layout.mytask_adpter_view_layout_2grids,tasks);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.mytask_adpter_view_layout_2grids, parent, false);

        }

        Task item = list.getItem(position);
    }
}
