package com.jredu.tk.activity;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.jredu.tk.R;
import com.jredu.tk.fragment.KnowledgePointPracticeFragment;
import com.jredu.tk.fragment.KnowledgePointPracticeFragment2;

public class KnowledgePointPracticeActivity extends AppCompatActivity {
    private static final String TAG=KnowledgePointPracticeFragment.class.getSimpleName();

    KnowledgePointPracticeFragment mKnowledgePointPracticeFragment;
    KnowledgePointPracticeFragment2 mKnowledgePointPracticeFragment2;
    RadioGroup radioGroup;
    private ImageView goBack;
    private FragmentTransaction ft;

    private String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowledge_point_practice);
        type=getIntent().getStringExtra("type");
        initData();
        initFragment();
        onClick();
    }

    /**
     * 初始化数据
     */
    public void initData(){
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        goBack=(ImageView)findViewById(R.id.go_back);
        mKnowledgePointPracticeFragment = new KnowledgePointPracticeFragment();
        mKnowledgePointPracticeFragment2 = new KnowledgePointPracticeFragment2();

        mKnowledgePointPracticeFragment.setType(type);

        ft = getSupportFragmentManager().beginTransaction();
    }


    /**
     * 初始化fragment
     */
    private void initFragment() {
        /**
         * 向布局中增加 碎片
         */
        ft.add(R.id.container, mKnowledgePointPracticeFragment);
        ft.add(R.id.container, mKnowledgePointPracticeFragment2);
        ft.commit();//提交
        getSupportFragmentManager().beginTransaction()
                .show(mKnowledgePointPracticeFragment)
                .hide(mKnowledgePointPracticeFragment2)
                .commit();

    }


    public void onClick(){
        //销毁activity
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //切换fragment
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                switch (checkedId) {
                    case R.id.small_example:
                        fragmentTransaction.show(mKnowledgePointPracticeFragment).hide(mKnowledgePointPracticeFragment2).commit();
                        break;
                    case R.id.big_example:
                        fragmentTransaction.show(mKnowledgePointPracticeFragment2).hide(mKnowledgePointPracticeFragment).commit();
                        break;
                }
            }
        });
    }

}
