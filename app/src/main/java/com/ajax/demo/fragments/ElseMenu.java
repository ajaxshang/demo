package com.ajax.demo.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.ajax.demo.R;
import com.ajax.demo.activities.AnimationActivity;
import com.ajax.demo.activities.NavigationDrawerActivity;
import com.ajax.demo.activities.PinnedSectionListActivity;

/**
 * Created by Administrator on 2015/9/8.
 */
public class ElseMenu extends Fragment implements OnClickListener {

    private Button Animation_btn, FloatingAction_btn, DrawerLayout_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_elsemenu, null);
        Animation_btn = (Button) view.findViewById(R.id.Animation_btn);
        FloatingAction_btn = (Button) view.findViewById(R.id.FloatingAction_btn);
        DrawerLayout_btn = (Button) view.findViewById(R.id.DrawerLayout_btn);
        Animation_btn.setOnClickListener(this);
        FloatingAction_btn.setOnClickListener(this);
        DrawerLayout_btn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.Animation_btn:
                intent.setClass(getActivity(), AnimationActivity.class);
                break;
            case R.id.FloatingAction_btn:
                intent.setClass(getActivity(), PinnedSectionListActivity.class);
                break;
            case R.id.DrawerLayout_btn:
                intent.setClass(getActivity(), NavigationDrawerActivity.class);
                break;
        }
        startActivity(intent);
    }
}
