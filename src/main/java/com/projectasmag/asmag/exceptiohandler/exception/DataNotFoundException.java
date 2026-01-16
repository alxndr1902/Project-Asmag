package com.projectasmag.asmag.exceptiohandler.exception;

public class DataNotFoundException extends RuntimeException {
    private final String id;

    public DataNotFoundException(String message, String id) {
        super(message);
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
