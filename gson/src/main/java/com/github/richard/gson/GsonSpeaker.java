package com.github.richard.gson;

/**
 * @Author Charlie
 * @Date 2022/9/22 9:46
 */

import com.github.richard.api.JolyglotGenerics;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Gson implementation of Jolyglot
 */
public class GsonSpeaker implements JolyglotGenerics {
    private final Gson gson;

    public GsonSpeaker(Gson gson) {
        this.gson = gson;
    }

    public GsonSpeaker() {
        this.gson = new Gson();
    }

    /**
     * {@inheritDoc}
     */
    @Override public String toJson(Object src) {
        return gson.toJson(src);
    }

    /**
     * {@inheritDoc}
     */
    @Override public String toJson(Object src, Type typeOfSrc) {
        return gson.toJson(src, typeOfSrc);
    }

    /**
     * {@inheritDoc}
     */
    @Override public <T> T fromJson(String json, Class<T> classOfT) throws RuntimeException {
        return gson.fromJson(json, classOfT);
    }

    /**
     * {@inheritDoc}
     */
    @Override public <T> T fromJson(String json, Type typeOfT) throws RuntimeException {
        return gson.fromJson(json, typeOfT);
    }

    /**
     * {@inheritDoc}
     */
    @Override public <T> T fromJson(File file, Class<T> classOfT) throws RuntimeException {
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file.getAbsoluteFile()));
            T object =  gson.fromJson(reader, classOfT);
            reader.close();
            return object;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException i) {}
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override public <T> T fromJson(File file, Type typeOfT) throws RuntimeException {
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file.getAbsoluteFile()));
            T object =  gson.fromJson(reader, typeOfT);
            reader.close();
            return object;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException i) {}
            }
        }
    }

    @Override public GenericArrayType arrayOf(Type componentType) {
        return Types.arrayOf(componentType);
    }

    /**
     * {@inheritDoc}
     */
    @Override public ParameterizedType newParameterizedType(Type rawType, Type... typeArguments) {
        return Types.newParameterizedType(rawType, typeArguments);
    }

}

