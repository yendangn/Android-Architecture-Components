package com.androidframework.di.module;

import dagger.Module;
import dagger.android.AndroidInjectionModule;

/**
 * Created by yendang on 2/18/18.
 */

@Module(includes = {AndroidInjectionModule.class,
        ActivityBuilderModule.class,
        ServiceModule.class,
        ViewModelModule.class})
public class AppModule {
}
