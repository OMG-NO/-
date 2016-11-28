package com.jredu.tk.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jredu.tk.R;
import com.jredu.tk.dao.UserDao;
import com.jredu.tk.entity.User;
import com.jredu.tk.help.ActivityManager;
import com.jredu.tk.help.BitmapAndString;
import com.jredu.tk.help.HeadZoomScrollView;
import com.squareup.picasso.Picasso;

import java.io.File;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

import static com.jredu.tk.R.id.btn_cancel;

/**
 * 我的界面
 * by:du
 */
public class MineActivity extends SwipeBackActivity implements View.OnClickListener {

    //返回
    private ImageView img_back;
    //上左右拉删除服务
    private SwipeBackLayout mSwipeBackLayout;
    //头像下的昵称
    private TextView tv_nickname;
    //昵称
    private RelativeLayout rl_nickname;
    //显示的昵称
    private TextView rl_tv_nickname;
    //性别
    private RelativeLayout rl_sex;
    //显示的性别
    private TextView rl_tv_sex;
    //题库
    private RelativeLayout rl_questionBank;
    //显示的题库
    private TextView rl_tv_questionBank;
    //参加年份
    private RelativeLayout rl_testYear;
    //显示的年份
    private TextView rl_tv_testYear;
    //参加地区
    private RelativeLayout rl_testPlace;
    //显示的地区
    private TextView rl_tv_testPlace;
    //考生类型
    private RelativeLayout rl_stuType;
    //显示的考生类型
    private TextView rl_tv_stuType;
    //吐槽
    private RelativeLayout rl_complain;
    //关于我们
    private RelativeLayout rl_about;
    //更新提示
    private RelativeLayout rl_update;
    //弹窗
    private PopupWindow popupWindow;
    //当前用户
    private User user;
    //ScrollView，下拉图片放大
    private HeadZoomScrollView scrollView;
    //放大的View：FrameLayout
    private FrameLayout zoom;
    //头像
    private ImageView img_user_photo;
    /* 头像文件 */
    private static final String IMAGE_FILE_NAME = "temp_head_image.jpg";

    /* 请求识别码 */
    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;

    // 裁剪后图片的宽(X)和高(Y),480 X 480的正方形。
    private static int output_X = 480;
    private static int output_Y = 480;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);
        ActivityManager.getInstance().addActivity(this);
        //设置滑动方向
        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeSize(200);
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        zoom=(FrameLayout)findViewById(R.id.zoom);
        scrollView=(HeadZoomScrollView)findViewById(R.id.scrollView);
        scrollView.setOnScrollListener(new HeadZoomScrollView.OnScrollListener() {
            @Override
            public void onScroll(int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                scrollView.setZoomView(zoom);
            }
        });
        tv_nickname = (TextView) findViewById(R.id.tv_nickname);
        img_back = (ImageView) findViewById(R.id.img_back);
        rl_nickname = (RelativeLayout) findViewById(R.id.rl_nickname);
        rl_tv_nickname = (TextView) findViewById(R.id.rl_tv_nickname);
        rl_sex = (RelativeLayout) findViewById(R.id.rl_sex);
        rl_tv_sex = (TextView) findViewById(R.id.rl_tv_sex);
        rl_questionBank = (RelativeLayout) findViewById(R.id.rl_questionBank);
        rl_tv_questionBank = (TextView) findViewById(R.id.rl_tv_questionBank);
        rl_testYear = (RelativeLayout) findViewById(R.id.rl_testYear);
        rl_tv_testYear = (TextView) findViewById(R.id.rl_tv_testYear);
        rl_testPlace = (RelativeLayout) findViewById(R.id.rl_testPlace);
        rl_tv_testPlace = (TextView) findViewById(R.id.rl_tv_testPlace);
        rl_stuType = (RelativeLayout) findViewById(R.id.rl_stuType);
        rl_tv_stuType = (TextView) findViewById(R.id.rl_tv_stuType);
        rl_complain = (RelativeLayout) findViewById(R.id.rl_complain);
        rl_about = (RelativeLayout) findViewById(R.id.rl_about);
        rl_update = (RelativeLayout) findViewById(R.id.rl_update);
        img_user_photo = (ImageView) findViewById(R.id.img_user_photo);
