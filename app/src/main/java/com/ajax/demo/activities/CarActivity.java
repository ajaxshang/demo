package com.ajax.demo.activities;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.ajax.demo.R;
import com.ajax.demo.view.ClockView;
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
    private RelativeLayout views_layout;
    private final int MP = ViewGroup.LayoutParams.MATCH_PARENT;
    private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        radioGroup = (RadioGroup) this.findViewById(R.id.radioGroup);
        views_layout = (RelativeLayout) this.findViewById(R.id.views_layout);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.customview_btn:
                        custom_view = new CustomView(CarActivity.this);
                        new Thread(custom_view).start();
                        views_layout.removeAllViews();
                        views_layout.addView(custom_view, new RelativeLayout.LayoutParams(MP, MP));
                        break;
                    case R.id.wave_btn:
                        wave_view = new WaveView(CarActivity.this);
                        views_layout.removeAllViews();
                        views_layout.addView(wave_view, new RelativeLayout.LayoutParams(MP, MP));
                        break;
                    case R.id.star_btn:
                        star_view = new StarView(CarActivity.this);
                        views_layout.removeAllViews();
                        views_layout.addView(star_view, new RelativeLayout.LayoutParams(MP, MP));
                        break;
                    case R.id.multi_btn:
                        multicricle_view = new MultiCricleView(CarActivity.this);
                        views_layout.removeAllViews();
                        views_layout.addView(multicricle_view, new RelativeLayout.LayoutParams(MP, MP));
                        break;
                    case R.id.font_btn:
                        font_view = new FontView(CarActivity.this);
                        views_layout.removeAllViews();
                        views_layout.addView(font_view, new RelativeLayout.LayoutParams(MP, MP));
                        new Thread(font_view).start();
                        break;
                    case R.id.clock_btn:
                        ClockView clockView = new ClockView(CarActivity.this);
                        views_layout.removeAllViews();
                        views_layout.addView(clockView, new RelativeLayout.LayoutParams(MP, MP));
                        new Thread(clockView).start();
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
