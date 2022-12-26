package com.github.richard.core.rx_cache3.internal.cache;

import com.github.richard.core.rx_cache3.internal.Memory;
import com.github.richard.core.rx_cache3.internal.Persistence;

import javax.inject.Provider;

import dagger.internal.Factory;

/**
 * @Author Charlie
 * @Date 2022/9/20 16:12
 */
public final class EvictRecord_Factory implements Factory<EvictRecord> {
    private final Provider<Memory> memoryProvider;
    private final Provider<Persistence> persistenceProvider;

    public EvictRecord_Factory(Provider<Memory> memoryProvider, Provider<Persistence> persistenceProvider) {
        this.memoryProvider = memoryProvider;
        this.persistenceProvider = persistenceProvider;
    }

    @Override
    public EvictRecord get() {
        return new EvictRecord((Memory)this.memoryProvider.get(), (Persistence)this.persistenceProvider.get());
    }

    public static EvictRecord_Factory create(Provider<Memory> memoryProvider, Provider<Persistence> persistenceProvider) {
        return new EvictRecord_Factory(memoryProvider, persistenceProvider);
    }
}