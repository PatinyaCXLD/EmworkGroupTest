package com.test.data.services;

public interface Filterer<T> {
    boolean filter(T t);
}
