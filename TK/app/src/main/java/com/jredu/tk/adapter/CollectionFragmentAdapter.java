package com.jredu.tk.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.jredu.tk.entity.Course;

import java.util.List;

/**
 * Created by 昂首天下 on 2016/11/10.
 */

public class CollectionFragmentAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> mData;
    private List<Course> mCourses;

    public CollectionFragmentAdapter(FragmentManager fm, List<Fragment> mData) {
        super(fm);
        this.mData = mData;
    }

    public CollectionFragmentAdapter(FragmentManager fm, List<Fragment> data, List<Course> mCourses) {
        super(fm);
        mData = data;
        this.mCourses = mCourses;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }


    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {
        return mData.get(position);
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public CharSequence getPageTitle(int position){
        return mCourses.get(position).getName();
    }
}
