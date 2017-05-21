package com.cxing.mygank.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;

import java.util.HashMap;

/**
 * Created by zhao on 17-5-21.
 */

public abstract class BaseDrawerActivity extends BaseSwipeRefreshActivity {
    protected DrawerLayout mDrawerLayout;
    protected NavigationView mNavigationView;
    private ActionBarDrawerToggle mDrawerToggle;
    protected HashMap<Integer,MenuItem> mMenuMap;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mNavigationView.setNavigationItemSelectedListener(getNavigetionItemSelectedListener());
        this.mDrawerLayout.setDrawerListener(getDrawListener());
    }

    private DrawerLayout.DrawerListener getDrawListener() {
        return null;
    }

    protected abstract NavigationView.OnNavigationItemSelectedListener getNavigetionItemSelectedListener();

}
