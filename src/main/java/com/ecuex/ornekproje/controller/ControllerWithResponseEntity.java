package com.ecuex.ornekproje.controller;

import org.springframework.http.ResponseEntity;

public interface ControllerWithResponseEntity<T, K> {
    ResponseEntity save(T t);

    ResponseEntity get(K k);

    ResponseEntity getAll();

    ResponseEntity put(T t);

    ResponseEntity delete(K k);
}