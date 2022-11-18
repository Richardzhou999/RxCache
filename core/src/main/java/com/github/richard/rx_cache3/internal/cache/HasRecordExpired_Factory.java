package com.github.richard.rx_cache3.internal.cache;

import dagger.internal.Factory;

/**
 * @Author Charlie
 * @Date 2022/9/20 16:26
 */
public final class HasRecordExpired_Factory implements Factory<HasRecordExpired> {
    private static final HasRecordExpired_Factory INSTANCE = new HasRecordExpired_Factory();

    public HasRecordExpired_Factory() {
    }

    @Override
    public HasRecordExpired get() {
        return new HasRecordExpired();
    }

    public static HasRecordExpired_Factory create() {
        return INSTANCE;
    }
}
