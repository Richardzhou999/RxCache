package com.github.richard.core.rx_cache2.internal.cache;

import com.github.richard.core.rx_cache2.internal.Memory;
import com.github.richard.core.rx_cache2.internal.Persistence;

import javax.inject.Provider;

import dagger.internal.Factory;

/**
 * @Author Charlie
 * @Date 2022/9/20 16:24
 */
public final class EvictExpiredRecordsPersistence_Factory implements Factory<EvictExpiredRecordsPersistence> {
    private final Provider<Memory> memoryProvider;
    private final Provider<Persistence> persistenceProvider;
    private final Provider<HasRecordExpired> hasRecordExpiredProvider;
    private final Provider<String> encryptKeyProvider;

    public EvictExpiredRecordsPersistence_Factory(Provider<Memory> memoryProvider, Provider<Persistence> persistenceProvider, Provider<HasRecordExpired> hasRecordExpiredProvider, Provider<String> encryptKeyProvider) {
        this.memoryProvider = memoryProvider;
        this.persistenceProvider = persistenceProvider;
        this.hasRecordExpiredProvider = hasRecordExpiredProvider;
        this.encryptKeyProvider = encryptKeyProvider;
    }

    @Override
    public EvictExpiredRecordsPersistence get() {
        return new EvictExpiredRecordsPersistence((Memory)this.memoryProvider.get(), (Persistence)this.persistenceProvider.get(), (HasRecordExpired)this.hasRecordExpiredProvider.get(), (String)this.encryptKeyProvider.get());
    }

    public static EvictExpiredRecordsPersistence_Factory create(Provider<Memory> memoryProvider, Provider<Persistence> persistenceProvider, Provider<HasRecordExpired> hasRecordExpiredProvider, Provider<String> encryptKeyProvider) {
        return new EvictExpiredRecordsPersistence_Factory(memoryProvider, persistenceProvider, hasRecordExpiredProvider, encryptKeyProvider);
    }
}
