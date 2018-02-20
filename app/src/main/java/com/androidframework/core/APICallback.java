package com.androidframework.core;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yendang on 2/18/18.
 */

public abstract class APICallback<T extends AppResponse> implements Callback<T> {

    abstract public void onSuccess(Response<T> response);

    @Override
    public void onResponse(Call<T> call, Response<T> response) {

        if (response != null && response.isSuccessful() && response.body() != null) {

            // TODO: 2/20/18 Handle logic.
            onSuccess(response);

        } else {
            onFail(call, response);
        }
    }

    @Override
    public void onFailure(Call call, Throwable t) {
        // TODO: 2/18/18 Handle logic.
    }

    public void onFail(Call<T> call, Response<T> response) {

        // TODO: 2/20/18 Handle logic.

    }
}
