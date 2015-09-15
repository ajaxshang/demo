package com.ajax.demo.activities;

import android.app.ActionBar;
import android.os.Bundle;

import com.ajax.demo.R;

/**
 * Created by Administrator on 2015/8/25.
 */
public class MultitouchActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multitouch);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("手势操作");
    }

}
