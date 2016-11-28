package com.jredu.tk.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jredu.tk.R;
import com.jredu.tk.adapter.GradeAdapter;
import com.jredu.tk.application.Constant;
import com.jredu.tk.entity.Answer;
import com.jredu.tk.entity.Question;
import com.jredu.tk.entity.RequestBundle;
import com.jredu.tk.help.RoundImageView;
import com.jredu.tk.unit.ChannelListView;

import java.util.ArrayList;
import java.util.List;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * 成绩单Activity
 */
public class GradeActivity extends SwipeBackActivity {
    //返回按钮
    private ImageView img_back;
    //心情图片
    private RoundImageView img_heart;
    //成绩描述
    private TextView tv_follow;
    //正确数量
    private TextView tv_correct;
    //答题总数
    private TextView tv_sum;
    //显示答题记录
    private ListView lv;
    //答题记录显示Adapter
    private GradeAdapter gradeAdapter;
    //题目
    private List<Question> mData;
    //上左右拉删除服务
    private SwipeBackLayout mSwipeBackLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);
        //设置滑动方向
        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeSize(200);
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        img_back = (ImageView) findViewById(R.id.img_back);
        img_heart = (RoundImageView) findViewById(R.id.img_heart);
        tv_follow = (TextView) findViewById(R.id.tv_follow);
        tv_correct = (TextView) findViewById(R.id.tv_correct);
        tv_sum = (TextView) findViewById(R.id.tv_sum);
        lv = (ChannelListView) findViewById(R.id.lv);
        initData();
        setText();
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    //插入数据
    private void initData() {
        mData = new ArrayList<>();
        mData=getIntent().getBundleExtra("questionList").getParcelableArrayList("questionList");
        gradeAdapter = new GradeAdapter(this, mData);
        lv.setAdapter(gradeAdapter);
    }

    private void setText(){
        //设置正确数量和一共数量
        tv_sum.setText(mData.size()+"");
        int flag=0;
        for (int i=0;i<mData.size();i++){
            if (mData.get(i).isRight()){
                flag++;
            }
        }
        tv_correct.setText(""+flag);
        //设置鼓励词
        if (flag==mData.size()){
            tv_follow.setText("恭喜你，全部答对！");
            img_heart.setImageResource(R.mipmap.result_bg);
        }else if (flag>=mData.size()/2){
            tv_follow.setText("再接再厉~");
            img_heart.setImageResource(R.mipmap.analysis_complete);
        }else {
            tv_follow.setText("要努力了~");
            img_heart.setImageResource(R.mipmap.answer_report_header_part);
        }
    }
}
