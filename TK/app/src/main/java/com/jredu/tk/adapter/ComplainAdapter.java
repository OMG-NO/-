package com.jredu.tk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jredu.tk.R;
import com.jredu.tk.dao.UserDao;
import com.jredu.tk.entity.Compalin;
import com.jredu.tk.entity.User;
import com.jredu.tk.help.BitmapAndString;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by du on 2016/11/5.
 * 吐槽Adapter
 */
public class ComplainAdapter extends BaseAdapter {
    private Context mContext;
    private List<Compalin> mData;
    private UserDao dao;

    public ComplainAdapter(Context mContext, List<Compalin> mData) {
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
        Compalin c = mData.get(i);
        dao=new UserDao(mContext);
        User user=dao.findUserByAccount(c.getAccount());
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.complain_adapter, null, false);
            viewHolder = new ViewHolder();
            viewHolder.photo = (ImageView) view.findViewById(R.id.photo);
            viewHolder.text = (TextView) view.findViewById(R.id.text);
            viewHolder.date=(TextView)view.findViewById(R.id.date);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.text.setText(c.getText());
        viewHolder.date.setText(c.getDate());
        if (user.isChangePhoto()){
            //判断是不是登录界面转过来的，是就下载图片
            Picasso.with(mContext)
                    .load(user.getPhoto())
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(viewHolder.photo);
        }else {
            viewHolder.photo.setImageBitmap(BitmapAndString.stringToBitmap(user.getPhoto()));
        }
        return view;
    }

    public class ViewHolder {
        ImageView photo;
        TextView text;
        TextView date;
    }
}
