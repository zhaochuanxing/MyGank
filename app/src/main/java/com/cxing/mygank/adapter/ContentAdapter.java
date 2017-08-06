package com.cxing.mygank.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cxing.mygank.R;
import com.cxing.mygank.bean.Scenery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhao on 17-8-6.
 */

public class ContentAdapter extends RecyclerView.Adapter {

    private final List<Scenery> mSceneryList;
    private Context mContext;

    public ContentAdapter(){
        mSceneryList = getSceneryList();
    }

    private List<Scenery> getSceneryList() {
        List<Scenery> sceneryList = new ArrayList<>();
        int start = 0;
        if(mSceneryList!=null){
            start = mSceneryList.size();
        }
        for(int i=start;i<start+8;i++){
            Scenery scenery = new Scenery("风景"+i, R.drawable.picture);
            sceneryList.add(scenery);
        }
        return sceneryList;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_content_view,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.setData(position);
    }

    @Override
    public int getItemCount() {
        if (mSceneryList != null) {
            return mSceneryList.size();
        }
        return 0;
    }

    public void refreshData() {
        List<Scenery> sceneryList = getSceneryList();
        mSceneryList.addAll(sceneryList);
        notifyDataSetChanged();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {

        public final ImageView mPostImg;
        public final TextView mTitleTx;

        public MyViewHolder(View itemView) {
            super(itemView);
            mPostImg = (ImageView)itemView.findViewById(R.id.img_poster);
            mTitleTx = (TextView)itemView.findViewById(R.id.tx_title);
        }

        public void setData(int position) {
            Scenery scenery = mSceneryList.get(position);
            mTitleTx.setText(scenery.getName());
            Glide.with(mContext).load(scenery.getImgId()).into(mPostImg);
        }
    }
}
