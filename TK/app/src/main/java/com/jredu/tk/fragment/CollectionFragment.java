package com.jredu.tk.fragment;



import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jredu.tk.R;
import com.jredu.tk.adapter.CollectionFragmentAdapter;
import com.jredu.tk.datamanager.Data;
import com.jredu.tk.entity.Course;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollectionFragment extends Fragment {

    private List<Fragment> mData;
    List<Course> types;
    CollectionFragmentAdapter adapter;
    private ViewPager vp;
    private TabLayout tabs;

    List<Course> temTypes;
    List<Fragment> temFragments;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_collection, container, false);
        initDataNet(view);
        adapter = new CollectionFragmentAdapter(getChildFragmentManager(), mData, types);
        vp.setAdapter(adapter);
        tabs.setupWithViewPager(vp);
        temFragments.addAll(mData);
        temTypes.addAll(types);
        return view;
    }

    public void initDataNet(View view) {
        temFragments = new ArrayList<>();
        temTypes = new ArrayList<>();
        mData = new ArrayList<>();
        vp = (ViewPager) view.findViewById(R.id.viewpager);
        tabs = (TabLayout) view.findViewById(R.id.tabs);
        types = Data.initTabsLayout();
        for (int i = 0; i < types.size(); i++) {
                CollectionQuestionFragment f = new CollectionQuestionFragment();
                Bundle bundle = new Bundle();
                bundle.putString("name", types.get(i).getName());
//                bundle.putParcelable("newsType", types.get(i));
                f.setArguments(bundle);
                mData.add(f);
        }
    }
}
