package com.github.richard.rx_cache2.internal.cache;

import javax.inject.Provider;

import dagger.internal.Factory;

/**
 * @Author Charlie
 * @Date 2022/9/20 16:23
 */
public final class TwoLayersCache_Factory implements Factory<TwoLayersCache> {
    private final Provider<EvictRecord> evictRecordProvider;
    private final Provider<RetrieveRecord> retrieveRecordProvider;
    private final Provider<SaveRecord> saveRecordProvider;

    public TwoLayersCache_Factory(Provider<EvictRecord> evictRecordProvider, Provider<RetrieveRecord> retrieveRecordProvider, Provider<SaveRecord> saveRecordProvider) {
        this.evictRecordProvider = evictRecordProvider;
        this.retrieveRecordProvider = retrieveRecordProvider;
        this.saveRecordProvider = saveRecordProvider;
    }

    public TwoLayersCache get() {
        return new TwoLayersCache((EvictRecord)this.evictRecordProvider.get(), (RetrieveRecord)this.retrieveRecordProvider.get(), (SaveRecord)this.saveRecordProvider.get());
    }

    public static TwoLayersCache_Factory create(Provider<EvictRecord> evictRecordProvider, Provider<RetrieveRecord> retrieveRecordProvider, Provider<SaveRecord> saveRecordProvider) {
        return new TwoLayersCache_Factory(evictRecordProvider, retrieveRecordProvider, saveRecordProvider);
    }
}