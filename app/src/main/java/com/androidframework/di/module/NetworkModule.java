package com.androidframework.di.module;

import android.support.annotation.NonNull;
import android.util.Log;

import com.androidframework.BuildConfig;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yendang on 2/18/18.
 */

@Module
public class NetworkModule {

    private final int TIME_OUT = 5; //MINUTES

    @Provides
    @Singleton
    OkHttpClient okHttpClient() {

        return new OkHttpClient.Builder()
                .addInterceptor(TOKEN_AUTH_INTERCEPTOR)
                .addInterceptor(TIME_ZONE_INTERCEPTOR)
                .addInterceptor(API_KEY_INTERCEPTOR)
                .addInterceptor(LOG_JSON_INTERCEPTOR)
                .connectTimeout(TIME_OUT, TimeUnit.MINUTES)
                .writeTimeout(TIME_OUT, TimeUnit.MINUTES)
                .readTimeout(TIME_OUT, TimeUnit.MINUTES)
                .build();
    }


    @Provides
    @Singleton
    Retrofit provideRetrofitClient(@NonNull OkHttpClient okHttpClient) {

        return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }


    private final Interceptor TOKEN_AUTH_INTERCEPTOR = chain -> {
        Request request = chain.request();
        Request newRequest;

        HttpUrl url = chain.request().url()
                .newBuilder()
                .build();

        newRequest = request.newBuilder()
                .addHeader("Authorization", "YOUR_TOKEN")
                .url(url).build();

        return chain.proceed(newRequest);
    };

    private final Interceptor API_KEY_INTERCEPTOR = chain -> {
        Request request = chain.request();
        HttpUrl url = request.url().newBuilder()
                .addQueryParameter("api_key", BuildConfig.API_KEY)
                .build();
        request = request.newBuilder().url(url).build();
        return chain.proceed(request);
    };

    private final Interceptor TIME_ZONE_INTERCEPTOR = chain -> {
        Request request = chain.request();
        Request newRequest;

        HttpUrl url = chain.request().url()
                .newBuilder()
                .build();

        newRequest = request.newBuilder()
                .addHeader("Timezone", Calendar.getInstance().getTimeZone().getID())
                .url(url).build();

        return chain.proceed(newRequest);
    };

    private final Interceptor LOG_JSON_INTERCEPTOR = chain -> {

        Request request = chain.request();

        String requestRaw = request.url().toString() + "\n";

        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            if (copy != null && copy.body() != null) {
                copy.body().writeTo(buffer);
            }
            requestRaw += buffer.readUtf8();
        } catch (final Exception ignored) {
        }


        Log.d("JSON request", String.format("raw JSON request is: %s", requestRaw));
        //Log.d("Token", AMPGlobal.getToken());


        Response response = chain.proceed(request);

        // don't rebuild response for binary file
        if (!response.body().contentType().toString().equals("application/json; charset=utf-8")) {
            return response;
        }

        String rawJson = response.body().string();

        Log.d("JSON response is", String.format("raw JSON response is: %s", rawJson));

        // Re-create the response before returning it because body can be read only once
        return response.newBuilder().body(ResponseBody.create(response.body().contentType(), rawJson)).build();
    };

}
