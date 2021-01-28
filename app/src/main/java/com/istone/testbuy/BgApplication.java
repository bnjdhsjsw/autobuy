package com.istone.testbuy;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDexApplication;

import com.blankj.utilcode.util.LogUtils;
import com.istone.testbuy.ui.activity.WelcomeActivity;
import com.zxy.recovery.core.Recovery;

public class BgApplication extends MultiDexApplication {

    private static Application application;
    public static Context getContext() {
        return application.getApplicationContext();
    }

    public static boolean isDebug() {
        return BuildConfig.DEBUG;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        LogUtils.getConfig().setLogSwitch(isDebug());
        try {
            crashDefender();
        } catch (Exception e) {
            LogUtils.d(e);
        }
    }

    private void crashDefender() {
        Recovery.getInstance()
                .recoverStack(false)
                .recoverEnabled(true)
                .debug(isDebug())
                .recoverInBackground(false)
                .mainPage(WelcomeActivity.class)
                .silent(!isDebug(), Recovery.SilentMode.RESTART)
                .init(this);
    }

}
