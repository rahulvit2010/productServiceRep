package com.epam.productservice.exception;

public class ProductCategoryNotFound extends RuntimeException {
    public ProductCategoryNotFound(String message) {
        super(message);
    }

    public ProductCategoryNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductCategoryNotFound(Throwable cause) {
        super(cause);
    }
}
