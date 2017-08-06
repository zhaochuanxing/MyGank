package com.cxing.mygank.activity;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cxing.mygank.R;

public class SceneryActivity extends AppCompatActivity {

    public static final String KEY_NAME = "key_name";
    public static final String KEY_IMG = "key_img";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenery);
        Intent intent = getIntent();
        String sceneryName = intent.getStringExtra(KEY_NAME);
        int imgId = intent.getIntExtra(KEY_IMG,0);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_content);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id
                .collapsing_toolbar);
        collapsingToolbarLayout.setTitle(sceneryName);

        ImageView headerImg = (ImageView)findViewById(R.id.img_scenery);
        TextView nameTx = (TextView)findViewById(R.id.tx_card_name);
        Glide.with(this).load(imgId).into(headerImg);
        String contentStr = getContent(sceneryName);
        nameTx.setText(contentStr);
    }

    private String getContent(String sceneryName) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<300;i++){
            stringBuilder.append(sceneryName);
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
