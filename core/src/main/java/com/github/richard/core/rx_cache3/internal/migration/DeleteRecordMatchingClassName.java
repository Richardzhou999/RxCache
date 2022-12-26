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

package com.github.richard.core.rx_cache3.internal.migration;

import com.github.richard.core.rx_cache3.internal.Persistence;
import com.github.richard.core.rx_cache3.internal.Record;
import java.util.List;
import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public final class DeleteRecordMatchingClassName {
  private final Persistence persistence;
  private final String encryptKey;
  private List<Class> classes;

  @Inject public DeleteRecordMatchingClassName(Persistence persistence, String encryptKey) {
    this.persistence = persistence;
    this.encryptKey = encryptKey;
  }

  public DeleteRecordMatchingClassName with(List<Class> classes) {
    this.classes = classes;
    return this;
  }

  public Observable<Integer> react() {
    if (classes.isEmpty()) {
      return Observable.just(1);
    }

    List<String> allKeys = persistence.allKeys();

    for (String key : allKeys) {
      Record record = persistence.retrieveRecord(key, false, encryptKey);

      if (record == null) {
        record = persistence.retrieveRecord(key, true, encryptKey);
      }

      if (evictRecord(record)) {
        persistence.evict(key);
      }
    }

    return Observable.just(1);
  }

  private boolean evictRecord(Record record) {
    String candidate = record.getDataClassName();

    for (Class aClass : classes) {
      String className = aClass.getName();
      if (className.equals(candidate)) {
        return true;
      }
    }

    return false;
  }
}
