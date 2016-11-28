package com.jredu.tk.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.jredu.tk.R;
import com.jredu.tk.dao.UserDao;
import com.jredu.tk.entity.User;
import com.jredu.tk.help.ActivityManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 进入动画Activity
 * by:du
 */
public class SplashActivity extends AppCompatActivity {
    //3秒后进入
    Handler mHandler = new Handler();
    //背景
    private RelativeLayout rl_splash;
    //动画效果
    private Animation anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ActivityManager.getInstance().addActivity(this);
        rl_splash = (RelativeLayout) findViewById(R.id.rl_splash);
        rl_splash.setBackgroundResource(R.mipmap.splash);
        anim = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.splash);
        anim.setFillAfter(true);
        rl_splash.startAnimation(anim);
//        rl_splash.setAnimation(R.style);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                UserDao dao = new UserDao(SplashActivity.this);
                List<User> list = new ArrayList<>();
                list.addAll(dao.findUser());
                User user = null;
                //判断本地是否有用户登录过，并且判断是不是按退出键退出的
                if (list != null) {
                    for (User u : list) {
                        //是否登录过
                        user = u;
                        if (user.isLogin()) {
                            break;
                        }
                    }
                } else {
                    toLogin();
                }
                if (user != null) {
                    if (!user.isLogin()) {
                        toLogin();
                    } else {
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        intent.putExtra("user", user);
                        startActivity(intent);

                    }
                } else {
                    toLogin();
                }
                finish();
            }
        }, 5 * 1000);
        setFullScreen();
    }

    private void toLogin() {
        //跳转到登陆界面
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getInstance().removeActivity(this);
    }
    /**
     * 设置为全屏显示
     */
    private void setFullScreen() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
