package com.ajax.demo.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ajax.demo.R;
import com.ajax.demo.activities.CarmerActivity;
import com.ajax.demo.activities.MediaPlayActivity;
import com.ajax.demo.zxing.activity.CaptureActivity;

/**
 * Created by Administrator on 2015/9/8.
 */
public class HardWareMenu extends Fragment implements View.OnClickListener {
    private Button ZXing_btn, Camera_btn, MediaPlay_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_hardware, null);
        ZXing_btn = (Button) view.findViewById(R.id.ZXing_btn);
        Camera_btn = (Button) view.findViewById(R.id.Camera_btn);
        MediaPlay_btn = (Button) view.findViewById(R.id.MediaPlay_btn);
        ZXing_btn.setOnClickListener(this);
        Camera_btn.setOnClickListener(this);
        MediaPlay_btn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.ZXing_btn:
                intent.setClass(getActivity(), CaptureActivity.class);
                break;
            case R.id.Camera_btn:
                intent.setClass(getActivity(), CarmerActivity.class);
                break;
            case R.id.MediaPlay_btn:
                intent.setClass(getActivity(), MediaPlayActivity.class);
                break;
        }
        startActivity(intent);
    }
}
