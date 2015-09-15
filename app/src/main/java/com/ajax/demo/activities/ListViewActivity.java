package com.ajax.demo.activities;

import android.app.ActionBar;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.ListView;

import com.ajax.demo.R;
import com.ajax.demo.Utils.cache.ImageLoader;
import com.ajax.demo.adapter.ImageAdapter;
import com.ajax.demo.adapter.LoaderAdapter;
import com.ajax.demo.url.Images;

/**
 * Created by Administrator on 2015/8/26.
 */
public class ListViewActivity extends BaseActivity {
    private ListView listView;
    private ImageAdapter adapter;
    private LoaderAdapter loaderAdapter;
    private int type = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        type = getIntent().getIntExtra("type", -1);
        ActionBar actionBar = getActionBar();

        listView = (ListView) findViewById(R.id.listview);
        if (type == 1) {
            adapter = new ImageAdapter(getApplicationContext(), 0, Images.imageUrls);
            actionBar.setTitle("ListView 不带线程池");
        }
        listView.setAdapter(adapter);
        if (type == 2) {
            actionBar.setTitle("ListView 带线程池");
            loaderAdapter = new LoaderAdapter(Images.imageUrls.length, ListViewActivity.this, Images.imageUrls);
            listView.setOnScrollListener(mScrollListener);
            listView.setAdapter(loaderAdapter);
        }
    }

    AbsListView.OnScrollListener mScrollListener = new AbsListView.OnScrollListener() {

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            switch (scrollState) {
                case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
                    loaderAdapter.setFlagBusy(true);
                    break;
                case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                    loaderAdapter.setFlagBusy(false);
                    break;
                case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                    loaderAdapter.setFlagBusy(false);
                    break;
            }
            loaderAdapter.notifyDataSetChanged();
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem,
                             int visibleItemCount, int totalItemCount) {

        }
    };

    @Override
    protected void onDestroy() {
        if (type == 2) {
            ImageLoader imageLoader = loaderAdapter.getImageLoader();
            if (imageLoader != null) {
                imageLoader.clearCache();
            }
        }
        super.onDestroy();
    }


}
