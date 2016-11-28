package com.jredu.tk.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.jredu.tk.R;
import com.jredu.tk.adapter.ExpandlableListViewItemAdapter;
import com.jredu.tk.datamanager.Data;
import com.jredu.tk.entity.Expandlable;

import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class KnowledgePointPracticeFragment2 extends Fragment {

    private ExpandableListView expandList;
    private ExpandlableListViewItemAdapter myAdapter;
    private List<Expandlable> groupData;//group的数据源
    private Map<Integer, List<Expandlable>> childData;//child的数据源
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_blank_knowledge_point_practice, container, false);
        //数据初始化
        init();
        expandList=(ExpandableListView)view.findViewById(R.id.expandable_list_view);
        expandList.setGroupIndicator(null);
        expandList.setAdapter(myAdapter);
        //设置点击事件
        onClick();
        return view;
    }


    /**
     * 初始化数据
     */
    public  void  init(){
        groupData= Data.initExpandlableParentData2();
        childData= Data.initExpandlableChileData2();
        myAdapter=new ExpandlableListViewItemAdapter(getActivity(),groupData,childData);
    }


    /**
     * 点击事件
     */
    public  void onClick(){
        expandList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Expandlable expandlable =groupData.get(groupPosition);
                if(expandList.isGroupExpanded(groupPosition)){
                    expandlable.setTurn(R.mipmap.arrow_down);
                }else {
                    expandlable.setTurn(R.mipmap.arrow_up);
                }
                myAdapter.notifyDataSetChanged();
                return false;
            }
        });
    }
}
