//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.github.richard.rx_cache3;

import com.github.richard.rx_cache3.internal.Disk_Factory;
import com.github.richard.MigrationCache;
import com.github.richard.api.JolyglotGenerics;
import com.github.richard.rx_cache3.internal.Memory;
import com.github.richard.rx_cache3.internal.Persistence;
import com.github.richard.rx_cache3.internal.cache.EvictExpirableRecordsPersistence;
import com.github.richard.rx_cache3.internal.cache.EvictExpirableRecordsPersistence_Factory;
import com.github.richard.rx_cache3.internal.cache.EvictExpiredRecordsPersistence;
import com.github.richard.rx_cache3.internal.cache.EvictExpiredRecordsPersistence_Factory;
import com.github.richard.rx_cache3.internal.cache.EvictRecord_Factory;
import com.github.richard.rx_cache3.internal.cache.GetDeepCopy;
import com.github.richard.rx_cache3.internal.cache.HasRecordExpired_Factory;
import com.github.richard.rx_cache3.internal.cache.RetrieveRecord_Factory;
import com.github.richard.rx_cache3.internal.cache.SaveRecord_Factory;
import com.github.richard.rx_cache3.internal.cache.TwoLayersCache;
import com.github.richard.rx_cache3.internal.cache.TwoLayersCache_Factory;
import com.github.richard.rx_cache3.internal.encrypt.Encryptor;
import com.github.richard.rx_cache3.internal.encrypt.FileEncryptor_Factory;
import com.github.richard.rx_cache3.internal.migration.DoMigrations;

import java.io.File;
import java.util.List;

import javax.inject.Provider;

import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;

public final class DaggerRxCacheComponent implements RxCacheComponent3 {
    private RxCacheModule3 rxCacheModule;
    private Provider<Memory> provideMemoryProvider;
    private Provider<File> provideCacheDirectoryProvider;
    private Provider<Encryptor> provideEncryptorProvider;
    private FileEncryptor_Factory fileEncryptorProvider;
    private Provider<JolyglotGenerics> provideJolyglotProvider;
    private Disk_Factory diskProvider;
    private Provider<Persistence> providePersistenceProvider;
    private EvictRecord_Factory evictRecordProvider;
    private Provider<String> provideEncryptKeyProvider;
    private RetrieveRecord_Factory retrieveRecordProvider;
    private Provider<Integer> maxMbPersistenceCacheProvider;
    private Provider<EvictExpirableRecordsPersistence> evictExpirableRecordsPersistenceProvider;
    private SaveRecord_Factory saveRecordProvider;
    private Provider<TwoLayersCache> twoLayersCacheProvider;
    private Provider<Boolean> useExpiredDataIfLoaderNotAvailableProvider;
    private Provider<EvictExpiredRecordsPersistence> evictExpiredRecordsPersistenceProvider;
    private Provider<List<MigrationCache>> provideMigrationsProvider;

