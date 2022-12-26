package com.github.richard.core.rx_cache3.internal.cache;

import com.github.richard.core.rx_cache3.internal.Memory;
import com.github.richard.core.rx_cache3.internal.Persistence;

import javax.inject.Provider;

import dagger.internal.Factory;

/**
 * @Author Charlie
 * @Date 2022/9/20 16:14
 */
public final class RetrieveRecord_Factory implements Factory<RetrieveRecord> {
    private final Provider<Memory> memoryProvider;
    private final Provider<Persistence> persistenceProvider;
    private final Provider<EvictRecord> evictRecordProvider;
    private final Provider<HasRecordExpired> hasRecordExpiredProvider;
    private final Provider<String> encryptKeyProvider;

    public RetrieveRecord_Factory(Provider<Memory> memoryProvider, Provider<Persistence> persistenceProvider, Provider<EvictRecord> evictRecordProvider, Provider<HasRecordExpired> hasRecordExpiredProvider, Provider<String> encryptKeyProvider) {
        this.memoryProvider = memoryProvider;
        this.persistenceProvider = persistenceProvider;
        this.evictRecordProvider = evictRecordProvider;
        this.hasRecordExpiredProvider = hasRecordExpiredProvider;
        this.encryptKeyProvider = encryptKeyProvider;
    }

    @Override
    public RetrieveRecord get() {
        return new RetrieveRecord((Memory)this.memoryProvider.get(), (Persistence)this.persistenceProvider.get(), (EvictRecord)this.evictRecordProvider.get(), (HasRecordExpired)this.hasRecordExpiredProvider.get(), (String)this.encryptKeyProvider.get());
    }

    public static RetrieveRecord_Factory create(Provider<Memory> memoryProvider, Provider<Persistence> persistenceProvider, Provider<EvictRecord> evictRecordProvider, Provider<HasRecordExpired> hasRecordExpiredProvider, Provider<String> encryptKeyProvider) {
        return new RetrieveRecord_Factory(memoryProvider, persistenceProvider, evictRecordProvider, hasRecordExpiredProvider, encryptKeyProvider);
    }
}