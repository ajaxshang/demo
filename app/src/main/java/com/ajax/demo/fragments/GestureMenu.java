package com.ajax.demo.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ajax.demo.R;
import com.ajax.demo.activities.MultitouchActivity;
import com.ajax.demo.activities.PinchZoomActivity;

/**
 * Created by Administrator on 2015/9/8.
 */
public class GestureMenu extends Fragment implements View.OnClickListener {

    private Button Multitouch_btn, PinchZoom_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_grestuemenu, null);
        Multitouch_btn = (Button) view.findViewById(R.id.Multitouch_btn);
        PinchZoom_btn = (Button) view.findViewById(R.id.PinchZoom_btn);
        Multitouch_btn.setOnClickListener(this);
        PinchZoom_btn.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.Multitouch_btn:
                intent.setClass(getActivity(), MultitouchActivity.class);
                break;
            case R.id.PinchZoom_btn:
                intent.setClass(getActivity(), PinchZoomActivity.class);
                break;
        }
        startActivity(intent);
    }
}
