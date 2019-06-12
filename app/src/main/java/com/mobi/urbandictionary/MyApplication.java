package com.mobi.urbandictionary;

import android.app.Application;

import com.mobi.urbandictionary.di.component.AppComponent;
import com.mobi.urbandictionary.di.component.DaggerAppComponent;
import com.mobi.urbandictionary.di.module.AppModule;
import com.mobi.urbandictionary.di.module.RetrofitModule;

public class MyApplication extends Application {
    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).retrofitModule(new RetrofitModule()).build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}