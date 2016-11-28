package com.jredu.tk.fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jredu.tk.R;
import com.jredu.tk.activity.AbilityAnalysisActivity;
import com.jredu.tk.activity.MineActivity;
import com.jredu.tk.adapter.CourseAdapter;
import com.jredu.tk.dao.UserDao;
import com.jredu.tk.datamanager.CourseDataManager;
import com.jredu.tk.entity.Course;
import com.jredu.tk.entity.User;
import com.jredu.tk.help.BitmapAndString;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * 左边侧栏
 * by:du
 */
public class LeftMainFragment extends Fragment {
    //显示course
    private ListView lv;
    private CourseAdapter courseAdapter;
    //Course数据
    private List<Course> mData;
    //对外提供接口
    private LeftMainListener leftMainListener;
    //视图v
    View v;
    //头像
    private ImageView img_user_photo;
    //昵称
    private TextView tv_user_name;
    //当前登录的用户
    private User user;
    //User操作Dao
    private UserDao dao;
    //能力测评
    private TextView tv_ability;
    //答题记录
    private RelativeLayout rl_result;
    //答题结果
    private TextView tv_result_num;

    public void setLeftMainListener(LeftMainListener leftMainListener) {
        this.leftMainListener = leftMainListener;
    }

    public LeftMainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (v == null) {
            v = inflater.inflate(R.layout.fragment_left_main, container, false);
            dao = new UserDao(getActivity());
            mData = new ArrayList<>();
            user = getActivity().getIntent().getParcelableExtra("user");
            initData();
            lv = (ListView) v.findViewById(R.id.lv);
            courseAdapter = new CourseAdapter(getActivity(), mData);
            lv.setAdapter(courseAdapter);
            img_user_photo = (ImageView) v.findViewById(R.id.img_user_photo);
            tv_user_name = (TextView) v.findViewById(R.id.tv_user_name);
            tv_ability = (TextView) v.findViewById(R.id.tv_ability);
            rl_result = (RelativeLayout) v.findViewById(R.id.rl_result);
            tv_result_num = (TextView) v.findViewById(R.id.tv_result_num);
            if (dao.findUser().size() == 0) {
                //预防万一，预防崩溃
                initUser();
            } else {
                tv_user_name.setText(user.getName());
                //判断本地数据库有没有用户登录
                if (user.getPhoto() != null) {
                    if (user.isChangePhoto()) {
                        //判断是不是登录界面转过来的，是就下载图片
                        Picasso.with(getActivity())
                                .load(user.getPhoto())
                                .placeholder(R.mipmap.ic_launcher)
                                .error(R.mipmap.ic_launcher)
                                .into(img_user_photo);
                    } else {
                        img_user_photo.setImageBitmap(BitmapAndString.stringToBitmap(user.getPhoto()));
                    }
                }
            }
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(getActivity(), mData.get(i).getName(), Toast.LENGTH_SHORT).show();
                    leftMainListener.showCourse(mData.get(i));

                }
            });
            img_user_photo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), MineActivity.class);
                    intent.putExtra("user", user);
                    startActivityForResult(intent, 1);
                    leftMainListener.closeLeft();
                }
            });

            tv_ability.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //能力测评
                    Intent intent = new Intent(getActivity(), AbilityAnalysisActivity.class);
                    startActivity(intent);
                }
            });
            rl_result.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //答题记录
                    tv_result_num.setText("");
                }
            });
            lv.setSelection(0);
        }
        return v;
    }

    private void initUser() {
        user = new User();
        user.setName(tv_user_name.getText().toString());
        user.setPhoto(BitmapAndString.bitmapToString(((BitmapDrawable) (img_user_photo).getDrawable()).getBitmap()));
        user.setPlace("山东");
        user.setQuestionBank("高考");
        user.setSex("男");
        user.setType("理科");
        user.setYear("2017");
        user.setChangePhoto(false);
    }

    private void initData() {
        //加载课程数据
        if (user.getType() != null) {
            if (user.getType().equals("理科")) {
                mData.clear();
                mData.addAll(new CourseDataManager().initDataLi());
            } else if (user.getType().equals("文科")) {
                {
                    mData.clear();
                    mData.addAll(new CourseDataManager().initDataWen());
                }
            } else {
                mData.clear();
                mData.addAll(new CourseDataManager().initDataNo());
            }
        } else {
            mData.clear();
            mData.addAll(new CourseDataManager().initDataNo());
        }
    }

    //从我的界面返回主界面时调用此方法刷新
    private void refreshCourseData() {
        initData();
        courseAdapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //MineActivity的返回结果
        if (requestCode == 1 && resultCode == 1) {
            user = data.getParcelableExtra("user");
            if (dao.findUserByAccount(user.getAccount()) == null) {
                dao.addUser(user);
            } else {
                dao.updateUser(user);
            }
            String name = user.getName();
            //考生类型
            String type = user.getType();
            //题库
            String questionBank = user.getQuestionBank();
            if (!user.isChangePhoto()) {
                Bitmap photo = BitmapAndString.stringToBitmap(user.getPhoto());
                img_user_photo.setImageBitmap(photo);
            }
            tv_user_name.setText(name);
            leftMainListener.mineResult(questionBank);
            //刷新CourseData
            refreshCourseData();
        }
    }

    //MainFragment中PopupWindow修改题库类型时调用
    public void changeQuestionBank(String questionBank) {
        user.setQuestionBank(questionBank);
    }

    // TODO: 2016/11/11  精简化接口,这里杜贤贤添加了三个借口类
    //MineActivity选择的数据对外提供接口
        public interface LeftMainListener {
            //MineActivity选择的数据对外提供接口
            void mineResult(String questionBank);

            //选择课程对外接口
            void showCourse(Course course);

            //点击头像跳转到我的界面的接口，关闭侧栏
            void closeLeft();
        }
}
