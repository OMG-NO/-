package com.jredu.tk.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.jredu.tk.R;
import com.jredu.tk.activity.AnswerActivity;
import com.jredu.tk.activity.GradeActivity;
import com.jredu.tk.application.Constant;
import com.jredu.tk.entity.Question;
import com.jredu.tk.entity.RequestBundle;

import java.util.List;

/**
 * Created by du on 2016/11/15.
 * 成绩单Adapter
 */
public class GradeAdapter extends BaseAdapter {
    private Context mContext;
    private List<Question> mData;

    public GradeAdapter(Context mContext, List<Question> mData) {
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
        return mData.get(i).getId();
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        Question question=mData.get(i);
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.grade_adapter, null, false);
            viewHolder = new ViewHolder();
            viewHolder.tv_result_num = (TextView) view.findViewById(R.id.tv_result_num);
            viewHolder.tv_result=(TextView)view.findViewById(R.id.tv_result);
            viewHolder.btn_analysis=(Button)view.findViewById(R.id.btn_analysis);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tv_result_num.setText((i+1)+"");
        if (question.isSelected()&!question.isRight()){
            viewHolder.tv_result_num.setBackgroundColor(mContext.getResources().getColor(R.color.tv_result_error));
            viewHolder.tv_result.setText("答错");
        }else if (question.isSelected()&&question.isRight()){
            viewHolder.tv_result_num.setBackgroundColor(mContext.getResources().getColor(R.color.tv_result_right));
            viewHolder.tv_result.setText("答对");
        }else {
            viewHolder.tv_result_num.setBackgroundColor(mContext.getResources().getColor(R.color.tv_result_unselected));
            viewHolder.tv_result.setText("未做");
        }
        (viewHolder.btn_analysis).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Question question=mData.get(i);
                Intent intent=new Intent(mContext, AnswerActivity.class);
                RequestBundle requestBundle=new RequestBundle(
                        "1",
                        question.getTitle()
                );
                requestBundle.setCount("1");
                intent.putExtra("url", Constant.findSubjectByTypeAndTitle);
                Bundle bundle=new Bundle();
                bundle.putParcelable("requestBundle",requestBundle);
                intent.putExtra("requestBundle",bundle);

                mContext.startActivity(intent);
            }
        });
        return view;
    }

    public class ViewHolder {
        TextView tv_result_num;
        TextView tv_result;
        Button btn_analysis;
    }
}
