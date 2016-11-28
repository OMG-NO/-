package com.jredu.tk.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.jredu.tk.R;
import com.jredu.tk.adapter.CommitAdapter;
import com.jredu.tk.entity.Question;

import java.util.List;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class CommitActivity extends SwipeBackActivity {
    //上左右拉删除服务
    private SwipeBackLayout mSwipeBackLayout;
    //返回
    private ImageView img_back;
    //提交按钮
    private TextView tv_commit;
    //显示答案个数
    private GridView gv;
    //答案显示的Adapter
    private CommitAdapter commitAdapter;
    private Bundle questionListBundle;
    private List<Question> questionList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commit);
        getview();
        questionListBundle=getIntent().getBundleExtra("questionList");
        questionList=questionListBundle.getParcelableArrayList("questionList");
        commitAdapter=new CommitAdapter(questionList,this);
        gv.setAdapter(commitAdapter);
        tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CommitActivity.this,GradeActivity.class);
                intent.putExtra("questionList",questionListBundle);
                startActivity(intent);
                finish();
            }
        });
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void getview(){
        //设置滑动方向
        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeSize(200);
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        img_back = (ImageView) findViewById(R.id.img_back);
        tv_commit = (TextView) findViewById(R.id.tv_commit);
        gv = (GridView) findViewById(R.id.gv);
        tv_commit= (TextView) findViewById(R.id.tv_commit);
    }
}
