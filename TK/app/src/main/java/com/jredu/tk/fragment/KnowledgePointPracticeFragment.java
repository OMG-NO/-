package com.jredu.tk.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.jredu.tk.R;
import com.jredu.tk.activity.AnswerActivity;
import com.jredu.tk.adapter.ExpandlableListViewItemAdapter;
import com.jredu.tk.application.Constant;
import com.jredu.tk.datamanager.Data;
import com.jredu.tk.datamanager.JSONHelper;
import com.jredu.tk.datamanager.RequestManager;
import com.jredu.tk.entity.Expandlable;
import com.jredu.tk.entity.RequestBundle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class KnowledgePointPracticeFragment extends Fragment {

    private static final String TAG=KnowledgePointPracticeFragment.class.getSimpleName();

    private ExpandableListView expandList;
    private ExpandlableListViewItemAdapter myAdapter;
    private List<Expandlable> groupData;//group的数据源
    private Map<Integer, List<Expandlable>> childData;//child的数据源

    private String type="语文";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_blank_knowledge_point_practice, container, false);
        expandList = (ExpandableListView) view.findViewById(R.id.expandable_list_view);
        expandList.setGroupIndicator(null);
        loadLatest();
        onClick();
        return view;
    }

    private void init() {
        groupData = Data.getParentList();
        childData = Data.getChildMap();
        myAdapter = new ExpandlableListViewItemAdapter(getActivity(), groupData, childData);
    }

    private void loadLatest() {
        final HashMap<String, String> params = new HashMap<>();
        Log.i(TAG,type);
        params.put("type", "1");
        params.put("course", type);
        params.put("count", "3");
        RequestManager.getInstance(getContext()).requestAsyn(
                "courses/findCourses",
                RequestManager.TYPE_POST_JSON,
                params,
                new RequestManager.ReqCallBack<String>() {
                    @Override
                    public void onReqSuccess(String result) {
                        JSONHelper.initExpandlableData(result);
                        init();
                        expandList.setAdapter(myAdapter);
                    }

                    @Override
                    public void onReqFailed(String errorMsg) {
                        Log.d("请求失败", errorMsg);
                    }
                }
        );
    }

    public void setType(String type){
        this.type=type;
    }

    /**
     * 添加点击事件
     */
    private void onClick() {
        expandList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Expandlable expandlable = groupData.get(groupPosition);
                if (expandList.isGroupExpanded(groupPosition)) {
                    expandlable.setTurn(R.mipmap.arrow_down);
                } else {
                    expandlable.setTurn(R.mipmap.arrow_up);
                }
                myAdapter.notifyDataSetChanged();
                return false;
            }
        });

        expandList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                List<Expandlable> expandlableList=childData.get(groupPosition);
                Expandlable expandlable=expandlableList.get(childPosition);

                RequestBundle requestBundle=creatrequestBundle(type,expandlable.getTitle());
                Bundle bundle=new Bundle();
                bundle.putParcelable("requestBundle",requestBundle);
                Intent intent=new Intent(getContext(), AnswerActivity.class);
                intent.putExtra("requestBundle",bundle);
                intent.putExtra("url", Constant.findSubjectsByTypeAndCourse);
                startActivity(intent);
                return false;
            }
        });

    }

    private RequestBundle creatrequestBundle(String course, String section){
        RequestBundle requestBundle=new RequestBundle();
        requestBundle.setType("1");
        requestBundle.setCourse(course);
        requestBundle.setCount("3");
        requestBundle.setSection(section);

        return requestBundle;
    }
}
