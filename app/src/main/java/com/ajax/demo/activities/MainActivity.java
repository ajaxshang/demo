package com.ajax.demo.activities;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ajax.demo.R;
import com.ajax.demo.adapter.DevicesRecyclerViewAdapter;
import com.ajax.demo.fragments.ElseMenu;
import com.ajax.demo.fragments.GestureMenu;
import com.ajax.demo.fragments.HardWareMenu;
import com.ajax.demo.fragments.NetWorkMenu;
import com.ajax.demo.fragments.ViewMenu;
import com.ajax.demo.serivices.ReceiveMsgService;
import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;

/**
 * 上传到GIT进行版本控制
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FragmentManager manager;

    private GestureMenu gestureMenu;
    private HardWareMenu hardWareMenu;
    private ElseMenu elseMenu;
    private NetWorkMenu netWorkMenu;
    private ViewMenu viewMenu;

    protected String TAG = "NETWORK";
    ReceiveMsgService receiveMsgService;

    private TextView textView;
    private FragmentTransaction transaction;

    private RecyclerView mRecyclerView;
    private Toolbar mToolbar;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private FloatingActionButton mFab;
    private DrawerLayout drawerLayout;
    private DevicesRecyclerViewAdapter mDevicesRecyclerViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ActionBar actionBar = getActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(false);
        manager = getSupportFragmentManager();
        dm = getResources().getDisplayMetrics();
//        ViewPager pager = (ViewPager) findViewById(R.id.pager);
//        tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
//        textView = (TextView) findViewById(R.id.network_state);
//        pager.setAdapter(new PagerAdapter(manager));
//        tabs.setViewPager(pager);
//        setTabsValue();

//        bind();

        setupNavigationView();
        setupToolbar();
        setupCollapsingToolbarLayout();
        setupFab();
        setupRecyclerView();
    }

    private PagerSlidingTabStrip tabs;
    private DisplayMetrics dm;

    private void setTabsValue() {
        tabs.setShouldExpand(true);
        tabs.setDividerColor(Color.TRANSPARENT);
        tabs.setUnderlineHeight((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 1, dm));
        tabs.setIndicatorHeight((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 4, dm));
        tabs.setTextSize((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 16, dm));
        tabs.setIndicatorColor(Color.parseColor("#45c01a"));
        tabs.setTabBackground(0);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab) {
//            Snackbar.make(findViewById(R.id.coordinatorLayout), "This is Snackbar",
//                    Snackbar.LENGTH_LONG).setAction("Action", this).show();
            Toast.makeText(getApplication(), "先摆设一下", Toast.LENGTH_SHORT).show();
        }
    }

    public class PagerAdapter extends FragmentPagerAdapter {
        public PagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        private final String[] titles = {"硬件相关", "手势", "网络", "自定义View", "其他"};

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    if (hardWareMenu == null) {
                        hardWareMenu = new HardWareMenu();
                    }
                    return hardWareMenu;
                case 1:
                    if (gestureMenu == null) {
                        gestureMenu = new GestureMenu();
                    }
                    return gestureMenu;
                case 2:
                    if (netWorkMenu == null) {
                        netWorkMenu = new NetWorkMenu();
                    }
                    return netWorkMenu;
                case 3:
                    if (viewMenu == null) {
                        viewMenu = new ViewMenu();
                    }
                    return viewMenu;
                case 4:
                    if (elseMenu == null) {
                        elseMenu = new ElseMenu();
                    }
                    return elseMenu;
                default:
                    return null;
            }
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void setupRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.activity_main_recyclerview_numbers);
        mDevicesRecyclerViewAdapter = new DevicesRecyclerViewAdapter(new ArrayList<String>());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mDevicesRecyclerViewAdapter);

        for (int i = 0; i < 50; i++) {
            mDevicesRecyclerViewAdapter.getDevices().add("Card Item: " + i);
        }

        mDevicesRecyclerViewAdapter.notifyDataSetChanged();


    }

    private void setupNavigationView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    }

    private void setupCollapsingToolbarLayout() {
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.activity_main_collapsing);
        if (mCollapsingToolbarLayout != null) {
            mCollapsingToolbarLayout.setTitle("I LOVE ANDROID !!!");
        }
    }

    private void setupFab() {
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        if (mFab != null)
            mFab.setOnClickListener(this);
    }

    private void setupToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);

        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
        // Show menu icon
        final android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (drawerLayout != null)
                    drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void bind() {
        Intent intent = new Intent(MainActivity.this, ReceiveMsgService.class);
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }

    private final int NONE_STATE = 0;
    private final int MOBILE_STATE = 1;
    private final int WIFI_STATE = 2;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            receiveMsgService = ((ReceiveMsgService.MyBinder) service).getService();
            receiveMsgService.setOnGetConnectState(new ReceiveMsgService.GetConnectState() {
                @Override
                public void GetState(final int state) {
                    Message msg = Message.obtain();
                    Bundle bundle = new Bundle();
                    switch (state) {
                        case NONE_STATE:
                            bundle.putString("state", "NONE_STATE");
                            break;
                        case MOBILE_STATE:
                            bundle.putString("state", "MOBILE_STATE");
                            break;
                        case WIFI_STATE:
                            bundle.putString("state", "WIFI_STATE");
                            break;
                    }
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private void unbind() {
        if (receiveMsgService != null) {
            unbindService(serviceConnection);
            Log.i(TAG, "DO unbind()");
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            textView.setText("state:" + msg.getData().getString("state"));
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbind();
    }
}
