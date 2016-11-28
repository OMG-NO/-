package com.jredu.tk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.jredu.tk.R;
import com.jredu.tk.entity.GridChoice;

import java.util.List;

/**
 * Created by 昂首天下 on 2016/11/2.
 */

public class ExaminationPaperImitateAdapter extends BaseAdapter{

    private Context mContext;
    private List<GridChoice> mData;

    /**
     *
     * @param mContext 上下文
     * @param mData  数据集合
     */
    public ExaminationPaperImitateAdapter(Context mContext, List<GridChoice> mData) {
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
        final GridChoice gc = mData.get(position);
        ViewHolder viewHolder = null;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.grid_model, null, false);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) convertView.findViewById(R.id.text_view);
            viewHolder.imageView=(ImageView)convertView.findViewById(R.id.image_view);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(gc.getText());
        viewHolder.imageView.setImageResource(gc.getPic());
        return convertView;
    }

   class  ViewHolder{
        private TextView textView;
        private ImageView imageView;
    }
}
