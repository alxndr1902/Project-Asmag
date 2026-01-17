package com.projectasmag.asmag.exceptiohandler.exception;

import java.util.Set;
import java.util.UUID;

public class MultipleDataNotFoundException extends RuntimeException {
    private final Set<UUID> ids;

    public MultipleDataNotFoundException(String message, Set<UUID> ids) {
        super(message);
        this.ids = ids;
    }

    public Set<UUID> getIds() {
        return this.ids;
    }
}
