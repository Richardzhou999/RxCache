package com.github.Richard.RxCache

import android.content.Context
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * @Author Charlie
 * @Date 2022/8/1 15:22
 */
object RetrofitManager {

    /**
     * 超时时间
     */
    const val TIME_OUT = 15
    /**
     * 缓存内存 - 10 MB
     */
    const val DEFAULT_DIR_CACHE = 10 * 1024 * 1024;

    /**
     * 默认Okhttp,绑定日志
     * @param headerMap
     * @param paramMap 若有参数则加上
     */
    private fun getOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
                .connectTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
                .readTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
                .addInterceptor(InterceptorUtil.logInterceptor())
        return builder.build()
    }

    fun getRetrofit(): Retrofit{
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("http://jasperdev.uweiads.com/ka-api/")
                .client(getOkHttpClient())
                .build()
    }


    fun getRetrofit3(): Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl("http://jasperdev.uweiads.com/ka-api/")
            .client(getOkHttpClient())
            .build()
    }


}