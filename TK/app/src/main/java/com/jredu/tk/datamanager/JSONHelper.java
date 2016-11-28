package com.jredu.tk.datamanager;



import com.jredu.tk.R;
import com.jredu.tk.entity.Expandlable;
import com.jredu.tk.entity.Question;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 昂首天下 on 2016/11/8.
 */

public class JSONHelper {

    //知识点练习解析  列表   KnowledgePointPracticeFragment里面
    public static List<Expandlable> initExpandlableData(String s) {
        Data mData=new Data();
        int k=0;
        final List<Expandlable> parent = new ArrayList<Expandlable>();
        Map<Integer, List<Expandlable>> map = new HashMap<>();
        List<Expandlable> list1=new ArrayList<>();
        try {
            JSONArray jsonArray=new JSONArray(s);
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (i==0) {
                    Expandlable expandlable = new Expandlable(jsonObject.getString("sname"), "0", "1504", R.mipmap.arrow_down);
                    Expandlable expandlableChile = new Expandlable(jsonObject.getString("tname"), "0", jsonObject.getString("tnum"), R.mipmap.arrow_down);
                    parent.add(expandlable);
                    list1.add(expandlableChile);
                }else{
                    boolean flag=jsonObject.get("sname").equals(jsonArray.getJSONObject(i-1).get("sname"));
                    if(!flag){
                        int num=0;
                        for (int j=0;j<list1.size();j++){
                          int  numChild=Integer.parseInt(list1.get(j).getSum());
                            num=num+numChild;
                        }
                        parent.get(k).setSum(num+"");
                        map.put(k++, list1);
                        list1=new ArrayList<>();
                        Expandlable expandlable = new Expandlable(jsonObject.getString("sname"), "0", "1504", R.mipmap.arrow_down);
                        Expandlable expandlableChile = new Expandlable(jsonObject.getString("tname"), "0", jsonObject.getString("tnum"), R.mipmap.arrow_down);
                        parent.add(expandlable);
                        list1.add(expandlableChile);
                    }else{
                        Expandlable expandlableChile = new Expandlable(jsonObject.getString("tname"), "0", jsonObject.getString("tnum"), R.mipmap.arrow_down);
                        list1.add(expandlableChile);
                    }
                }
                if(i==jsonArray.length()-1){
                    int num=0;
                    for (int j=0;j<list1.size();j++){
                        int  numChild=Integer.parseInt(list1.get(j).getSum());
                        num=num+numChild;
                    }
                    parent.get(k).setSum(num+"");
                    map.put(k++, list1);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mData.setParentList(parent);
        mData.setChildMap(map);
        return parent;
    }

    //收藏的问题解析   CollectionQuestionFragment 里面
    public static List<Question> transformCollectionQuestion(String s){
        List<Question> list=new ArrayList<>();
        try {
            JSONArray jsonArray=new JSONArray(s);
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                Question question=new Question();
                question.setTitle(jsonObject.getString("title"));
                list.add(question);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
