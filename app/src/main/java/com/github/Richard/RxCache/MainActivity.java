package com.github.Richard.RxCache;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button2 = findViewById(R.id.cache2);
        Button button3 = findViewById(R.id.cache3);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiService service =
                RetrofitManager.INSTANCE.getRetrofit().create(ApiService.class);

                CacheProviders.getLoginCache().getLogin(service.getVerificationCode("13702383169"))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe( new DefaultObserver<BasicResponse>() {
                            @Override
                            public void onNext(BasicResponse basicResponse) {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });

            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiService service =
                        RetrofitManager.INSTANCE.getRetrofit3().create(ApiService.class);
                CacheProviders.getLoginCache3().getLogin3(service.getVerification("13702383169"))
                        .subscribeOn(io.reactivex.rxjava3.schedulers.Schedulers.newThread())
                        .observeOn(io.reactivex.rxjava3.android.schedulers.AndroidSchedulers.mainThread())
                        .subscribe();
            }
        });
    }
}