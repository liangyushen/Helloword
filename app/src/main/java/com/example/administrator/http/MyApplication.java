package com.example.administrator.http;

import android.app.Application;

import com.lzy.okgo.OkGo;

/**
 * Created by Administrator on 2019/6/29.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        OkGo.init(this);
    }
}
