package com.jredu.tk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.jredu.tk.R;
import com.jredu.tk.entity.Expandlable;

import java.util.List;
import java.util.Map;

/**
 * Created by 昂首天下 on 2016/11/4.
 */

public class ExpandlableListViewItemAdapter extends BaseExpandableListAdapter implements View.OnClickListener {
    private Context mContext;
    private List<Expandlable> groupTitle;
    //子项是一个map，key是group的id，每一个group对应一个ChildItem的list
    private Map<Integer, List<Expandlable>> childMap;
    private TurnListener mTurnListener;

    public ExpandlableListViewItemAdapter(Context context, List<Expandlable> groupTitle, Map<Integer, List<Expandlable>> childMap) {
        this.mContext = context;
        this.groupTitle = groupTitle;
        this.childMap = childMap;
    }
    /*
     *  Gets the data associated with the given child within the given group
     */
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        //我们这里返回一下每个item的名称，以便单击item时显示
        return childMap.get(groupPosition).get(childPosition).getTitle();
    }
    /*
     * 取得给定分组中给定子视图的ID. 该组ID必须在组中是唯一的.必须不同于其他所有ID（分组及子项目的ID）
     */
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
    /*
     *  Gets a View that displays the data for the given child within the given group
     */
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
                             ViewGroup parent) {
        GroupHolder childHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_child, null);
            childHolder = new GroupHolder();
            childHolder.title = (TextView) convertView.findViewById(R.id.title);
            childHolder.section = (TextView) convertView.findViewById(R.id.sum);
            convertView.setTag(childHolder);
        }else {
            childHolder = (GroupHolder) convertView.getTag();
        }
        childHolder.title.setText(childMap.get(groupPosition).get(childPosition).getTitle());
        childHolder.section.setText(childMap.get(groupPosition).get(childPosition).getSection()+"/"+childMap.get(groupPosition).get(childPosition).getSum());

        return convertView;
    }

    /*
     * 取得指定分组的子元素数
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        // TODO Auto-generated method stub
        return childMap.get(groupPosition).size();
    }

    /**
     * 取得与给定分组关联的数据
     */
    @Override
    public Object getGroup(int groupPosition) {
        return groupTitle.get(groupPosition);
    }

    /**
     * 取得分组数
     */
    @Override
    public int getGroupCount() {
        return groupTitle.size();
    }

    /**
     * 取得指定分组的ID.该组ID必须在组中是唯一的.必须不同于其他所有ID（分组及子项目的ID）
     */
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
    /*
     *Gets a View that displays the given group
     *return: the View corresponding to the group at the specified position
     */
    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder groupHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_parent, null);
            groupHolder = new GroupHolder();
            groupHolder.title = (TextView) convertView.findViewById(R.id.title);
            groupHolder.section = (TextView) convertView.findViewById(R.id.sum);
            groupHolder.turn=(ImageView)convertView.findViewById(R.id.turn);
            convertView.setTag(groupHolder);
        }else {
            groupHolder = (GroupHolder) convertView.getTag();
        }

        groupHolder.title.setText(groupTitle.get(groupPosition).getTitle());
        groupHolder.section.setText(groupTitle.get(groupPosition).getSection()+"/"+groupTitle.get(groupPosition).getSum());
        groupHolder.turn.setImageResource(groupTitle.get(groupPosition).getTurn());
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        // Indicates whether the child and group IDs are stable across changes to the underlying data
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        // Whether the child at the specified position is selectable
        return true;
    }
    /**
     * show the text on the child and group item
     */
    private class GroupHolder {
        TextView title;
        TextView section;
        ImageView turn;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.practice:
//                Log.d("MyBaseExpandableListAdapter", "你点击了group button");
            default:
                break;
        }
    }

    public interface TurnListener{
        public void turn(int positon);
    }
}
