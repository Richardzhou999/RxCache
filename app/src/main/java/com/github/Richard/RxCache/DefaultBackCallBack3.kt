package com.github.Richard.RxCache

import android.accounts.NetworkErrorException
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable


import java.net.ConnectException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

/**
 * @Author Charlie
 * @Date 2022/8/15 14:24
 */
abstract class DefaultBackCallBack3<T>(): Observer<BasicResponse<T>> {

    override fun onSubscribe(d: Disposable) {
        onStart()

    }

    override fun onNext(response: BasicResponse<T>) {
        if(response.success){
            if(response.data == null && response.result == null && response.datas == null){
                onSuccessEmpty()
            }
            response.data?.let {
                onSuccess(it)
            }
            response.result?.let {
                onSuccess(it)
            }
            response.datas?.let {
                onSuccess(it)
            }
        }else{
            onError(response)
        }
    }

    override fun onError(throwable: Throwable) {
        if (throwable is ConnectException ||
                throwable is TimeoutException ||
                throwable is NetworkErrorException ||
                throwable is UnknownHostException) {
            try {
                onFailure(throwable, false)
            } catch (e1: java.lang.Exception) {
                e1.printStackTrace()
            }
        } else {
            try {
                onFailure(throwable, true)
            } catch (e1: java.lang.Exception) {
                e1.printStackTrace()
            }
        }
    }

    override fun onComplete() {
      
    }

    /**
     * 开始请求
     */
    protected fun onStart(){}

    /**
     * 请求成功且返回码为200
     * @param response
     * @return
     */
    abstract fun onSuccess(response: T)

    /**
     * 请求成功且返回码为200
     * @param response
     * @return 没有data情况下
     */
    abstract fun onSuccessEmpty()

    /**
     * 请求错误
     * @param response
     */
    abstract fun onError(response: BasicResponse<T>)

    /**
     * 请求失败
     * @param e
     * @param netWork
     */
    @Throws(Exception::class)
    abstract fun onFailure(e: Throwable?, netWork: Boolean)
}