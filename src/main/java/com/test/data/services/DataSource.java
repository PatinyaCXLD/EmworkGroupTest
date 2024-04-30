package com.test.data.services;

public interface DataSource<T> {
    T readData();
    void writeData(T t);
}
