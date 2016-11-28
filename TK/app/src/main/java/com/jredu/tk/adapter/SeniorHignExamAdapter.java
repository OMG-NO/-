package com.jredu.tk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.jredu.tk.R;
import com.jredu.tk.entity.SeniorHighExam;

import java.util.List;

/**
 * Created by 昂首天下 on 2016/11/2.
 */

public class SeniorHignExamAdapter extends BaseAdapter {

    private Context mContext;
    private List<SeniorHighExam> mData;

    /**
     *
     * @param mContext 上下文
     * @param mData  数据集合
     */
    public SeniorHignExamAdapter(Context mContext, List<SeniorHighExam> mData) {
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
        final SeniorHighExam seniorHighExam = mData.get(position);
        SeniorHignExamAdapter.ViewHolder viewHolder = null;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.senior_hign_exam_model, null, false);
            viewHolder = new SeniorHignExamAdapter.ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (SeniorHignExamAdapter.ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(seniorHighExam.getName());
        return convertView;
    }

    class  ViewHolder{
        private TextView name;
    }
}
