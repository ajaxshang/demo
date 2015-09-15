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
import com.ajax.demo.activities.ListViewActivity;
import com.ajax.demo.activities.VolleyActivity;

/**
 * Created by Administrator on 2015/9/8.
 */
public class NetWorkMenu extends Fragment implements OnClickListener {
    private Button ListView_btn, ListView2_btn, Volley_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_network, null);
        ListView_btn = (Button) view.findViewById(R.id.ListView_btn);
        ListView2_btn = (Button) view.findViewById(R.id.ListView2_btn);
        Volley_btn = (Button) view.findViewById(R.id.Volley_btn);
        ListView_btn.setOnClickListener(this);
        ListView2_btn.setOnClickListener(this);
        Volley_btn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.ListView_btn:
                intent.setClass(getActivity(), ListViewActivity.class);
                intent.putExtra("type", 1);
                break;
            case R.id.ListView2_btn:
                intent.setClass(getActivity(), ListViewActivity.class);
                intent.putExtra("type", 2);
                break;
            case R.id.Volley_btn:
                intent.setClass(getActivity(), VolleyActivity.class);
                break;
        }
        startActivity(intent);
    }
}
