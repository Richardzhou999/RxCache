package com.github.Richard.RxCache;
import com.github.richard.gson.GsonSpeaker;
import com.github.richard.runtime.rx_cache2.RxCache2;
import com.github.richard.runtime.rx_cache3.RxCache3;

/**
 * @Author Charlie
 * @Date 2022/9/14 14:44
 */
public class CacheProviders {

    public synchronized static LoginCacheProviders getLoginCache(){
        return new RxCache2.Builder()
                .persistence(CardApplication.getContext().getExternalCacheDir(), new GsonSpeaker())
                .using(LoginCacheProviders.class);
    }


    public synchronized static LoginCacheProviders getLoginCache3(){
        return new RxCache3.Builder()
                .persistence(CardApplication.getContext().getExternalCacheDir(), new GsonSpeaker())
                .using(LoginCacheProviders.class);
    }


}
