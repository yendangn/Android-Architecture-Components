package com.androidframework.core;

import android.arch.lifecycle.LifecycleOwner;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.androidframework.feature.NavigationController;

import javax.inject.Inject;

import butterknife.Unbinder;
import dagger.android.AndroidInjection;
import dagger.android.DispatchingAndroidInjector;

/**
 * Created by yendang on 2/18/18.
 */

public class BaseActivity extends AppCompatActivity implements LifecycleOwner {

    protected Unbinder unbinder;

    @Inject
    public DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Inject
    public NavigationController navigationController;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);

    }

    @Override
    protected void onDestroy() {

        if (unbinder != null) {
            unbinder.unbind();
        }

        super.onDestroy();
    }
}
