package com.github.richard.core.rx_cache2.internal.cache;

import com.github.richard.core.rx_cache2.internal.Memory;
import com.github.richard.core.rx_cache2.internal.Persistence;

import javax.inject.Provider;

import dagger.internal.Factory;

/**
 * @Author Charlie
 * @Date 2022/9/20 16:24
 */
public final class EvictExpirableRecordsPersistence_Factory implements Factory<EvictExpirableRecordsPersistence> {
    private final Provider<Memory> memoryProvider;
    private final Provider<Persistence> persistenceProvider;
    private final Provider<Integer> maxMgPersistenceCacheProvider;
    private final Provider<String> encryptKeyProvider;

    public EvictExpirableRecordsPersistence_Factory(Provider<Memory> memoryProvider, Provider<Persistence> persistenceProvider, Provider<Integer> maxMgPersistenceCacheProvider, Provider<String> encryptKeyProvider) {
        this.memoryProvider = memoryProvider;
        this.persistenceProvider = persistenceProvider;
        this.maxMgPersistenceCacheProvider = maxMgPersistenceCacheProvider;
        this.encryptKeyProvider = encryptKeyProvider;
    }

    @Override
    public EvictExpirableRecordsPersistence get() {
        return new EvictExpirableRecordsPersistence((Memory)this.memoryProvider.get(), (Persistence)this.persistenceProvider.get(), (Integer)this.maxMgPersistenceCacheProvider.get(), (String)this.encryptKeyProvider.get());
    }

    public static EvictExpirableRecordsPersistence_Factory create(Provider<Memory> memoryProvider, Provider<Persistence> persistenceProvider, Provider<Integer> maxMgPersistenceCacheProvider, Provider<String> encryptKeyProvider) {
        return new EvictExpirableRecordsPersistence_Factory(memoryProvider, persistenceProvider, maxMgPersistenceCacheProvider, encryptKeyProvider);
    }
}
