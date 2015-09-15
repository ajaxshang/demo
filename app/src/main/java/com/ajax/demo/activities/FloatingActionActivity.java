package com.ajax.demo.activities;

import android.app.FragmentTransaction;
import android.os.Bundle;

import com.ajax.demo.floatingactionbutton.FloatingActionButtonBasicFragment;
import com.ajax.demo.R;

/**
 * Created by Administrator on 2015/8/25.
 */
public class FloatingActionActivity extends BaseActivity {
    public static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            FloatingActionButtonBasicFragment fragment = new FloatingActionButtonBasicFragment();
            transaction.replace(R.id.sample_content_fragment, fragment);
            transaction.commit();
        }
    }
}
