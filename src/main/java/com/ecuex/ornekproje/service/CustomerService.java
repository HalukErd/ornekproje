package com.ecuex.ornekproje.service;

import java.util.List;

public interface CustomerService<T> {
    List<T> getCustomers();

    T saveCustomer(T t);

    T getCustomer(Long tckn);

    T putCustomer(T t);

    boolean deleteCustomer(Long tckn);
}