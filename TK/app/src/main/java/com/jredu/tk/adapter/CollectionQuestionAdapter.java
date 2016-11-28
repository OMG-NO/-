package com.jredu.tk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jredu.tk.R;
import com.jredu.tk.entity.Question;

import java.util.List;

/**
 * Created by 昂首天下 on 2016/11/10.
 */

public class CollectionQuestionAdapter extends BaseAdapter {
    private Context mContext;
    private List<Question> mData;

    public CollectionQuestionAdapter(Context context, List<Question> data) {
        mContext = context;
        mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Question getItem(int position) {
        return mData.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Question question = mData.get(position);
        ViewHolder viewHolder = null;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.question_collection, null, false);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.title.setText(question.getTitle());
        return convertView;
    }

    class ViewHolder{
        TextView title;
    }
}
