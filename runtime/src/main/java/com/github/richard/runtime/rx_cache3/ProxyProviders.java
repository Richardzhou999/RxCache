/*
 * Copyright 2015 Victor Albertos
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.richard.runtime.rx_cache3;
import com.github.richard.core.MigrationCache;

import com.github.richard.core.rx_cache3.DaggerRxCacheComponent;
import com.github.richard.core.rx_cache3.ProcessorProviders;
import com.github.richard.core.rx_cache3.RxCacheModule3;
import com.github.richard.core.rx_cache3.internal.Locale;
import com.github.richard.runtime.rx_cache3.internal.EncryptKey;
import com.github.richard.runtime.rx_cache3.internal.Migration;
import com.github.richard.runtime.rx_cache3.internal.SchemeMigration;


import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Supplier;


public final class ProxyProviders implements InvocationHandler {
  private final ProcessorProviders processorProviders;
  private final ProxyTranslator proxyTranslator;

  public ProxyProviders(RxCache3.Builder builder, Class<?> providersClass) {
    processorProviders = DaggerRxCacheComponent.builder()
        .rxCacheModule(new RxCacheModule3(builder.getCacheDirectory(),
            builder.useExpiredDataIfLoaderNotAvailable(),
            builder.getMaxMBPersistenceCache(), getEncryptKey(providersClass),
            getMigrations(providersClass), builder.getJolyglot()))
        .build().providers();

    proxyTranslator = new ProxyTranslator();
  }

  public String getEncryptKey(Class<?> providersClass) {
    EncryptKey encryptKey = providersClass.getAnnotation(EncryptKey.class);
    if (encryptKey == null) {
      return null;
    }
    return encryptKey.value();
  }

  public List<MigrationCache> getMigrations(Class<?> providersClass) {
    List<MigrationCache> migrations = new ArrayList<>();

    Annotation annotation = providersClass.getAnnotation(SchemeMigration.class);
    if (annotation == null) {
      return migrations;
    }

    SchemeMigration schemeMigration = (SchemeMigration) annotation;

    for (Migration migration : schemeMigration.value()) {
      migrations.add(new MigrationCache(migration.version(),
          migration.evictClasses()));
    }

    return migrations;
  }

  @Override public Object invoke(final Object proxy, final Method method, final Object[] args)
      throws Throwable {
    return Observable.defer(new Supplier<ObservableSource<?>>() {
      @Override
      public ObservableSource<?> get() throws Throwable {
        Observable<Object> observable =
                processorProviders.process(proxyTranslator.processMethod(method, args));
        Class<?> methodType = method.getReturnType();

        if (methodType == Observable.class) {
          return Observable.just(observable);
        }

        if (methodType == Single.class) {
          return Observable.just(Single.fromObservable(observable));
        }

        if (methodType == Maybe.class) {
          return Observable.just(Maybe.fromSingle(Single.fromObservable(observable)));
        }

        if (method.getReturnType() == io.reactivex.Flowable.class) {
          return Observable.just(observable.toFlowable(BackpressureStrategy.MISSING));
        }

        String errorMessage = method.getName() + Locale.INVALID_RETURN_TYPE;
        throw new RuntimeException(errorMessage);
      }
    }).blockingFirst();
  }

  Observable<Void> evictAll() {
    return processorProviders.evictAll();
  }
}