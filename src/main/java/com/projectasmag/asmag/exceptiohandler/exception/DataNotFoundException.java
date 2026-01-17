package com.projectasmag.asmag.exceptiohandler.exception;

import java.util.UUID;

public class DataNotFoundException extends RuntimeException {
    private final UUID id;

    public DataNotFoundException(String message, UUID id) {
        super(message);
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
