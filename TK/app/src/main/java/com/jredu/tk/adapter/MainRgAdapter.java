package com.jredu.tk.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 练习，试卷模拟Adapter
 */
public class MainRgAdapter extends FragmentPagerAdapter {
    private List<Fragment> mData;
    private String[] titles={"练习","套卷模拟"};
    public MainRgAdapter(FragmentManager fm, List<Fragment> mData) {
        super(fm);
        this.mData = mData;
    }

    @Override
    public Fragment getItem(int position) {
        return mData.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
