package com.jredu.tk.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jredu.tk.R;
import com.jredu.tk.entity.RecommendType;

import java.util.List;


/**
 * Created by hp on 2016/8/19.
 */
public class Adapter_GridRecycle extends RecyclerView.Adapter<Adapter_GridRecycle.ViewHolder> {
    List<RecommendType> list;
    Context context;

    OnRecyclerItemClickListener onItemClicklistener;

    public Adapter_GridRecycle(List<RecommendType> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.rec_recycle_item,null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    public void setOnItemClicklistener(OnRecyclerItemClickListener onItemClicklistener){
        this.onItemClicklistener=onItemClicklistener;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        RecommendType recommendType=list.get(position);
        if (holder!=null){
            holder.recommmend_name.setText(recommendType.getName());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClicklistener.onItemClick(holder.itemView,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView recommmend_name;

        public ViewHolder(View itemView) {
            super(itemView);
            this.recommmend_name= (TextView) itemView.findViewById(R.id.rec_title);
        }
    }
}
