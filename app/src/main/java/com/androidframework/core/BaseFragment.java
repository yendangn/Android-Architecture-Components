package com.androidframework.core;

import android.app.ProgressDialog;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.ViewModelProvider;
import android.support.v4.app.Fragment;

import com.androidframework.di.Injectable;

import javax.inject.Inject;

import butterknife.Unbinder;

/**
 * Created by yendang on 2/19/18.
 */

public class BaseFragment extends Fragment implements LifecycleOwner, Injectable {


    @Inject
    public ViewModelProvider.Factory viewModelFactory;

    protected Unbinder unbinder;

    private ProgressDialog dialog;

    @Override
    public void onDestroy() {

        if (unbinder != null) {
            unbinder.unbind();
        }

        super.onDestroy();
    }
}
