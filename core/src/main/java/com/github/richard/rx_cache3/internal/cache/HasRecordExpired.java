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

package com.github.richard.rx_cache3.internal.cache;

import com.github.richard.rx_cache3.internal.Record;

import javax.inject.Inject;

public final class HasRecordExpired {

  public @Inject HasRecordExpired() {}

  public boolean hasRecordExpired(Record record) {
    long now = System.currentTimeMillis();

    Long lifeTime = record.getLifeTime();
    if (lifeTime == null) {
      return false;
    }

    long expirationDate = record.getTimeAtWhichWasPersisted() + lifeTime;
    return now > expirationDate;
  }
}