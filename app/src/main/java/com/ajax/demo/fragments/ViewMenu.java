package com.ajax.demo.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ajax.demo.R;
import com.ajax.demo.activities.CarActivity;
import com.ajax.demo.activities.SnakeActivity;
import com.ajax.demo.activities.TetrisActivity;

/**
 * Created by Administrator on 2015/9/8.
 */
public class ViewMenu extends Fragment implements View.OnClickListener {

    private Button GameUI_btn, Snake_btn, View_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_viewmenu, null);
        GameUI_btn = (Button) view.findViewById(R.id.GameUI_btn);
        Snake_btn = (Button) view.findViewById(R.id.Snake_btn);
        View_btn = (Button) view.findViewById(R.id.views_btn);
        GameUI_btn.setOnClickListener(this);
        Snake_btn.setOnClickListener(this);
        View_btn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.GameUI_btn:
                intent.setClass(getActivity(), TetrisActivity.class);
                break;
            case R.id.Snake_btn:
                intent.setClass(getActivity(), SnakeActivity.class);
                break;
            case R.id.views_btn:
                intent.setClass(getActivity(), CarActivity.class);
                break;
        }
        startActivity(intent);
    }
}
