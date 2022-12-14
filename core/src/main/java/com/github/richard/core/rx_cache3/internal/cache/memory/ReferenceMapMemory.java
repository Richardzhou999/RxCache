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

package com.github.richard.core.rx_cache3.internal.cache.memory;

import com.github.richard.core.rx_cache3.internal.Memory;
import com.github.richard.core.rx_cache3.internal.Record;
import com.github.richard.core.rx_cache3.internal.cache.memory.apache.ReferenceMap;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public final class ReferenceMapMemory implements Memory {
  private final Map<String, Record> referenceMap;

  public ReferenceMapMemory() {
    referenceMap = Collections.synchronizedMap(new ReferenceMap<String, Record>());
  }

  @Override public <T> Record<T> getIfPresent(String key) {
    return referenceMap.get(key);
  }

  @Override public <T> void put(String key, Record<T> record) {
    referenceMap.put(key, record);
  }

  @Override public Set<String> keySet() {
    return referenceMap.keySet();
  }

  @Override public void evict(String key) {
    referenceMap.remove(key);
  }

  @Override public void evictAll() {
    Set<String> keys = referenceMap.keySet();

    synchronized (referenceMap) {
      Iterator iterator = keys.iterator();
      while (iterator.hasNext()) {
        iterator.next();
        iterator.remove();
      }
    }
  }
}
