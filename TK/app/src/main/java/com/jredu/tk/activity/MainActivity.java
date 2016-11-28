package com.jredu.tk.activity;

import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.jredu.tk.R;
import com.jredu.tk.entity.Course;
import com.jredu.tk.fragment.LeftMainFragment;
import com.jredu.tk.fragment.MainFragment;
import com.jredu.tk.help.ActivityManager;
import com.jredu.tk.help.MySlidingPaneLayout;

/**
 * 主界面
 */
public class MainActivity extends AppCompatActivity {
    //右滑
    private MySlidingPaneLayout mySlidingPaneLayout;
    //侧栏
    private LinearLayout ll_left;
    //主页面
    private LinearLayout ll_main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityManager.getInstance().addActivity(this);
        mySlidingPaneLayout=(MySlidingPaneLayout)findViewById(R.id.sliding_layout);
        ll_left=(LinearLayout)findViewById(R.id.ll_left);
        ll_main=(LinearLayout)findViewById(R.id.ll_main);

        mySlidingPaneLayout.setCoveredFadeColor(0);
        mySlidingPaneLayout.setPanelSlideListener(new SlidingPaneLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                ll_left.setScaleY(slideOffset / 2 + 0.5f);
                ll_left.setScaleX(slideOffset / 2 + 0.5f);
                ll_main.setScaleY(1 - slideOffset / 5);
            }

            @Override
            public void onPanelOpened(View panel) {

            }

            @Override
            public void onPanelClosed(View panel) {
            }
        });
        final LeftMainFragment leftMainFragment=(LeftMainFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_left);
        final MainFragment mainFragment=(MainFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_main);
        leftMainFragment.setLeftMainListener(new LeftMainFragment.LeftMainListener() {
            @Override
            public void mineResult(String questionBank) {
                //修改主界面的类型
                mainFragment.changeMainType(questionBank);
            }

            @Override
            public void showCourse(Course course) {
                //修改主界面的课程
                mainFragment.changeMainTitle(course);
                mySlidingPaneLayout.closePane();
                mainFragment.getCourse(course.getName());
            }

            @Override
            public void closeLeft() {
                //点击头像跳转到我的界面的接口，关闭侧栏
                mySlidingPaneLayout.closePane();
            }
        });
        mainFragment.setMainFragmentListener(new MainFragment.MainFragmentListener() {
            @Override
            public void changeQuestionBank(String questionBank) {
                //根据MainFragment中PopupWindow修改题库类型
                leftMainFragment.changeQuestionBank(questionBank);
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getInstance().removeActivity(this);
    }
}
