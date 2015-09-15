package com.ajax.demo.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.ajax.demo.R;
import com.ajax.demo.view.CustomView;
import com.ajax.demo.view.FontView;
import com.ajax.demo.view.MultiCricleView;
import com.ajax.demo.view.StarView;
import com.ajax.demo.view.WaveView;

/**
 * Created by Administrator on 2015/8/29.
 */
public class CarActivity extends BaseActivity {
    private RadioGroup radioGroup;
    private StarView star_view;
    private MultiCricleView multicricle_view;
    private CustomView custom_view;
    private WaveView wave_view;
    private FontView font_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);
        radioGroup = (RadioGroup) this.findViewById(R.id.radioGroup);
        star_view = (StarView) this.findViewById(R.id.star_view);
        multicricle_view = (MultiCricleView) this.findViewById(R.id.multicricle_view);
        custom_view = (CustomView) this.findViewById(R.id.custom_view);
        wave_view = (WaveView) this.findViewById(R.id.wave_view);
        font_view = (FontView) this.findViewById(R.id.font_view);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.customview_btn:
                        custom_view.setVisibility(View.VISIBLE);
                        new Thread(custom_view).start();
                        wave_view.setVisibility(View.GONE);
                        star_view.setVisibility(View.GONE);
                        multicricle_view.setVisibility(View.GONE);
                        font_view.setVisibility(View.GONE);
                        break;
                    case R.id.wave_btn:
                        wave_view.setVisibility(View.VISIBLE);
                        custom_view.setVisibility(View.GONE);
                        star_view.setVisibility(View.GONE);
                        multicricle_view.setVisibility(View.GONE);
                        font_view.setVisibility(View.GONE);
                        break;
                    case R.id.star_btn:
                        star_view.setVisibility(View.VISIBLE);
                        custom_view.setVisibility(View.GONE);
                        wave_view.setVisibility(View.GONE);
                        multicricle_view.setVisibility(View.GONE);
                        font_view.setVisibility(View.GONE);
                        break;
                    case R.id.multi_btn:
                        multicricle_view.setVisibility(View.VISIBLE);
                        custom_view.setVisibility(View.GONE);
                        wave_view.setVisibility(View.GONE);
                        star_view.setVisibility(View.GONE);
                        font_view.setVisibility(View.GONE);
                        break;
                    case R.id.font_btn:
                        new Thread(font_view).start();
                        multicricle_view.setVisibility(View.GONE);
                        custom_view.setVisibility(View.GONE);
                        wave_view.setVisibility(View.GONE);
                        star_view.setVisibility(View.GONE);
                        font_view.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


}
