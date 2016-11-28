package com.jredu.tk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jredu.tk.R;

import java.util.List;

/**
 * Created by Administrator on 2016/11/6.
 * 所有地区显示的Adapter
 */

public class PlaceAdapter extends BaseAdapter {
    private List<String> mData;
    private Context mContext;

    public PlaceAdapter(List<String> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return this.mData.size();
    }

    @Override
    public Object getItem(int position) {
        return this.mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String place=this.mData.get(position);
        LayoutInflater inflater=LayoutInflater.from(mContext);
        ViewHolder viewHolder=null;
        if(convertView==null){
            convertView=inflater.inflate(R.layout.place_adapter,null,false);
            viewHolder=new ViewHolder();
            viewHolder.name=(TextView)convertView.findViewById(R.id.tv_place);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)convertView.getTag();
        }
        viewHolder.name.setText(place);
        return convertView;
    }
    class  ViewHolder{
        TextView name;
    }
}
