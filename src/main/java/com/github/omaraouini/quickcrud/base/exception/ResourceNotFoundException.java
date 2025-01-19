package com.github.omaraouini.quickcrud.base.exception;

/**
 * @author aouin
 * Date: 04/03/2023
 * Time: 16:26
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
