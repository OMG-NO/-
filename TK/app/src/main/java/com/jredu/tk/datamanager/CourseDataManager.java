package com.jredu.tk.datamanager;

import com.jredu.tk.R;
import com.jredu.tk.entity.Course;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/2.
 */
public class CourseDataManager {
    public List<Course> list;

    public List<Course> initDataLi() {
        list = new ArrayList<>();
        list.add(new Course(1, "语文", R.mipmap.theme_history_icon_cid_1));
        list.add(new Course(2, "数学", R.mipmap.theme_history_icon_cid_2));
        list.add(new Course(3, "英语", R.mipmap.theme_history_icon_cid_4));
        list.add(new Course(4, "物理", R.mipmap.theme_history_icon_cid_5));
        list.add(new Course(5, "化学", R.mipmap.theme_history_icon_cid_6));
        list.add(new Course(6, "生物", R.mipmap.theme_history_icon_cid_10));
        return list;
    }

    public List<Course> initDataWen() {
        list = new ArrayList<>();
        list.add(new Course(1, "语文", R.mipmap.theme_history_icon_cid_1));
        list.add(new Course(2, "数学", R.mipmap.theme_history_icon_cid_2));
        list.add(new Course(3, "英语", R.mipmap.theme_history_icon_cid_4));
        list.add(new Course(4, "历史", R.mipmap.theme_history_icon_cid_7));
        list.add(new Course(5, "政治", R.mipmap.theme_history_icon_cid_8));
        list.add(new Course(6, "地理", R.mipmap.theme_history_icon_cid_9));
        return list;
    }

    public List<Course> initDataNo() {
        list = new ArrayList<>();
        list.add(new Course(1, "语文", R.mipmap.theme_history_icon_cid_1));
        list.add(new Course(2, "数学", R.mipmap.theme_history_icon_cid_2));
        list.add(new Course(3, "英语", R.mipmap.theme_history_icon_cid_4));
        list.add(new Course(4, "政治", R.mipmap.theme_history_icon_cid_8));
        list.add(new Course(5, "历史", R.mipmap.theme_history_icon_cid_7));
        list.add(new Course(6, "地理", R.mipmap.theme_history_icon_cid_9));
        list.add(new Course(7, "物理", R.mipmap.theme_history_icon_cid_5));
        list.add(new Course(8, "化学", R.mipmap.theme_history_icon_cid_6));
        list.add(new Course(9, "生物", R.mipmap.theme_history_icon_cid_10));
        return list;
    }
}
