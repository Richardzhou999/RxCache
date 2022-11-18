package com.github.Richard.RxCache;

import android.app.Application;
import android.content.Context;

/**
 * @author Charlie
 */
public class CardApplication extends Application {

    private static CardApplication mInstance;
    private static Context mContext;

    public static CardApplication getInstance() {
        if (null == mInstance) {
            mInstance = new CardApplication();
        }
        return mInstance;

    }

    public static Context getContext(){
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();


    }



}
