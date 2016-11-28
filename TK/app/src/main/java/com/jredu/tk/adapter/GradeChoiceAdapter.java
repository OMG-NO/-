package com.jredu.tk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.jredu.tk.R;
import com.jredu.tk.entity.GradeChoice;

import java.util.List;

/**
 * Created by 昂首天下 on 2016/11/3.
 */

public class GradeChoiceAdapter extends BaseAdapter {

    private Context mContext;
    private List<GradeChoice> mData;

    /**
     *
     * @param mContext 上下文
     * @param mData  数据集合
     */
    public GradeChoiceAdapter(Context mContext, List<GradeChoice> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData.size();
    }


    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final GradeChoice gc = mData.get(position);
        ViewHolder viewHolder = null;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.grade_choice_model, null, false);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) convertView.findViewById(R.id.grade_choice);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(gc.getGrade());
        return convertView;
    }

    class  ViewHolder{
        private TextView textView;
    }
}
