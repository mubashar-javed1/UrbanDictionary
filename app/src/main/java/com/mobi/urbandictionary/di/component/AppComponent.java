package com.mobi.urbandictionary.di.component;

import com.mobi.urbandictionary.di.module.AppModule;
import com.mobi.urbandictionary.di.module.RetrofitModule;
import com.mobi.urbandictionary.ui.mainactivity.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class, RetrofitModule.class})
@Singleton
public interface AppComponent {
    void doInjection(MainActivity mainActivity);
}
