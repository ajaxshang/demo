package com.ajax.demo.activities;

import android.os.Bundle;

import com.ajax.demo.R;
import com.ajax.demo.camera.Camera2BasicFragment;

/**
 * Created by Administrator on 2015/8/24.
 */
public class CarmerActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        if (null == savedInstanceState) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, Camera2BasicFragment.newInstance())
                    .commit();
        }
    }

}
