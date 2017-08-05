package com.cxing.mygank.base;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.cxing.mygank.R;

import butterknife.Bind;

/**
 * Created by zhao on 17-5-21.
 */

public abstract class BaseToolBarActivity extends BaseAppCompatActivity {
    @Bind(R.id.toolbar)
    protected Toolbar mToolbar;
    @Bind(R.id.app_bar_layout)
    protected AppBarLayout mAppBarLayout;
    private ActionBarHelper mActionHelper;


    @Override
    protected void initToolbar(Bundle savedInstanceState) {
       if(mToolbar==null || this.mAppBarLayout==null){
           return;
       }
        this.setSupportActionBar(mToolbar);
        this.mActionHelper = new ActionBarHelper();
        if(Build.VERSION.SDK_INT>=21){
            this.mAppBarLayout.setElevation(6.6f);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            this.onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void showBack(){
        if(mActionHelper!=null){
            mActionHelper.setDisplayHomeAsUpEnable(true);
        }
    }

    protected void setAppBarLayoutVisibity(boolean visible){
        if(mAppBarLayout!=null){
            if(visible){
                mAppBarLayout.setVisibility(View.VISIBLE);
            } else{
                mAppBarLayout.setVisibility(View.GONE);
            }
        }

    }

    public class ActionBarHelper{
        private final ActionBar mActionBar;
        public CharSequence mDrawerTitle;
        public CharSequence mTitle;

        public ActionBarHelper(){
            this.mActionBar = getSupportActionBar();
            this.mActionBar.setDisplayHomeAsUpEnabled(true);
            this.mActionBar.setDisplayShowHomeEnabled(false);
            this.mTitle = getTitle();
            this.mDrawerTitle = getTitle();
        }

        public void onDrawerClosed(){
            if(this.mActionBar!=null){
                this.mActionBar.setTitle(this.mTitle);
            }
        }

        public void onDrawerOpened(){
            if(this.mActionBar!=null){
                this.mActionBar.setTitle(this.mDrawerTitle);
            }
        }

        public void setTitle(CharSequence title){
            this.mTitle = title;
        }

        public void setDrawerTitle(CharSequence drawerTitle){
            this.mDrawerTitle = drawerTitle;
        }

        public void setDisplayHomeAsUpEnable(boolean showHomeAsUp){
            if(this.mActionBar!=null){
                this.mActionBar.setDisplayHomeAsUpEnabled(showHomeAsUp);
            }
        }





    }


}
