package com.jredu.tk.datamanager;




import com.jredu.tk.R;
import com.jredu.tk.entity.Course;
import com.jredu.tk.entity.Expandlable;
import com.jredu.tk.entity.GradeChoice;
import com.jredu.tk.entity.GridChoice;
import com.jredu.tk.entity.Question;
import com.jredu.tk.entity.SeniorHighExam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by 昂首天下 on 2016/11/2.
 */

public class Data {

    private static List<Expandlable> parentList;
    private static Map<Integer, List<Expandlable>> childMap;
    private static List<Question> questionList;

    public static List<Question> getQuestionList() {
        return questionList;
    }

    public static void setQuestionList(List<Question> questionList) {
        Data.questionList = questionList;
    }

    public static List<Expandlable> getParentList() {
        return parentList;
    }

    public void setParentList(List<Expandlable> parentList) {
        Data.parentList = parentList;
    }

    public static Map<Integer, List<Expandlable>> getChildMap() {
        return childMap;
    }

    public void setChildMap(Map<Integer, List<Expandlable>> childMap) {
        Data.childMap = childMap;
    }


    //初始化magridview数据
    public static List<GridChoice> initxaminationPaperGridChoice() {
        List<GridChoice> list = new ArrayList<>();
        GridChoice gridChoice = new GridChoice();
        gridChoice.setText("容易");
        gridChoice.setPic(R.mipmap.zhenti_default);
        list.add(gridChoice);
        GridChoice gridChoice2 = new GridChoice();
        gridChoice2.setText("中等");
        gridChoice2.setPic(R.mipmap.theme_moni_default);
        list.add(gridChoice2);
        GridChoice gridChoice3 = new GridChoice();
        gridChoice3.setText("困难");
        gridChoice3.setPic(R.mipmap.qimo_default);
        list.add(gridChoice3);
        return list;
    }

    //初始化 真题listview数据(第一个fragment中)
    public static List<SeniorHighExam> initSeniorHighExam() {
        List<SeniorHighExam> list = new ArrayList<>();
        SeniorHighExam seniorHighExam = new SeniorHighExam("2016年山东泰安中考真题");
        list.add(seniorHighExam);
        SeniorHighExam seniorHighExam2 = new SeniorHighExam("2015年山东临沂中考真题");
        list.add(seniorHighExam2);
        SeniorHighExam seniorHighExam4 = new SeniorHighExam("2015年山东泰安中考真题");
        list.add(seniorHighExam4);
        return list;
    }

    //初始真题listview数据（分组的）
    public static List<List<String>> initOleExamListItem() {
        List<String> list = null;
        List<String> groupkey = new ArrayList<String>();
        List<String> aList = new ArrayList<String>();
        List<String> bList = new ArrayList<String>();
        List<List<String>> lists = new ArrayList<>();
        list = new ArrayList<String>();
        groupkey.add("模拟");
        groupkey.add("真题");
        aList=imitate();
        bList=examination();
        list.add("模拟");
        list.addAll(aList);
        list.add("真题");
        list.addAll(bList);
        lists.add(list);
        lists.add(groupkey);
        return lists;
    }

    //初始化年级
    public static List<GradeChoice> initGradeChoice() {
        List<GradeChoice> list = new ArrayList<>();
        GradeChoice gradeChoice = new GradeChoice("六年级上");
        list.add(gradeChoice);
        GradeChoice gradeChoice2 = new GradeChoice("六年级下");
        list.add(gradeChoice2);
        GradeChoice gradeChoice3 = new GradeChoice("七年级上");
        list.add(gradeChoice3);
        GradeChoice gradeChoice4 = new GradeChoice("七年级下");
        list.add(gradeChoice4);
        GradeChoice gradeChoice5 = new GradeChoice("八年级上");
        list.add(gradeChoice5);
        GradeChoice gradeChoice6 = new GradeChoice("八年级下");
        list.add(gradeChoice6);
        GradeChoice gradeChoice7 = new GradeChoice("九年级上");
        list.add(gradeChoice7);
        GradeChoice gradeChoice8 = new GradeChoice("九年级下");
        list.add(gradeChoice8);
        return list;
    }

    //初始化练习题（ExpandlableListView）
    // 初始化数据
    public static List<Expandlable> initExpandlableParentData() {
        final List<Expandlable> parent = new ArrayList<Expandlable>();
        Expandlable expandlable = new Expandlable("集逻辑用语", "0", "1504", R.mipmap.arrow_down);
        parent.add(expandlable);
        Expandlable expandlable2 = new Expandlable("集合与常用逻辑用语", "0", "1504", R.mipmap.arrow_down);
        parent.add(expandlable2);
        Expandlable expandlable3 = new Expandlable("集合与常用逻辑用语", "0", "1504", R.mipmap.arrow_down);
        parent.add(expandlable3);
        return parent;
    }

