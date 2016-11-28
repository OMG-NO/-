package com.jredu.tk.fragment;


import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.jredu.tk.R;
import com.jredu.tk.adapter.MainRgAdapter;
import com.jredu.tk.control.GetFragmentContent;
import com.jredu.tk.dao.UserDao;
import com.jredu.tk.entity.Course;
import com.jredu.tk.entity.PracticeContent;
import com.jredu.tk.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * 主界面
 * by:du
 */
public class MainFragment extends Fragment {
    private static final String TAG=MainFragment.class.getSimpleName();

    /**
     * 用户请求的关于当前显示的信息  刁宏彬  所有跟这个有关的,让我来改
     */
    private PracticeContent practiceContent;


    //用户
    private User user;
    //用户操作Dao
    private UserDao dao;
    //视图v
    private View v;
    //练习,试卷模拟
    private TabLayout tabs;
    //fragment的容器
    private ViewPager vp;
    //fragment的Adapter
    private MainRgAdapter mainRgAdapter;
    //fragment的mData
    private List<Fragment> mData;
    //标题
    private TextView tv_course_name;
    //PopupWindow显示与隐藏的图片按钮
    private ImageView img_upOrDown;
    //判断PopupWindow显示或隐藏
    private boolean flag = false;
    int f = 0;
    //旋转动画
    private Animation anim;
    //弹窗
    PopupWindow popupWindow;
    //显示PopupWindow中RadioButton的text:高考，高考同步
    private TextView tv_course_type;
    //pop中RadioButton
    private RadioButton rb_pop_left;
    private RadioButton rb_pop_right;
    //选择PopupWindow中题库类型时处罚的接口
    private MainFragmentListener mainFragmentListener;

    public void setMainFragmentListener(MainFragmentListener mainFragmentListener) {
        this.mainFragmentListener = mainFragmentListener;
    }

    public MainFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (v == null) {
            v = inflater.inflate(R.layout.fragment_main, container, false);
            vp = (ViewPager) v.findViewById(R.id.vp);
            tv_course_name = (TextView) v.findViewById(R.id.tv_course_name);
            tabs = (TabLayout) v.findViewById(R.id.tabs);
            img_upOrDown = (ImageView) v.findViewById(R.id.img_upOrDown);
            tv_course_type = (TextView) v.findViewById(R.id.tv_course_type);

//            showBar=(ImageView)v.findViewById(R.id.showBar);
//            showBar.setVisibility(View.INVISIBLE);

            getFragmentContent("语文");

            initFragment();
            mainRgAdapter = new MainRgAdapter(getChildFragmentManager(), mData);
            vp.setAdapter(mainRgAdapter);
            tabs.setupWithViewPager(vp);
            tabs.setTabMode(TabLayout.MODE_FIXED);

            img_upOrDown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!flag && f % 2 == 0) {
                        flag = true;
                        popAnimDown();
                        popShow(view);
                    }
                    f++;
                }
            });
            init();
        }
        return v;
    }

    //初始化页面信息
    private void init() {
        dao = new UserDao(getActivity());
        if (dao.findUser().size() != 0) {
            user = dao.findUser().get(0);
            tv_course_type.setText(user.getQuestionBank());
        }
    }

    private boolean isFirstenter=true;
    //取得页面中将要显示的信息 包括主页面推荐知识点 还有试题部分
    private void getFragmentContent(String course){
        GetFragmentContent getFragmentContent = new GetFragmentContent();
        getFragmentContent.getPracticeContent(getContext(),course);
        getFragmentContent.setResponseListener(new GetFragmentContent.getResponseListener() {
            @Override
            public void getResponseSuccess(PracticeContent result) {
                practiceContent=result;
                Log.i("页面将要显示的数据",practiceContent.getPointList().get(0));
                if (practiceFragment==null){
                    Log.i(TAG,"初始化Fragment");
                    initFragmentViewPager();
                    isFirstenter=false;
                }
                setFragmentContent();
            }

            @Override
            public void getResponseError() {

            }
        });
    }
    //创建Fragment的ViewPager
    private void initFragmentViewPager(){
        //创建ViewPager部分
        initFragment();
        mainRgAdapter = new MainRgAdapter(getChildFragmentManager(), mData);
        vp.setAdapter(mainRgAdapter);
        tabs.setupWithViewPager(vp);
        tabs.setTabMode(TabLayout.MODE_FIXED);
    }

    private void setFragmentContent(){
        practiceFragment.setRecommendTypeList(practiceContent);
    }


    //pop弹窗
    private void popShow(View view) {

        View v = getActivity().getLayoutInflater().inflate(R.layout.main_pop, null);
        popupWindow = new PopupWindow(v,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT, true);
        popupWindow.setAnimationStyle(R.style.main_pop);
        popupWindow.setFocusable(false);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x55000000));
        popupWindow.showAsDropDown(view, 0, -5, Gravity.NO_GRAVITY);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                popAnimUp();
                flag = false;
            }
        });
        /**
         * popupWindow中的点击事件
         */
        //点击阴影处pop消失
        LinearLayout close = (LinearLayout) v.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                f = 0;
            }
        });
        RadioGroup radioGroup = (RadioGroup) v.findViewById(R.id.rg_pop);
        rb_pop_left = (RadioButton) v.findViewById(R.id.rb_pop_left);
        rb_pop_right = (RadioButton) v.findViewById(R.id.rb_pop_right);
        if (rb_pop_left.getText().equals(tv_course_type.getText())) {
            rb_pop_left.setChecked(true);
        } else {
            rb_pop_right.setChecked(true);
        }
        //PopupWindow中高考、高中同步
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_pop_left:
                        //高考
                        tv_course_type.setText(rb_pop_left.getText());
                        break;
                    case R.id.rb_pop_right:
                        //高中同步
                        tv_course_type.setText(rb_pop_right.getText());
                        break;
                }
                mainFragmentListener.
                        changeQuestionBank(tv_course_type.getText().toString());
                popupWindow.dismiss();
                f = 0;
            }
        });
    }

    //pop动画
    private void popAnimUp() {
        anim = AnimationUtils.loadAnimation(getActivity(), R.anim.main_anim_up);
        anim.setFillAfter(true);
        img_upOrDown.startAnimation(anim);
    }

    private void popAnimDown() {
        anim = AnimationUtils.loadAnimation(getActivity(), R.anim.main_anim_down);
        anim.setFillAfter(true);
        img_upOrDown.startAnimation(anim);
    }

    //练习页面和试题页面
    PracticeFragment practiceFragment;
    ExaminationPaperImitateFragment testFragment;
    //viewPager and RadioGroup 对应的fragment
    //mData的初始化
    private void initFragment() {
        mData = new ArrayList<>();

        practiceFragment = new PracticeFragment();
        testFragment = new ExaminationPaperImitateFragment();

        testFragment = new ExaminationPaperImitateFragment();

        mData.add(practiceFragment);
        mData.add(testFragment);

    }

    public void getCourse(String name) {
        //传给ExaminationPaperImitateFragment Course名称
        testFragment.getCourseName(name);
    }

    /**
     * 左侧栏点击Course时(接口)，修改标题所调用的方法
     */
    public void changeMainTitle(Course course) {
        //得到Fragment页面中要显示的内容
        getFragmentContent(course.getName());
        tv_course_name.setText(course.getName());
    }

    /**
     * 从我的界面返回修改题库类型
     */
    public void changeMainType(String type) {
        tv_course_type.setText(type);
    }

    //PopupWindow修改题库类型
    public interface MainFragmentListener {
        void changeQuestionBank(String questionBank);
    }
}
