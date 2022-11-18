/*
 * Copyright 2016 Victor Albertos
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

package com.github.richard.rx_cache2.internal.migration;

import com.github.richard.MigrationCache;
import com.github.richard.rx_cache2.internal.Persistence;

import io.reactivex.Observable;

import java.util.List;
import javax.inject.Inject;

final class UpgradeCacheVersion extends CacheVersion {
  private List<MigrationCache> migrations;

  @Inject public UpgradeCacheVersion(Persistence persistence) {
    super(persistence);
  }

  UpgradeCacheVersion with(List<MigrationCache> migrations) {
    this.migrations = migrations;
    return this;
  }

  Observable<Integer> react() {
    if (migrations == null || migrations.isEmpty()) {
      return Observable.just(1);
    }

    MigrationCache migration = migrations.get(migrations.size() - 1);
    persistence.save(KEY_CACHE_VERSION, migration.version(), false, null);

    return Observable.just(1);
  }
}
