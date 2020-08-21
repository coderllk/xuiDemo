package com.llk.xuidemo;

import android.app.Application;

import com.xuexiang.xui.XUI;

public class DemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        XUI.init(this); //初始化UI框架
        XUI.debug(true);
    }
}
