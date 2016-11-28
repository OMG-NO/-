package com.jredu.tk.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jredu.tk.R;
import com.jredu.tk.entity.Question;

import java.util.List;

/**
 * Created by du on 2016/11/15.
 * 答案显示的Adapter
 */

public class CommitAdapter extends BaseAdapter {
    private List<Question> mData;
    private Context mContext;

    public CommitAdapter(List<Question> mData, Context mContext) {
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
        Question place=this.mData.get(position);
        LayoutInflater inflater=LayoutInflater.from(mContext);
        ViewHolder viewHolder=null;
        if(convertView==null){
            convertView=inflater.inflate(R.layout.commit_answer,null,false);
            viewHolder=new ViewHolder();
            viewHolder.cb_answer=(TextView)convertView.findViewById(R.id.cb_answer);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)convertView.getTag();
        }
        viewHolder.cb_answer.setText((position+1)+"");
        if (place.isSelected()){
            viewHolder.cb_answer.setBackground(mContext.getResources().getDrawable(R.drawable.commit_anser_cli_bg));
            viewHolder.cb_answer.setTextColor(Color.WHITE);
        }
        return convertView;
    }
    class  ViewHolder{
        TextView cb_answer;
    }
}