    public static Map<Integer, List<Expandlable>> initExpandlableChileData() {
        Map<Integer, List<Expandlable>> map = new HashMap<>();
        List<Expandlable> list1 = new ArrayList<>();
        Expandlable expandlable7 = new Expandlable("集合之间的关系与运算", "0", "806", R.mipmap.arrow_down);
        list1.add(expandlable7);
        Expandlable expandlable8 = new Expandlable("集合之间的关系与运算", "0", "806", R.mipmap.arrow_down);
        list1.add(expandlable8);
        Expandlable expandlable9 = new Expandlable("集合之间的关系与运算", "0", "806", R.mipmap.arrow_down);
        list1.add(expandlable9);
        map.put(0, list1);

        List<Expandlable> list2 = new ArrayList<>();
        list2.add(expandlable7);
        list2.add(expandlable8);
        list2.add(expandlable8);
        map.put(1, list2);

        List<Expandlable> list3 = new ArrayList<>();
        list3.add(expandlable7);
        list3.add(expandlable8);
        list3.add(expandlable8);
        map.put(2, list3);
        return map;
    }

    public static List<Expandlable> initExpandlableParentData2() {
        List<Expandlable> parent = new ArrayList<Expandlable>();
        Expandlable expandlable = new Expandlable("集逻辑用语右", "0", "1504", R.mipmap.arrow_down);
        parent.add(expandlable);
        Expandlable expandlable2 = new Expandlable("集合与常用逻辑用语右", "0", "1504", R.mipmap.arrow_down);
        parent.add(expandlable2);
        Expandlable expandlable3 = new Expandlable("集合与常用逻辑用语右", "0", "1504", R.mipmap.arrow_down);
        parent.add(expandlable3);
        return parent;
    }

    public static Map<Integer, List<Expandlable>> initExpandlableChileData2() {
        Map<Integer, List<Expandlable>> map = new HashMap<>();
        List<Expandlable> list1 = new ArrayList<>();
        Expandlable expandlable7 = new Expandlable("集合之间的关系与运算右", "0", "806", R.mipmap.arrow_down);
        list1.add(expandlable7);
        Expandlable expandlable8 = new Expandlable("集合之间的关系与运算右", "0", "806", R.mipmap.arrow_down);
        list1.add(expandlable8);
        Expandlable expandlable9 = new Expandlable("集合之间的关系与运算右", "0", "806", R.mipmap.arrow_down);
        list1.add(expandlable9);
        map.put(0, list1);

        List<Expandlable> list2 = new ArrayList<>();
        list2.add(expandlable7);
        list2.add(expandlable8);
        list2.add(expandlable8);
        map.put(1, list2);

        List<Expandlable> list3 = new ArrayList<>();
        list3.add(expandlable7);
        list3.add(expandlable8);
        list3.add(expandlable8);
        map.put(2, list3);
        return map;
    }

    public static List<Course> initTabsLayout(){
        List<Course> list=new ArrayList<>();
        Course course=new Course(1, "语文", "描述", 1);
        list.add(course);
        Course course2=new Course(1, "数学", "描述", 1);
        list.add(course2);
        Course course3=new Course(1, "英语", "描述",1);
        list.add(course3);
        Course course4=new Course(1, "物理", "描述", 1);
        list.add(course4);
        Course course5=new Course(1, "化学", "描述", 1);
        list.add(course5);
        Course course6=new Course(1, "生物", "描述", 1);
        list.add(course6);
        Course course7=new Course(1, "政治", "描述", 1);
        list.add(course7);
        Course course8=new Course(1, "历史", "描述", 1);
        list.add(course8);
        Course course9=new Course(1, "地理", "描述", 1);
        list.add(course9);
        return list;
    }

    public static List<String> imitate(){
        List<String> alist=new ArrayList<>();
        alist.add("2016山东临沂一中模拟");
        alist.add("2016山东聊城一中模拟");
        alist.add("2016山东枣庄一中模拟");
        alist.add("2016山东东营一中模拟");
        alist.add("2016山东平邑一中模拟");
        alist.add("2016山东淄博一中模拟");
        alist.add("2016山东烟台一中模拟");
        alist.add("2016山东菏泽一中模拟");
        alist.add("2016山东济宁一中模拟");
        alist.add("2016山东济南一中模拟");
        return alist;
    }

    public static List<String> examination(){
        List<String> alist=new ArrayList<>();
        alist.add("2016山东临沂中考");
        alist.add("2016山东聊城中考");
        alist.add("2016山东枣庄中考");
        alist.add("2016山东东营中考");
        alist.add("2016山东泰安中考");
        alist.add("2016山东淄博中考");
        alist.add("2016山东烟台中考");
        alist.add("2016山东菏泽中考");
        alist.add("2016山东济宁中考");
        alist.add("2016山东济南中考");
        return alist;
    }
}
