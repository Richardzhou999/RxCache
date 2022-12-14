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

package com.github.richard.core.rx_cache3.internal.cache;

import com.github.richard.core.rx_cache3.internal.Locale;
import com.github.richard.core.rx_cache3.internal.Memory;
import com.github.richard.core.rx_cache3.internal.Persistence;
import com.github.richard.core.rx_cache3.internal.Record;

import javax.inject.Inject;


public final class SaveRecord extends Action {
  private final Integer maxMgPersistenceCache;
  private final EvictExpirableRecordsPersistence
      evictExpirableRecordsPersistence;
  private final String encryptKey;

  @Inject public SaveRecord(Memory memory, Persistence persistence, Integer maxMgPersistenceCache,
                            EvictExpirableRecordsPersistence evictExpirableRecordsPersistence, String encryptKey) {
    super(memory, persistence);
    this.maxMgPersistenceCache = maxMgPersistenceCache;
    this.evictExpirableRecordsPersistence = evictExpirableRecordsPersistence;
    this.encryptKey = encryptKey;
  }

  void save(final String providerKey, final String dynamicKey, final String dynamicKeyGroup,
      final Object data, final Long lifeTime, final boolean isExpirable,
      final boolean isEncrypted) {
    String composedKey = composeKey(providerKey, dynamicKey, dynamicKeyGroup);

    Record record = new Record(data, isExpirable, lifeTime);
    memory.put(composedKey, record);

    if (persistence.storedMB() >= maxMgPersistenceCache) {
      System.out.println(Locale.RECORD_CAN_NOT_BE_PERSISTED_BECAUSE_WOULD_EXCEED_THRESHOLD_LIMIT);
    } else {
      persistence.saveRecord(composedKey, record, isEncrypted, encryptKey);
    }

    evictExpirableRecordsPersistence.startTaskIfNeeded(isEncrypted);
  }
}
