package com.ecuex.ornekproje.controller;

import java.util.List;

public interface Controller<T> {
    T save(T t);

    T get(Long tckn);

    List<T> getAll();

    T put(T t);

    boolean delete(Long tckn);
}