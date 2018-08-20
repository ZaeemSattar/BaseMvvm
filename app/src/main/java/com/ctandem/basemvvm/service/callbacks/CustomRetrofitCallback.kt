package com.ctandem.basemvvm.service.callbacks

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class CustomRetrofitCallback<T> : Callback<T> {

    override fun onFailure(call: Call<T>?, t: Throwable?) {

        onError(RetrofitResponse(ResponseCodes.CODE_ERROR,""))
    }

    override fun onResponse(call: Call<T>?, response: Response<T>?) {
        if (response?.body() != null && response.isSuccessful)
        {

            onSuccess(RetrofitResponse(ResponseCodes.CODE_SUCCESS,""),response)
        }
        else
        {
            onError(RetrofitResponse(ResponseCodes.CODE_ERROR,""))
        }
    }

    protected abstract fun onError(retrofitResponse: RetrofitResponse)
    protected abstract fun onSuccess(retrofitResponse: RetrofitResponse,response: Response<T>)

}