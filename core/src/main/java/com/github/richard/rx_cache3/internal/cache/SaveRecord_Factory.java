package com.github.richard.rx_cache3.internal.cache;

import com.github.richard.rx_cache3.internal.Memory;
import com.github.richard.rx_cache3.internal.Persistence;

import javax.inject.Provider;

import dagger.internal.Factory;

/**
 * @Author Charlie
 * @Date 2022/9/20 16:11
 */
public final class SaveRecord_Factory implements Factory<SaveRecord> {
    private final Provider<Memory> memoryProvider;
    private final Provider<Persistence> persistenceProvider;
    private final Provider<Integer> maxMgPersistenceCacheProvider;
    private final Provider<EvictExpirableRecordsPersistence> evictExpirableRecordsPersistenceProvider;
    private final Provider<String> encryptKeyProvider;

    public SaveRecord_Factory(Provider<Memory> memoryProvider, Provider<Persistence> persistenceProvider, Provider<Integer> maxMgPersistenceCacheProvider, Provider<EvictExpirableRecordsPersistence> evictExpirableRecordsPersistenceProvider, Provider<String> encryptKeyProvider) {
        this.memoryProvider = memoryProvider;
        this.persistenceProvider = persistenceProvider;
        this.maxMgPersistenceCacheProvider = maxMgPersistenceCacheProvider;
        this.evictExpirableRecordsPersistenceProvider = evictExpirableRecordsPersistenceProvider;
        this.encryptKeyProvider = encryptKeyProvider;
    }

    @Override
    public SaveRecord get() {
        return new SaveRecord((Memory)this.memoryProvider.get(), (Persistence)this.persistenceProvider.get(), (Integer)this.maxMgPersistenceCacheProvider.get(), (EvictExpirableRecordsPersistence)this.evictExpirableRecordsPersistenceProvider.get(), (String)this.encryptKeyProvider.get());
    }

    public static SaveRecord_Factory create(Provider<Memory> memoryProvider, Provider<Persistence> persistenceProvider, Provider<Integer> maxMgPersistenceCacheProvider, Provider<EvictExpirableRecordsPersistence> evictExpirableRecordsPersistenceProvider, Provider<String> encryptKeyProvider) {
        return new SaveRecord_Factory(memoryProvider, persistenceProvider, maxMgPersistenceCacheProvider, evictExpirableRecordsPersistenceProvider, encryptKeyProvider);
    }
}
