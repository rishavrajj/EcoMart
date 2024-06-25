package com.shopping.ecomart.exception;

public class ResourceAlreadyExistException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    String resourceName;
    String field;
    String fieldName;

    public ResourceAlreadyExistException() {
    }

    public ResourceAlreadyExistException(String resourceName, String field, String fieldName) {
        super(String.format("%s already exists with %s: %s", resourceName, field, fieldName));
        this.resourceName = resourceName;
        this.field = field;
        this.fieldName = fieldName;
    }
}
