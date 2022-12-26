package com.github.richard.core.rx_cache2.internal.encrypt;

import javax.inject.Provider;

import dagger.internal.Factory;

/**
 * @Author Charlie
 * @Date 2022/9/20 16:13
 */
public final class FileEncryptor_Factory implements Factory<FileEncryptor> {
    private final Provider<Encryptor> encryptorProvider;

    public FileEncryptor_Factory(Provider<Encryptor> encryptorProvider) {
        this.encryptorProvider = encryptorProvider;
    }

    @Override
    public FileEncryptor get() {
        return new FileEncryptor((Encryptor)this.encryptorProvider.get());
    }

    public static FileEncryptor_Factory create(Provider<Encryptor> encryptorProvider) {
        return new FileEncryptor_Factory(encryptorProvider);
    }
}
