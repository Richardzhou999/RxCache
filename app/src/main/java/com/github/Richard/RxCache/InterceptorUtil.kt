package com.github.Richard.RxCache
import android.util.Log
import okhttp3.logging.HttpLoggingInterceptor
import java.io.UnsupportedEncodingException
import java.net.URLDecoder


/**
 * @Author Charlie
 * @Date 2022/7/19 14:35
 */
object InterceptorUtil {

    /**
     * 日志拦截器,用于打印返回请求的结果
     */
    fun logInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor {  message ->
            try {
                Log.w("UWLog", "log:${URLDecoder.decode(message,"utf-8")}")
            }catch (e: UnsupportedEncodingException){
                e.printStackTrace()
                Log.e("UWLog","error:$message")
            }
        }.setLevel(HttpLoggingInterceptor.Level.BODY)
    }



}