//数据初始化
        init();

        img_back.setOnClickListener(this);
        rl_nickname.setOnClickListener(this);
        rl_sex.setOnClickListener(this);
        rl_questionBank.setOnClickListener(this);
        rl_testYear.setOnClickListener(this);
        rl_testPlace.setOnClickListener(this);
        rl_stuType.setOnClickListener(this);
        rl_complain.setOnClickListener(this);
        rl_about.setOnClickListener(this);
        rl_update.setOnClickListener(this);
        img_user_photo.setOnClickListener(this);
        (findViewById(btn_cancel)).setOnClickListener(this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getInstance().removeActivity(this);
    }

    //进入时的初始化
    private void init() {
        user = getIntent().getParcelableExtra("user");
        if (user.isChangePhoto()){
            //判断是不是登录界面转过来的，是就下载图片
            Picasso.with(this)
                    .load(user.getPhoto())
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(img_user_photo);
        }else {
            img_user_photo.setImageBitmap(BitmapAndString.stringToBitmap(user.getPhoto()));
        }
        changeName(user.getName());
        if (user.getPlace() != null) {
            rl_tv_testPlace.setText(user.getPlace());
        }
        if (user.getSex() != null) {
            rl_tv_sex.setText(user.getSex());
        }
        if (user.getType() != null) {
            rl_tv_stuType.setText(user.getType());
        }
        if (user.getYear() != null) {
            rl_tv_testYear.setText(user.getYear());
        }
        if (user.getQuestionBank()!=null){
            rl_tv_questionBank.setText(user.getQuestionBank());
        }
    }

    //修改昵称
    private void changeName(String name) {
        rl_tv_nickname.setText(name);
        tv_nickname.setText(name);
    }

    //PopupWindow修改成功时触发的事件
    private void changeSuccess() {
        popupWindow.dismiss();
        Toast.makeText(MineActivity.this, "修改成功！", Toast.LENGTH_SHORT).show();
    }

    //点击事件
    //pop：昵称
    private EditText et_nickname;
    //性别：
    private RadioButton man;
    private RadioButton women;
    //题库
    private RadioButton rb_question_left;
    private RadioButton rb_question_right;
    //参加高考年份
    private RadioButton rb_2017;
    private RadioButton rb_2018;
    private RadioButton rb_2019;
    //文理科
    private RadioButton rb_wen;
    private RadioButton rb_li;
    private RadioButton rb_no;

    @Override
    public void onClick(View view) {
        View v;
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.rl_nickname:
                v = this.getLayoutInflater().inflate(R.layout.mine_nickname_pop, null);
                popupWindow = new PopupWindow(v,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT, true);
                popupWindow.setAnimationStyle(R.style.mine_pop);
                popupWindow.setFocusable(true);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setBackgroundDrawable(new ColorDrawable(0x55000000));
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
                et_nickname = (EditText) v.findViewById(R.id.et_nickname);
                et_nickname.setText(rl_tv_nickname.getText());
                et_nickname.setSelection(et_nickname.getText().length());
                //修改按钮
                (v.findViewById(R.id.btn_ok)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (et_nickname.getText() != null && !et_nickname.equals("")) {
                            changeName(et_nickname.getText().toString());
                            changeSuccess();
                        } else {
                            Toast.makeText(MineActivity.this, "昵称不能为空！", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                (v.findViewById(btn_cancel)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();
                        Toast.makeText(MineActivity.this, "已取消！", Toast.LENGTH_SHORT).show();
                    }
                });
                (v.findViewById(R.id.rl)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();
                    }
                });
                (v.findViewById(R.id.ll)).setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        return true;
                    }
                });
                break;
            case R.id.rl_sex:
                v = this.getLayoutInflater().inflate(R.layout.mine_sex_pop, null);
                popupWindow = new PopupWindow(v,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT, true);
                popupWindow.setAnimationStyle(R.style.mine_pop);
                popupWindow.setFocusable(true);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setBackgroundDrawable(new ColorDrawable(0x55000000));
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
                man = (RadioButton) v.findViewById(R.id.rb_man);
                women = (RadioButton) v.findViewById(R.id.rb_women);
                if (man.getText().equals(rl_tv_sex.getText())) {
                    man.setChecked(true);
                } else {
                    women.setChecked(true);
                }
                ((RadioGroup) v.findViewById(R.id.rg)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        switch (i) {
                            case R.id.rb_man:
                                rl_tv_sex.setText(man.getText());
                                break;
                            case R.id.rb_women:
                                rl_tv_sex.setText(women.getText());
                                break;
                        }
                        changeSuccess();
                    }
                });
                (v.findViewById(R.id.rl)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();
                    }
                });
                (v.findViewById(R.id.ll)).setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        return true;
                    }
                });
                break;
            case R.id.rl_questionBank:
                v = this.getLayoutInflater().inflate(R.layout.mine_questionbank_pop, null);
                popupWindow = new PopupWindow(v,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT, true);
                popupWindow.setAnimationStyle(R.style.mine_pop);
                popupWindow.setFocusable(true);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setBackgroundDrawable(new ColorDrawable(0x55000000));
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
                rb_question_left = (RadioButton) v.findViewById(R.id.rb_question_left);
                rb_question_right = (RadioButton) v.findViewById(R.id.rb_question_right);
                if (rb_question_left.getText().equals(rl_tv_questionBank.getText())) {
                    rb_question_left.setChecked(true);
                } else {
                    rb_question_right.setChecked(true);
                }
                ((RadioGroup) v.findViewById(R.id.rg)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        switch (i) {
                            case R.id.rb_question_left:
                                rl_tv_questionBank.setText(rb_question_left.getText());
                                break;
                            case R.id.rb_question_right:
                                rl_tv_questionBank.setText(rb_question_right.getText());
                                break;
                        }
                        changeSuccess();
                    }
                });
                (v.findViewById(R.id.rl)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();
                    }
                });
                (v.findViewById(R.id.ll)).setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        return true;
                    }
                });
                break;
            case R.id.rl_testYear:
                v = this.getLayoutInflater().inflate(R.layout.mine_year_pop, null);
                popupWindow = new PopupWindow(v,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT, true);
                popupWindow.setAnimationStyle(R.style.mine_pop);
                popupWindow.setFocusable(true);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setBackgroundDrawable(new ColorDrawable(0x55000000));
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
                rb_2017 = (RadioButton) v.findViewById(R.id.rb_2017);
                rb_2018 = (RadioButton) v.findViewById(R.id.rb_2018);
                rb_2019 = (RadioButton) v.findViewById(R.id.rb_2019);
                if (rb_2017.getText().equals(rl_tv_testYear.getText())) {
                    rb_2017.setChecked(true);
                } else if (rb_2018.getText().equals(rl_tv_testYear.getText())) {
                    rb_2018.setChecked(true);
                } else {
                    rb_2019.setChecked(true);
                }
                ((RadioGroup) v.findViewById(R.id.rg)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        switch (i) {
                            case R.id.rb_2017:
                                rl_tv_testYear.setText(rb_2017.getText());
                                break;
                            case R.id.rb_2018:
                                rl_tv_testYear.setText(rb_2018.getText());
                                break;
                            case R.id.rb_2019:
                                rl_tv_testYear.setText(rb_2019.getText());
                                break;
                        }
                        changeSuccess();
                    }
                });
                (v.findViewById(R.id.rl)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();
                    }
                });
                (v.findViewById(R.id.ll)).setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        return true;
                    }
                });
                break;
            case R.id.rl_testPlace:
                Intent place = new Intent(MineActivity.this, PlaceActivity.class);
                place.putExtra("currentPlace", rl_tv_testPlace.getText());
                startActivityForResult(place, 2);
                break;
            case R.id.rl_stuType:
                v = this.getLayoutInflater().inflate(R.layout.mine_type_pop, null);
                popupWindow = new PopupWindow(v,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT, true);
                popupWindow.setAnimationStyle(R.style.mine_pop);
                popupWindow.setFocusable(true);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setBackgroundDrawable(new ColorDrawable(0x55000000));
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
                rb_wen = (RadioButton) v.findViewById(R.id.rb_wen);
                rb_li = (RadioButton) v.findViewById(R.id.rb_li);
                rb_no = (RadioButton) v.findViewById(R.id.rb_no);
                if (rb_wen.getText().equals(rl_tv_stuType.getText())) {
                    rb_wen.setChecked(true);
                } else if (rb_li.getText().equals(rl_tv_stuType.getText())) {
                    rb_li.setChecked(true);
                } else {
                    rb_no.setChecked(true);
                }
                ((RadioGroup) v.findViewById(R.id.rg)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        switch (i) {
                            case R.id.rb_wen:
                                rl_tv_stuType.setText(rb_wen.getText());
                                break;
                            case R.id.rb_li:
                                rl_tv_stuType.setText(rb_li.getText());
                                break;
                            case R.id.rb_no:
                                rl_tv_stuType.setText(rb_no.getText());
                                break;
                        }
                        changeSuccess();
                    }
                });
                (v.findViewById(R.id.rl)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();
                    }
                });
                (v.findViewById(R.id.ll)).setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        return true;
                    }
                });
                break;
            case R.id.rl_complain:
                Intent complain = new Intent(MineActivity.this, ComplainActivity.class);
                complain.putExtra("user",user);
                startActivity(complain);
                break;
            case R.id.rl_about:
                Intent about=new Intent(MineActivity.this,AboutUsActivity.class);
                startActivity(about);
                break;
            case R.id.rl_update:
                Toast.makeText(MineActivity.this, "暂无更新", Toast.LENGTH_SHORT).show();
                break;
            case R.id.img_user_photo:
                choseHeadImageFromGallery();
                break;
            case R.id.btn_cancel:
                //退出时更新用户信息
                initUser();
                user.setLogin(false);
                UserDao dao=new UserDao(MineActivity.this);
                dao.updateUser(user);
                Intent intent=new Intent(MineActivity.this,LoginActivity.class);
                startActivity(intent);
                ActivityManager.getInstance().exit();
                break;
        }
    }

    // 从本地相册选取图片作为头像
    private void choseHeadImageFromGallery() {
        Intent intentFromGallery = new Intent();
        // 设置文件类型
        intentFromGallery.setType("image/*");
        intentFromGallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intentFromGallery, CODE_GALLERY_REQUEST);
    }

    /**
     * 裁剪原始的图片
     */
    public void cropRawPhoto(Uri uri) {

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");

        // 设置裁剪
        intent.putExtra("crop", "true");

        // aspectX , aspectY :宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        // outputX , outputY : 裁剪图片宽高
        intent.putExtra("outputX", output_X);
        intent.putExtra("outputY", output_Y);
        intent.putExtra("return-data", true);

        startActivityForResult(intent, CODE_RESULT_REQUEST);
    }

    /**
     * 提取保存裁剪之后的图片数据，并设置头像部分的View
     */
    Bitmap photo = null;

    private void setImageToHeadView(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            photo = extras.getParcelable("data");
            img_user_photo.setImageBitmap(photo);
            user.setChangePhoto(false);
        }
    }

    /**
     * 检查设备是否存在SDCard的工具方法
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            // 有存储的SDCard
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent intent) {
        switch (requestCode) {
            case CODE_GALLERY_REQUEST:
                if(intent==null){
                    Toast.makeText(MineActivity.this,"头像没有修改！",Toast.LENGTH_SHORT).show();
                }else {
                    cropRawPhoto(intent.getData());
                }
            case CODE_CAMERA_REQUEST:
                if (hasSdcard()) {
                    File tempFile = new File(
                            Environment.getExternalStorageDirectory(),
                            IMAGE_FILE_NAME);
                    cropRawPhoto(Uri.fromFile(tempFile));
                } else {
                    Toast.makeText(getApplication(), "没有SDCard!", Toast.LENGTH_LONG)
                            .show();
                }

                break;
            case CODE_RESULT_REQUEST:
                if (intent != null) {
                    setImageToHeadView(intent);
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, intent);
        //选择的当前地区
        if (requestCode == 2 && resultCode == 2) {
            String place = intent.getStringExtra("place");
            rl_tv_testPlace.setText(place);
            Toast.makeText(MineActivity.this, place, Toast.LENGTH_SHORT).show();
        }
    }
    //向用户插入数据
    private void initUser(){
        user.setName(rl_tv_nickname.getText().toString());
        user.setPlace(rl_tv_testPlace.getText().toString());
        user.setQuestionBank(rl_tv_questionBank.getText().toString());
        user.setSex(rl_tv_sex.getText().toString());
        user.setType(rl_tv_stuType.getText().toString());
        user.setYear(rl_tv_testYear.getText().toString());
        if (!user.isChangePhoto()){
            user.setPhoto(BitmapAndString.bitmapToString(((BitmapDrawable)(img_user_photo).getDrawable()).getBitmap()));
        }
    }
    @Override
    public void finish() {
        //把图库类型和高考地区和考生类型传回上层
        Intent data = new Intent();
        initUser();
        data.putExtra("user", user);
        setResult(1, data);
        super.finish();
    }
}
