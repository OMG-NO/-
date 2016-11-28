package com.jredu.tk.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.j256.ormlite.stmt.query.In;
import com.jredu.tk.R;
import com.jredu.tk.activity.CollectionActivity;
import com.jredu.tk.activity.KnowledgePointPracticeActivity;
import com.jredu.tk.adapter.Adapter_GridRecycle;
import com.jredu.tk.adapter.OnRecyclerItemClickListener;
import com.jredu.tk.control.GotoAnswerActivity;
import com.jredu.tk.entity.PracticeContent;
import com.jredu.tk.entity.RecommendType;
import com.jredu.tk.unit.PicssoImageLoader;
import com.jredu.tk.view.FullyGridLayoutManager;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by HunBing on 2016/11/2.
 * 显示练习页面
 */

public class PracticeFragment extends Fragment {
    private static final String TAG = PracticeFragment.class.getSimpleName();

    View view;
    //图片轮播
    Banner banner;
    //练习分类
    View bulb_btn, pencil_btn;
    //推荐类型列表
    RecyclerView recommend_list;

    String type;
    PracticeContent practiceContent;

    LinearLayout mistake_btn,collection_btn;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.practicefragment_view, null);

        setView();
        //设置图片轮播控件
        setBanner();
        //设置智能推荐
        setBulbPencil_btn();
        //设置推荐练习列表
        type="语文";
        setRecommend_list();
        setCollection_btnclick();

        return view;
    }

    //提取页面内的控件
    public void setView() {
        banner = (Banner) view.findViewById(R.id.banner);
        bulb_btn = view.findViewById(R.id.bulb_btn);
        pencil_btn = view.findViewById(R.id.pencil_btn);
        recommend_list = (RecyclerView) view.findViewById(R.id.recommend_recycleview);

        mistake_btn= (LinearLayout) view.findViewById(R.id.mistake_btn);
        collection_btn= (LinearLayout) view.findViewById(R.id.collection_btn);
    }


    //设置图片轮播的控件
    public void setBanner() {
        Integer[] ims = {R.drawable.pictrue1, R.drawable.pictrue2, R.drawable.pictrue3};
        List<Integer> images = new ArrayList<>();
        images.addAll(Arrays.asList(ims));
        //设置图片加载器
        banner.setImageLoader(new PicssoImageLoader());
        banner.setImages(images);
        banner.start();
    }

    //推荐类型测试数据
    List<RecommendType> recTypeList = new ArrayList<>();

    //推荐练习的适配器
    Adapter_GridRecycle gridAdapter;
    /**
     * 设置推荐练习的列表
     */
    public void setRecommend_list() {
        FullyGridLayoutManager fullyGridLayoutManager = new FullyGridLayoutManager(getContext(), 2);
        fullyGridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        gridAdapter = new Adapter_GridRecycle(recTypeList, getContext());
        recommend_list.setLayoutManager(fullyGridLayoutManager);
        recommend_list.setAdapter(gridAdapter);
        gridAdapter.setOnItemClicklistener(new RecyclerItemClickListener());

    }

    /**
     * 设置智能推荐和按知识点练习点击事件
     */
    public void setBulbPencil_btn() {
        bulb_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bulbClick();
            }
        });

        pencil_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pencilClick();
            }
        });
    }

    /**
     * 推荐练习点击事件执行的方法,
     */
    public void bulbClick() {
        Toast.makeText(getContext(), "推荐练习", Toast.LENGTH_SHORT).show();
        GotoAnswerActivity.jumpByCourse(type,getContext());
    }

    /**
     * 按知识点练习点击事件执行的方法
     */
    public void pencilClick() {
        Toast.makeText(getContext(), "按知识点练习", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(getContext(), KnowledgePointPracticeActivity.class);
        intent.putExtra("type",type);
        startActivity(intent);
    }


    /**
     * 设置推荐练习列表的点击事件
     */
    public class RecyclerItemClickListener implements OnRecyclerItemClickListener {

        @Override
        public void onItemClick(View view, int position) {
            String topical=recTypeList.get(position).getName();
            Toast.makeText(getContext(), "点击事件" + topical, Toast.LENGTH_SHORT).show();
            GotoAnswerActivity.jumpByTopical(type,topical,getContext());
        }
    }

    private void setCollection_btnclick(){
        mistake_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), CollectionActivity.class);
                intent.putExtra("type","我的错题");
                startActivity(intent);
            }
        });

        collection_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), CollectionActivity.class);
                intent.putExtra("type","我的收藏");
                startActivity(intent);
            }
        });
    }


    //设置测试用的标题数据
    public void setRecommendTypeList(PracticeContent content) {
        practiceContent=content;
        recTypeList.clear();
        List<String> pointList=content.getPointList();
        for(int i=0;i<pointList.size();i++){
            recTypeList.add(new RecommendType(i,pointList.get(i)));
            Log.i("显示的内容",TAG+" "+recTypeList.get(i).getName());
        }
        if (gridAdapter!=null) {
            Log.i(TAG,"数据更新");
            gridAdapter.notifyDataSetChanged();
        }
    }
}
