package com.jredu.tk.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jredu.tk.R;
import com.jredu.tk.activity.AnswerActivity;
import com.jredu.tk.adapter.CollectionQuestionAdapter;
import com.jredu.tk.application.Constant;
import com.jredu.tk.datamanager.JSONHelper;
import com.jredu.tk.datamanager.RequestManager;
import com.jredu.tk.entity.Question;
import com.jredu.tk.entity.RequestBundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollectionQuestionFragment extends Fragment {

    private CollectionQuestionAdapter adapter;
    private List<Question> mData;
    private ListView mListView;
    private String courseName;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_collection_question, container, false);
        mListView=(ListView)view.findViewById(R.id.list_view) ;
        courseName=getArguments().getString("name");
        initData();
        loadLatest();
        listItemClick();
        return view;
    }

    private void initData(){
        mData=new ArrayList<>();
        adapter=new CollectionQuestionAdapter(getActivity(),mData);
        mListView.setAdapter(adapter);

    }


    private void loadLatest() {
        final HashMap<String, String> params = new HashMap<>();
        params.put("account", "admin");
        params.put("course",courseName);
        RequestManager.getInstance(getContext()).requestAsyn(
                "subjects/findSubjectsByAccountAndCourse",
                RequestManager.TYPE_POST_JSON,
                params,
                new RequestManager.ReqCallBack<String>() {
                    @Override
                    public void onReqSuccess(String result) {
                        Log.i("请求结果", result);
                        mData=  JSONHelper.transformCollectionQuestion(result);
                        adapter=new CollectionQuestionAdapter(getActivity(),mData);
                        mListView.setAdapter(adapter);

                    }

                    @Override
                    public void onReqFailed(String errorMsg) {
                        Log.d("请求失败", errorMsg);
                    }
                }
        );
    }

    public void listItemClick(){
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RequestBundle requestBundle=new RequestBundle("1",mData.get(position).getTitle());
                requestBundle.setCount("1");
                Bundle bundle=new Bundle();
                bundle.putParcelable("requestBundle",requestBundle);
                Intent intent=new Intent(getContext(), AnswerActivity.class);
                intent.putExtra("requestBundle",bundle);
                intent.putExtra("url", Constant.findSubjectByTypeAndTitle);
                startActivity(intent);
            }
        });
    }

}
