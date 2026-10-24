package com.tecylab.order_api.error;

public class ProductNotFoudException extends RuntimeException {
    public ProductNotFoudException(String message) {
        super(message);
    }
}