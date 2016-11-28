package com.jredu.tk.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jredu.tk.R;
import com.jredu.tk.fragment.CollectionFragment;


public class CollectionActivity extends AppCompatActivity {

    CollectionFragment mCollectionFragment;
    private FragmentTransaction ft;
    private ImageView go_back;
    private TextView exam_type;

    private String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type=getIntent().getStringExtra("type");

        setContentView(R.layout.activity_collection);
        exam_type= (TextView) findViewById(R.id.exam_type);
        exam_type.setText(type);

        initData();
        initFragment();
        onClick();
    }

    public void initData(){
        go_back=(ImageView) findViewById(R.id.go_back);
        mCollectionFragment = new CollectionFragment();
        ft = getSupportFragmentManager().beginTransaction();
    }

    /**
     * 初始化fragment
     */
    private void initFragment() {
        /**
         * 向布局中增加 碎片
         */
        ft.add(R.id.collection_fragment, mCollectionFragment);
        ft.commit();//提交
        getSupportFragmentManager().beginTransaction()
                .show(mCollectionFragment)
                .commit();

    }

    public void onClick(){
        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
