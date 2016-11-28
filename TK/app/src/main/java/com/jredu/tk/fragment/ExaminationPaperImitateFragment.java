package com.jredu.tk.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import com.jredu.tk.R;
import com.jredu.tk.activity.GroupActivity;
import com.jredu.tk.adapter.ExaminationPaperImitateAdapter;
import com.jredu.tk.adapter.SeniorHignExamAdapter;
import com.jredu.tk.control.GotoAnswerActivity;
import com.jredu.tk.datamanager.Data;
import com.jredu.tk.entity.GridChoice;
import com.jredu.tk.entity.RequestBundle;
import com.jredu.tk.entity.SeniorHighExam;
import com.jredu.tk.mydefineview.MyGridView;
import com.jredu.tk.unit.Util;

import java.util.List;


public class ExaminationPaperImitateFragment extends Fragment {

    //集合存放 试卷对象
    private List<GridChoice> mData;
    private ExaminationPaperImitateAdapter adapter;
    MyGridView myGridView;
    //存放真题对象（listitem）
    private List<SeniorHighExam> mSeniorHighExams;
    private SeniorHignExamAdapter mSeniorHignExamAdapter;
    ListView mListView;
    FragmentManager fm;
    FragmentTransaction ft;
    View header;
    //当前科目
    String type;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_examination_paper_imitate, container, false);
        //加载gridview  历年真题  中考真题  模拟真题  期末试卷  期中试卷
        myGridView=(MyGridView)view.findViewById(R.id.examination_paper_choice);
        mListView=(ListView)view.findViewById(R.id.list_view);
        type="语文";
        init();
        myGridView.setAdapter(adapter);
        header=inflater.inflate(R.layout.header, container, false);
        onClick();
        mListView.setAdapter(mSeniorHignExamAdapter);
        mListView.addHeaderView(header);
        //为了解决ListView在ScrollView中只能显示一行数据的问题
        Util.setListViewHeightBasedOnChildren(mListView);
        return view;
    }


    /**
     * 初始化数据
     */
    public void init(){
        fm = getFragmentManager();
        ft = fm.beginTransaction();
        mData= Data.initxaminationPaperGridChoice();
        adapter=new ExaminationPaperImitateAdapter(getActivity(),mData);
        mSeniorHighExams= Data.initSeniorHighExam();
        mSeniorHignExamAdapter=new SeniorHignExamAdapter(getActivity(),mSeniorHighExams);
    }

    /**
     * 设置点击事件
     */
    public void onClick(){
        myGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String degree="0";
                switch (position) {
                    case 0:
                        degree="1";
                        break;
                    case 1:
                        degree="2";
                        break;
                    case 2:
                        degree="3";
                        break;
                }
//                GotoAnswerActivity.jumpByDegree("语文",degree,getContext());
                RequestBundle requestBundle=new RequestBundle("1",type,"3");
                requestBundle.setDegree(degree);
                Bundle bundle=new Bundle();
                bundle.putParcelable("requestBundle",requestBundle);
                Intent intent=new Intent(getContext(),GroupActivity.class);
                intent.putExtra("requestBundle",bundle);
                startActivity(intent);
            }
        });

    }
    //得到Course名称
    public void getCourseName(String courseName){
        Toast.makeText(getActivity(),courseName,Toast.LENGTH_SHORT).show();
    }

    public void setType(String type){
        this.type=type;
    }


}
