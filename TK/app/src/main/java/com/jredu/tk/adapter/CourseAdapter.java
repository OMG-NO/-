package com.jredu.tk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jredu.tk.R;
import com.jredu.tk.entity.Course;

import java.util.List;

/**
 * Created by du on 2016/11/2.
 * 主界面左侧CourseAdapter
 */

public class CourseAdapter extends BaseAdapter {
    private Context mContext;
    private List<Course> mData;

    public CourseAdapter(Context mContext, List<Course> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        Course c = mData.get(i);
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.course_adapter, null, false);
            viewHolder = new ViewHolder();
            viewHolder.img_course_photo = (ImageView) view.findViewById(R.id.img_course_photo);
            viewHolder.tv_course_name = (TextView) view.findViewById(R.id.tv_course_name);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tv_course_name.setText(c.getName());
        viewHolder.img_course_photo.setImageResource(c.getImgUrl());
        return view;
    }

    public class ViewHolder {
        ImageView img_course_photo;
        TextView tv_course_name;
    }
}
