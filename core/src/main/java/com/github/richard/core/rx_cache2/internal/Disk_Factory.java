package com.github.richard.core.rx_cache2.internal;

import com.github.richard.api.JolyglotGenerics;
import com.github.richard.core.rx_cache2.internal.encrypt.FileEncryptor;

import java.io.File;

import javax.inject.Provider;
import dagger.internal.Factory;

/**
 * @Author Charlie
 * @Date 2022/9/20 16:07
 */
public final class Disk_Factory implements Factory<Disk> {
    private final Provider<File> cacheDirectoryProvider;
    private final Provider<FileEncryptor> fileEncryptorProvider;
    private final Provider<JolyglotGenerics> jolyglotProvider;

    public Disk_Factory(Provider<File> cacheDirectoryProvider, Provider<FileEncryptor> fileEncryptorProvider, Provider<JolyglotGenerics> jolyglotProvider) {
        this.cacheDirectoryProvider = cacheDirectoryProvider;
        this.fileEncryptorProvider = fileEncryptorProvider;
        this.jolyglotProvider = jolyglotProvider;
    }

    @Override
    public Disk get() {
        return new Disk((File)this.cacheDirectoryProvider.get(), (FileEncryptor)this.fileEncryptorProvider.get(), (JolyglotGenerics)this.jolyglotProvider.get());
    }

    public static Disk_Factory create(Provider<File> cacheDirectoryProvider, Provider<FileEncryptor> fileEncryptorProvider, Provider<JolyglotGenerics> jolyglotProvider) {
        return new Disk_Factory(cacheDirectoryProvider, fileEncryptorProvider, jolyglotProvider);
    }
}
