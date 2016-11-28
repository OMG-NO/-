package com.jredu.tk.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jredu.tk.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 昂首天下 on 2016/11/11.
 */

public class GroupAdapter extends BaseAdapter {
    private List<String> list;
    private List<String> groupkey;
    private Context mContext;

    public GroupAdapter(List<String> list, List<String> groupkey, Context context) {
        this.list = list;
        this.groupkey = groupkey;
        mContext = context;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public boolean isEnabled(int position) {
        // TODO Auto-generated method stub
        if (groupkey.contains(getItem(position))) {
            return false;
        }
        return super.isEnabled(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View view = convertView;
        TextView text;
        if (groupkey.contains(list.get(position))) {
            view = LayoutInflater.from(mContext.getApplicationContext()).inflate(R.layout.devide_group_item, null);
            text = (TextView) view.findViewById(R.id.addexam_list_item_text);
            text.setTextColor(Color.parseColor("#8888ff"));
        } else {
            view = LayoutInflater.from(mContext.getApplicationContext()).inflate(R.layout.ole_exam_list_item, null);
            text = (TextView) view.findViewById(R.id.addexam_list_item_text);
            text.setTextColor(Color.parseColor("#333333"));
        }
        text.setText(list.get(position).toString());
        return view;
    }
}
