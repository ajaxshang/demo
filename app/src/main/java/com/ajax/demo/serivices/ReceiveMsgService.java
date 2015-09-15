package com.ajax.demo.serivices;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.ajax.demo.Utils.NetworkStateManager;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by Administrator on 2015/9/13.
 */
public class ReceiveMsgService extends Service {

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                Timer timer = new Timer();
                timer.schedule(new QunxTask(getApplicationContext()), new Date());
            }
        }
    };

    public interface GetConnectState {
        void GetState(int state);
    }

    private GetConnectState onGetConnectState;

    public GetConnectState getOnGetConnectState() {
        return onGetConnectState;
    }

    public void setOnGetConnectState(GetConnectState onGetConnectState) {
        this.onGetConnectState = onGetConnectState;
    }

    private Binder binder = new MyBinder();
    private boolean isConnected = true;
    private int state = 0;
    private final int NONE_STATE = 0;
    private final int MOBILE_STATE = 1;
    private final int WIFI_STATE = 2;

    private NetworkStateManager networkStateManager;


    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {
        IntentFilter mFilter = new IntentFilter();
        mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mReceiver, mFilter);
        networkStateManager.instance().init(this);
    }

    class QunxTask extends TimerTask {
        private Context context;

        public QunxTask(Context context) {
            this.context = context;
        }

        @Override
        public void run() {

            if (networkStateManager.instance().isNetworkConnected()) {
                if (networkStateManager.instance().isMobileConnected())
                    state = MOBILE_STATE;
                if (networkStateManager.instance().isWifiConnected())
                    state = WIFI_STATE;
            } else {
                state = NONE_STATE;
            }
            if (onGetConnectState != null) {
                onGetConnectState.GetState(state); // 通知网络状态改变
                Log.i("NETWORK", "state : " + state);
            }
        }
    }


    public class MyBinder extends Binder {
        public ReceiveMsgService getService() {
            return ReceiveMsgService.this;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }
}
