package com.jredu.tk.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.jredu.tk.R;
import com.jredu.tk.dao.UserDao;
import com.jredu.tk.entity.User;
import com.jredu.tk.help.ActivityManager;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 登录Activity
 * by:du
 */
public class LoginActivity extends AppCompatActivity {
    //最外层的RelativeLayout，用来设置背景
    private RelativeLayout rl_login;
    //登录QQ按钮
    private ImageView img_login;
    private Tencent mTencent;
    private String APP_ID = "1105734341";
    private IUiListener loginListener;
    private String SCOPE = "all";
    private IUiListener userInfoListener;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActivityManager.getInstance().addActivity(this);
        user = new User();
        rl_login = (RelativeLayout) findViewById(R.id.rl_login);
        rl_login.setBackgroundResource(R.mipmap.login_bg);
        img_login = (ImageView) findViewById(R.id.img_login);
        img_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initQqLogin();
                if (!mTencent.isSessionValid()) {
                    mTencent.login(LoginActivity.this, SCOPE, loginListener);
                }
            }
        });
    }

    //把Activity从Manager里删除
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getInstance().removeActivity(this);
    }

    //初始化QQ登录分享的需要的资源
    private void initQqLogin() {
        mTencent = Tencent.createInstance(APP_ID, LoginActivity.this.getApplicationContext());
        //创建QQ登录回调接口
        loginListener = new IUiListener() {
            @Override
            public void onComplete(Object o) {
                //登录成功后回调该方法
                //设置openid，如果不设置这一步的话无法获取用户信息
                JSONObject jo = (JSONObject) o;
                String openID;
                try {
                    openID = jo.getString("openid");
                    String accessToken = jo.getString("access_token");
                    String expires = jo.getString("expires_in");
                    user.setAccount(openID);
                    mTencent.setOpenId(openID);
                    mTencent.setAccessToken(accessToken, expires);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(UiError uiError) {
                //登录失败后回调该方法
                Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                Log.e("LoginError:", uiError.toString());
            }

            @Override
            public void onCancel() {
                //取消登录后回调该方法
                Toast.makeText(LoginActivity.this, "取消登录", Toast.LENGTH_SHORT).show();
            }
        };
        //获取用户信息的回调接口
        userInfoListener = new IUiListener() {
            @Override
            public void onComplete(Object o) {
                JSONObject jo = (JSONObject) o;
                Log.e("COMPLETE:", jo.toString());
                try {
                    JSONObject info = (JSONObject) o;
                    Log.e("JO:", jo.toString());
                    //昵称
                    String nickName = info.getString("nickname");
                    //头像
                    String photo = info.getString("figureurl_qq_2");
                    //性别
                    String sex = info.getString("gender");
                    UserDao userDao = new UserDao(LoginActivity.this);
                    List<User> list = new ArrayList<>();
                    list.addAll(userDao.findUser());
                    if (list != null) {
                        //判断是否存在此用户
                        boolean f = false;
                        for (User u : list) {
                            if (u.getAccount().equals(user.getAccount())) {
                                u.setLogin(true);
                                userDao.updateUser(u);
                                user = u;
                                f = true;
                                break;
                            }
                        }
                        if (!f) {
                            user.setName(nickName);
                            user.setPhoto(photo);
                            user.setSex(sex);
                            user.setChangePhoto(true);
                            user.setLogin(true);
                            userDao.addUser(user);
                        }
                    } else {
                        user.setName(nickName);
                        user.setPhoto(photo);
                        user.setSex(sex);
                        user.setChangePhoto(true);
                        user.setLogin(true);
                        userDao.addUser(user);
                    }
                    //关闭当前Activity
                    finish();
                    //跳转到主界面
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this, "hello，" + nickName, Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(UiError uiError) {

            }

            @Override
            public void onCancel() {

            }

        };

    }

    //获取用户信息
    private void getUserInfo() {
        UserInfo info = new UserInfo(this, mTencent.getQQToken());
        info.getUserInfo(userInfoListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_LOGIN) {
            if (resultCode == -1) {
                Tencent.onActivityResultData(requestCode, resultCode, data, loginListener);
                Tencent.handleResultData(data, loginListener);
                getUserInfo();
            }
        }
    }
}
