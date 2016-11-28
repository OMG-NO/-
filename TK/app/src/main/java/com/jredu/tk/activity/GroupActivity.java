package com.jredu.tk.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.jredu.tk.R;
import com.jredu.tk.adapter.GradeChoiceAdapter;
import com.jredu.tk.adapter.GroupAdapter;
import com.jredu.tk.application.Constant;
import com.jredu.tk.datamanager.Data;
import com.jredu.tk.entity.GradeChoice;
import com.jredu.tk.entity.RequestBundle;
import com.jredu.tk.mydefineview.MyGridView;
import com.jredu.tk.mydefineview.QuicLocationBar;

import java.util.ArrayList;
import java.util.List;

public class GroupActivity extends AppCompatActivity {

    private List<String> list = null;
    private List<String> groupkey = new ArrayList<String>();
    private ListView listview;
    private QuicLocationBar mQuicLocationBar;
    private View mView;
    private View visible;
    private boolean flag;
    private GradeChoiceAdapter mGradeChoiceAdapter;
    private List<GradeChoice> mGradeChoiceList;
    private MyGridView mMyGridView;
    private ImageView goBack;
    private TextView grade;
    String courseName="语文";

    private RequestBundle requestBundle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        requestBundle=getIntent().getBundleExtra("requestBundle").getParcelable("requestBundle");

        findView();
        initData();
        onClick();
        GroupAdapter adapter = new GroupAdapter(list,groupkey,this);
        listview.setAdapter(adapter);
    }

    /**
     * 初始化数据
     */
    public void initData() {
        courseName=getIntent().getStringExtra("courseName");
        List<List<String>> lists = new ArrayList<>();
        lists = Data.initOleExamListItem();
        list = lists.get(0);
        groupkey = lists.get(1);
        mGradeChoiceList=Data.initGradeChoice();
        mGradeChoiceAdapter=new GradeChoiceAdapter(this,mGradeChoiceList);
        mMyGridView.setAdapter(mGradeChoiceAdapter);

    }

    /**
     * 查找控件
     */
    public void findView(){
        listview = (ListView) findViewById(R.id.group_list);
        mQuicLocationBar = (QuicLocationBar) findViewById(R.id.loactionbar);
        mView = (View) findViewById(R.id.select);
        grade=(TextView)findViewById(R.id.grade);
        visible = findViewById(R.id.visible);
        goBack=(ImageView)findViewById(R.id.go_back);
        mMyGridView=(MyGridView)findViewById(R.id.grade_choice);
    }

    /**
     * 设置点击事件
     */
    public  void onClick(){

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Bundle toAnswerBundle=new Bundle();
                toAnswerBundle.putParcelable("requestBundle",requestBundle);
                Intent intent=new Intent(GroupActivity.this,AnswerActivity.class);
                intent.putExtra("requestBundle",toAnswerBundle);
                intent.putExtra("url", Constant.findSubjectsByTypeAndCourseAndDegree);
                startActivity(intent);

            }
        });

        //  退出activity
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 标题栏点击事件
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flag) {
                    visible.setVisibility(View.VISIBLE);
                }else{
                    visible.setVisibility(View.GONE);
                }
                flag=!flag;
            }
        });

        // 年级点击事件

        mMyGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GradeChoice gradeChoice=mGradeChoiceList.get(position);
                grade.setText("("+gradeChoice.getGrade()+")");
                visible.setVisibility(View.GONE);
            }
        });
    }

    //设置要请求的一套题
    private RequestBundle creatRequestBundle(){
        RequestBundle requestBundle=new RequestBundle();
        requestBundle.setType("1");
        requestBundle.setCourse(courseName);
        requestBundle.setCount("3");
        return requestBundle;
    }

}
