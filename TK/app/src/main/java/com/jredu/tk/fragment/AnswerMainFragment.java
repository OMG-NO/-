package com.jredu.tk.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jredu.tk.R;
import com.jredu.tk.activity.CommitActivity;
import com.jredu.tk.adapter.Adapter_OptionList;
import com.jredu.tk.adapter.PagerAdapter_Question;
import com.jredu.tk.entity.Option;
import com.jredu.tk.entity.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HunBing on 2016/11/4.
 * 回答问题的主要页面,在这个页面里有一个Viewpager用来展示
 */

public class AnswerMainFragment extends Fragment {
    private  static final String TAG=AnswerMainFragment.class.getSimpleName();

    View view;

    /**
     * 问题列表
     */
    List<Question> questionList;
    /**
     * 题干展示的ViewPager
     */
    ViewPager theQuestionPager;
    /**
     * 选项列表
     */
    ListView optionViewList;

    /**
     * 当前问题的选项列表
     */
    List<Option> optionList;

    //交卷
    TextView handPaper;

    //对Fragment的监听
    OnFragmentListener fragmentListener;

    int currentPosition=0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.answer_main_fragment,null);
        setView();
        setTheQuestionPager();
        setOptionListView(0);
        setHandPaper();

        return view;
    }

    public void setView(){
        theQuestionPager= (ViewPager) view.findViewById(R.id.theQuestionPager);
        optionViewList= (ListView) view.findViewById(R.id.optionlist_view);
        handPaper= (TextView) view.findViewById(R.id.handParper);
    }

    /**
     * 从Activity中获取到要显示的数据
     * @param questionList 请求的问题列表
     */
    public void setTheQuestionList(List<Question> questionList){
        this.questionList=questionList;
    }

    /**
     * 生成Fragment的列表,在这里请求数据
     */
    public List<Fragment> setTheQuestionPagerFragment(){
        List<Fragment> questionPagerFragmentList=new ArrayList<>();
        for (int i=0;i<questionList.size();i++){
            AnswerQuestionFragment pageFragment=new AnswerQuestionFragment();
            Log.i(TAG,questionList.get(i).getTitle());
            pageFragment.setTheQuestionTitle(questionList.get(i).getTitle());
            questionPagerFragmentList.add(pageFragment);
        }
        return questionPagerFragmentList;
    }

    /**
     * 设置题干展示的Viewpager
     */
    public void setTheQuestionPager(){
        List<Fragment> questionPagerFragmentList=setTheQuestionPagerFragment();
        Log.i(TAG,"创建试题列表");
        PagerAdapter_Question questionAdapter=new PagerAdapter_Question(getFragmentManager(),questionPagerFragmentList);

        theQuestionPager.setAdapter(questionAdapter);
        theQuestionPager.addOnPageChangeListener(new onQuestionPageChangeListener());
    }

    boolean isScrolling=false;
    boolean isTheLast=false;
    /**
     * 设置题干展示页面的监听
     */
    public class onQuestionPageChangeListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (isScrolling && currentPosition==questionList.size()-1){

                Log.i(TAG,"position"+position+"positionoffset"+positionOffset+"positionOffsetPixels"+positionOffsetPixels);
            }
        }

        @Override
        public void onPageSelected(int position) {
            Toast.makeText(getContext(),"当前页码"+position,Toast.LENGTH_SHORT).show();
            //改变当前位置
            currentPosition=position;

            fragmentListener.onQuestionPageSelected(position);
            setOptionListView(position);
            if (position==questionList.size()-1){
                isTheLast=true;
                setHandPaper();
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            Log.i("viewpager","状态变化"+state);
            fragmentListener.onQuestionPageStateChanged(state);

            if (state==1){
                isScrolling=true;
            }else{
                isScrolling=false;
            }
        }
    }

    /**
     * 设置点击交卷按钮进行交卷
     */
    private void setHandPaper(){
        if (isTheLast){
            handPaper.setVisibility(View.VISIBLE);
            handPaper.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    handPaper();
                }
            });
        }
    }

    /**
     * 点击交卷是弹出弹窗,然后确认弹窗
     */
    public void handPaper(){
        final AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setTitle("确认")
                .setMessage("是否确认交卷")
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent(getContext(), CommitActivity.class);
                        Bundle bundle=new Bundle();
                        bundle.putParcelableArrayList("questionList", (ArrayList<? extends Parcelable>) questionList);
                        intent.putExtra("questionList",bundle);
                        startActivity(intent);
                        getActivity().finish();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).show();

        builder.create();
    }

    /**
     * 设置对Fragment的监听
     * @param fragmentListener  监听器包含页面的跳转,滑动时的状态转换
     */
    public void setFragmentListener(OnFragmentListener fragmentListener){
        this.fragmentListener=fragmentListener;
    }

    /**
     * Fragment的监听,包含页面状态变化,页面变化
     */
    public interface OnFragmentListener{
        public void onQuestionPageStateChanged(int state);

        public void onQuestionPageSelected(int position);

        public void onChooseAnswerLinstener(int position, int answerPosition);
    }

    /**
     * 设置选项列表的显示
     * @param position  当前问题
     */
    private void setOptionListView(int position){
        optionList=questionList.get(position).getOptionList();

        try {
            optionList.size();
        }catch (NullPointerException e){
            return;
        }
        final Adapter_OptionList optionAdapter=new Adapter_OptionList(optionList,getContext());

        optionViewList.setAdapter(optionAdapter);

        optionViewList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (Option option : optionList) {
                    option.setSelec(false);
                }
                questionList.get(currentPosition).setSelected(true);
                fragmentListener.onChooseAnswerLinstener(currentPosition,position);
                
                optionList.get(position).setSelec(true);
                optionAdapter.notifyDataSetChanged();
            }
        });
    }
}
