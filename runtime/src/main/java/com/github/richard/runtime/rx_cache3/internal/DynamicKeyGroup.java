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

package com.github.richard.runtime.rx_cache3.internal;

/**
 * Wrapper around the key and the group for those providers which need to handle multiple records in
 * sections, so they need to provide multiple keys organized in groups, such us end points with
 * filtering AND pagination requirements
 */
public class DynamicKeyGroup {
  private final Object dynamicKey;
  private final Object group;

  public DynamicKeyGroup(Object dynamicKey, Object group) {
    this.dynamicKey = dynamicKey;
    this.group = group;
  }

  public Object getDynamicKey() {
    return dynamicKey;
  }

  public Object getGroup() {
    return group;
  }
}