    private DaggerRxCacheComponent(Builder builder) {
        this.initialize(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    private GetDeepCopy getGetDeepCopy() {
        return new GetDeepCopy((Memory)this.provideMemoryProvider.get(), (Persistence)this.providePersistenceProvider.get(), (JolyglotGenerics)this.provideJolyglotProvider.get());
    }

    private DoMigrations getDoMigrations() {
        return new DoMigrations((Persistence)this.providePersistenceProvider.get(), (List)this.provideMigrationsProvider.get(), (String)this.provideEncryptKeyProvider.get());
    }

    private ProcessorProvidersBehaviour getProcessorProvidersBehaviour() {
        return new ProcessorProvidersBehaviour((TwoLayersCache)this.twoLayersCacheProvider.get(), (Boolean)this.useExpiredDataIfLoaderNotAvailableProvider.get(), (EvictExpiredRecordsPersistence)this.evictExpiredRecordsPersistenceProvider.get(), this.getGetDeepCopy(), this.getDoMigrations());
    }

    private void initialize(Builder builder) {
        this.rxCacheModule = builder.rxCacheModule;
        this.provideMemoryProvider = DoubleCheck.provider(RxCacheModule3_ProvideMemoryFactory.create(builder.rxCacheModule));
        this.provideCacheDirectoryProvider = DoubleCheck.provider(RxCacheModule3_ProvideCacheDirectoryFactory.create(builder.rxCacheModule));
        this.provideEncryptorProvider = DoubleCheck.provider(RxCacheModule3_ProvideEncryptorFactory.create(builder.rxCacheModule));
        this.fileEncryptorProvider = FileEncryptor_Factory.create(this.provideEncryptorProvider);
        this.provideJolyglotProvider = DoubleCheck.provider(RxCacheModule3_ProvideJolyglotFactory.create(builder.rxCacheModule));
        this.diskProvider = Disk_Factory.create(this.provideCacheDirectoryProvider, this.fileEncryptorProvider, this.provideJolyglotProvider);
        this.providePersistenceProvider = DoubleCheck.provider(RxCacheModule3_ProvidePersistenceFactory.create(builder.rxCacheModule, this.diskProvider));
        this.evictRecordProvider = EvictRecord_Factory.create(this.provideMemoryProvider, this.providePersistenceProvider);
        this.provideEncryptKeyProvider = DoubleCheck.provider(RxCacheModule3_ProvideEncryptKeyFactory.create(builder.rxCacheModule));
        this.retrieveRecordProvider = RetrieveRecord_Factory.create(this.provideMemoryProvider, this.providePersistenceProvider, this.evictRecordProvider, HasRecordExpired_Factory.create(), this.provideEncryptKeyProvider);
        this.maxMbPersistenceCacheProvider = DoubleCheck.provider(RxCacheModule3_MaxMbPersistenceCacheFactory.create(builder.rxCacheModule));
        this.evictExpirableRecordsPersistenceProvider = DoubleCheck.provider(EvictExpirableRecordsPersistence_Factory.create(this.provideMemoryProvider, this.providePersistenceProvider, this.maxMbPersistenceCacheProvider, this.provideEncryptKeyProvider));
        this.saveRecordProvider = SaveRecord_Factory.create(this.provideMemoryProvider, this.providePersistenceProvider, this.maxMbPersistenceCacheProvider, this.evictExpirableRecordsPersistenceProvider, this.provideEncryptKeyProvider);
        this.twoLayersCacheProvider = DoubleCheck.provider(TwoLayersCache_Factory.create(this.evictRecordProvider, this.retrieveRecordProvider, this.saveRecordProvider));
        this.useExpiredDataIfLoaderNotAvailableProvider = DoubleCheck.provider(RxCacheModule3_UseExpiredDataIfLoaderNotAvailableFactory.create(builder.rxCacheModule));
        this.evictExpiredRecordsPersistenceProvider = DoubleCheck.provider(EvictExpiredRecordsPersistence_Factory.create(this.provideMemoryProvider, this.providePersistenceProvider, HasRecordExpired_Factory.create(), this.provideEncryptKeyProvider));
        this.provideMigrationsProvider = DoubleCheck.provider(RxCacheModule3_ProvideMigrationsFactory.create(builder.rxCacheModule));
    }

    @Override
    public ProcessorProviders providers() {
        return RxCacheModule3_ProvideProcessorProvidersFactory.proxyProvideProcessorProviders(this.rxCacheModule, this.getProcessorProvidersBehaviour());
    }

    public static final class Builder {
        private RxCacheModule3 rxCacheModule;

        private Builder() {
        }

        public RxCacheComponent3 build() {
            if (this.rxCacheModule == null) {
                throw new IllegalStateException(RxCacheModule3.class.getCanonicalName() + " must be set");
            } else {
                return new DaggerRxCacheComponent(this);
            }
        }

        public Builder rxCacheModule(RxCacheModule3 rxCacheModule) {
            this.rxCacheModule = (RxCacheModule3)Preconditions.checkNotNull(rxCacheModule);
            return this;
        }
    }
}
