package com.cxing.mygank.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.cxing.mygank.R;
import com.cxing.mygank.adapter.ContentAdapter;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = HomeActivity.class.getSimpleName();
    private Toolbar mToolBar;
    private DrawerLayout mDrawerLayout;
    private SwipeRefreshLayout mSwipeLayout;
    private ContentAdapter contentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
    }

    private void initView() {
        this.mToolBar = (Toolbar) findViewById(R.id.toolbar);
        this.mDrawerLayout = (DrawerLayout)findViewById(R.id.drawerlayout);
//        mDrawerLayout.closeDrawer(GravityCompat.START);
        setSupportActionBar(mToolBar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_launcher);
        }
        NavigationView navigationView = (NavigationView)findViewById(R.id.navi_view);
        navigationView.setCheckedItem(R.id.nav_call);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Log.i(TAG,"onNavigationItemSelected"+item);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
        FloatingActionButton floatingActionButton = (FloatingActionButton)findViewById(R.id.floating_btn);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Snackbar.make(v,"data deleted",Snackbar.LENGTH_LONG).setAction("撤销",new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "floating", Toast.LENGTH_SHORT).show();

                    }
                }).show();
            }
        });

        setContentData();
    }

    private void setContentData() {RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        contentAdapter = new ContentAdapter();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(contentAdapter);
        mSwipeLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh);
        mSwipeLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });
    }

    private void refreshData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        contentAdapter.refreshData();
                        mSwipeLayout.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i(TAG, "onOptionsItemSelected" + item);
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
