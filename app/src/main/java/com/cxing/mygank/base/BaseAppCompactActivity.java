package com.cxing.mygank.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by zhao on 17-5-21.
 */

public abstract class BaseAppCompactActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initToolbar(savedInstanceState);
        initViews(savedInstanceState);
        initData();
        initListener();

    }

    protected abstract void initListener();

    protected abstract void initData();

    protected abstract void initViews(Bundle savedInstanceState);

    protected abstract void initToolbar(Bundle savedInstanceState);

    protected abstract int getLayoutId();

    //获取view的便利方法
    protected <V extends View> V findView(int id){
        return (V)this.findViewById(id);
    }


}
