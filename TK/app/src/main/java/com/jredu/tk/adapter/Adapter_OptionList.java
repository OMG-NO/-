package com.jredu.tk.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.jredu.tk.R;
import com.jredu.tk.entity.Option;

import java.util.List;

/**
 * Created by HunBing on 2016/11/5.
 *
 * 答题页面选项 列表的 适配器
 */

public class Adapter_OptionList extends BaseAdapter {
    List<Option> optionList;
    Context context;

    int[] unClickFlags={
            R.drawable.a_unclick,
            R.drawable.b_unclick,
            R.drawable.c_unclick,
            R.drawable.d_unclick
    };

    int[] clickFlags={
            R.drawable.a_click,
            R.drawable.b_click,
            R.drawable.c_click,
            R.drawable.d_click
    };

    public Adapter_OptionList(List<Option> optionList, Context context) {
       this.optionList=optionList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return optionList.size();
    }

    @Override
    public Object getItem(int position) {
        return optionList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Option option=optionList.get(position);
        ViewHolder viewHolder = null;

        LayoutInflater inflater = LayoutInflater.from(context);
        if (convertView == null) {
            viewHolder=new ViewHolder();
            convertView=inflater.inflate(R.layout.option_view,null);
            viewHolder.option= (LinearLayout) convertView.findViewById(R.id.option);
            viewHolder.option_flag= (ImageView) convertView.findViewById(R.id.option_flag);
            viewHolder.optionContent= (TextView) convertView.findViewById(R.id.OptionContent);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (option.isSelec()){
            viewHolder.option.setBackgroundColor(Color.rgb(168,217,245));
        }else{
            viewHolder.option.setBackgroundColor(Color.WHITE);
        }

        viewHolder.optionContent.setText(option.getOptionContent());
        viewHolder.option_flag.setImageResource(searchFlag(option.isSelec(),position));

        return convertView;
    }

    private int searchFlag(boolean isSelect, int position){

        if (isSelect){
            return clickFlags[position];
        }else {
            return unClickFlags[position];
        }
    }

    class ViewHolder {
        LinearLayout option;
        ImageView option_flag;
        TextView optionContent;
    }
}
