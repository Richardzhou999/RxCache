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

package com.github.richard.core.rx_cache2;

import com.github.richard.core.MigrationCache;
import com.github.richard.api.JolyglotGenerics;
import com.github.richard.core.rx_cache2.internal.Disk;
import com.github.richard.core.rx_cache2.internal.Memory;
import com.github.richard.core.rx_cache2.internal.Persistence;
import com.github.richard.core.rx_cache2.internal.cache.memory.ReferenceMapMemory;
import com.github.richard.core.rx_cache2.internal.encrypt.BuiltInEncryptor;
import com.github.richard.core.rx_cache2.internal.encrypt.Encryptor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class RxCacheModule2 {
  private final File cacheDirectory;
  private final boolean useExpiredDataIfLoaderNotAvailable;
  private final Integer maxMgPersistenceCache;
  private final String encryptKey;
  private final List<MigrationCache> migrations;
  private final JolyglotGenerics jolyglot;

  public RxCacheModule2(File cacheDirectory, Boolean useExpiredDataIfLoaderNotAvailable,
                        Integer maxMgPersistenceCache,
                        String encryptKey, List<MigrationCache> migrations, JolyglotGenerics jolyglot) {
    this.cacheDirectory = cacheDirectory;
    this.useExpiredDataIfLoaderNotAvailable = useExpiredDataIfLoaderNotAvailable;
    this.maxMgPersistenceCache = maxMgPersistenceCache;
    this.encryptKey = encryptKey;
    this.migrations = migrations;
    this.jolyglot = jolyglot;
  }

  @Singleton @Provides File provideCacheDirectory() {
    return cacheDirectory;
  }

  @Singleton @Provides
  Persistence providePersistence(Disk disk) {
    return disk;
  }

  @Singleton @Provides Boolean useExpiredDataIfLoaderNotAvailable() {
    return useExpiredDataIfLoaderNotAvailable;
  }

  @Singleton @Provides
  Memory provideMemory() {
    return new ReferenceMapMemory();
  }

  @Singleton @Provides Integer maxMbPersistenceCache() {
    return maxMgPersistenceCache != null ? maxMgPersistenceCache : 100;
  }

  @Singleton @Provides Encryptor provideEncryptor() {
    return new BuiltInEncryptor();
  }

  @Singleton @Provides String provideEncryptKey() {
    return encryptKey != null ? encryptKey : "";
  }

  @Singleton @Provides List<MigrationCache> provideMigrations() {
    return migrations != null ? migrations : new ArrayList<MigrationCache>();
  }

  @Singleton @Provides JolyglotGenerics provideJolyglot() {
    return jolyglot;
  }

  @Singleton @Provides
  ProcessorProviders provideProcessorProviders(
          ProcessorProvidersBehaviour processorProvidersBehaviour) {
    return processorProvidersBehaviour;
  }
}
