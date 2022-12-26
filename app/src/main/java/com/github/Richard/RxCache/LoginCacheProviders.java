package com.github.Richard.RxCache;


import com.github.richard.runtime.rx_cache2.internal.LifeCache;

import java.util.concurrent.TimeUnit;



/**
 * @Author Charlie
 * @Date 2022/9/14 14:40
 */
public interface LoginCacheProviders {

    @LifeCache(duration = 5,timeUnit = TimeUnit.MINUTES)
    io.reactivex.Observable<BasicResponse> getLogin(io.reactivex.Observable<BasicResponse> user);

    @LifeCache(duration = 5,timeUnit = TimeUnit.MINUTES)
    io.reactivex.rxjava3.core.Observable<BasicResponse> getLogin3(io.reactivex.rxjava3.core.Observable<BasicResponse> user);

}
