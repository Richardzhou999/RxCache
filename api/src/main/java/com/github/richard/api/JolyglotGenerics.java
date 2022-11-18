package com.github.richard.api;

/**
 * @Author Charlie
 * @Date 2022/9/20 18:48
 */

import java.io.File;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Jolyglot supports for generic types.
 * @see Jolyglot
 */
public interface JolyglotGenerics extends Jolyglot {

    /**
     * This method serializes the specified object, including those of generic types, into its
     * equivalent Json representation. This method must be used if the specified object is a generic
     * type. For non-generic objects, use {@link #toJson(Object)} instead.
     * @param src the object for which JSON representation is to be created
     * @param typeOfSrc The specific genericized type of src.
     * @return Json representation of {@code src}
     */
    String toJson(Object src, Type typeOfSrc);

    /**
     * This method deserializes the specified Json into an object of the specified type. This method
     * is useful if the specified object is a generic type. For non-generic objects, use
     * {@link #fromJson(String, Class)} instead. If you have the Json in a {@link File} instead of
     * a String, use {@link #fromJson(File, Type)} instead.
     */
    <T> T fromJson(String json, Type type) throws RuntimeException;

    /**
     * This method deserializes the Json read from the specified reader into an object of the
     * specified type. This method is useful if the specified object is a generic type. For
     * non-generic objects, use {@link #fromJson(File, Class)} instead. If you have the Json in a
     * String form instead of a {@link File}, use {@link #fromJson(String, Type)} instead.
     * @param <T> the type of the desired object
     * @param file the file producing Json from which the object is to be deserialized
     * @param typeOfT The specific genericized type of src.
     * @return an object of type T from the json.
     */
    <T> T fromJson(File file, Type typeOfT) throws RuntimeException;

    /** Returns an array type whose elements are all instances of {@code componentType}. */
    GenericArrayType arrayOf(Type componentType);

    /**
     * Returns a new parameterized type, applying {@code typeArguments} to {@code rawType}.
     */
    ParameterizedType newParameterizedType(Type rawType, Type... typeArguments);
}
