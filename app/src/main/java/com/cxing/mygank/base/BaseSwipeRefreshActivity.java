package com.cxing.mygank.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.widget.SwipeRefreshLayout;

import com.cxing.mygank.R;

/**
 * Created by zhao on 17-5-21.
 */

public abstract class BaseSwipeRefreshActivity extends BaseToolBarActivity {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private boolean mIsRefreshing;

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        this.initMultiSwipeRefreshLayout();
    }

    private void initMultiSwipeRefreshLayout() {
        if(mSwipeRefreshLayout==null){
            return;
        }
        this.mSwipeRefreshLayout.setColorSchemeColors(R.color.colorPrimary);
        this.mSwipeRefreshLayout.setOnRefreshListener(() -> onSwipeRefresh());
    }

    protected abstract void onSwipeRefresh();

    public void setRefreshing(boolean status){
        this.mIsRefreshing = status;
    }

    public boolean getRefreshing(){
        return this.mIsRefreshing;
    }

    public void refresh(boolean refresh){
        if(this.mSwipeRefreshLayout==null){
            return;
        }
        if(!refresh && this.mIsRefreshing){
            this.mSwipeRefreshLayout.postDelayed(()->{
                mSwipeRefreshLayout.setRefreshing(false);
                mIsRefreshing = false;
            },300);
        }else if(!this.mIsRefreshing){
            mSwipeRefreshLayout.post(()-> mSwipeRefreshLayout.setRefreshing(true));
            this.mIsRefreshing = true;
        }
    }


}
