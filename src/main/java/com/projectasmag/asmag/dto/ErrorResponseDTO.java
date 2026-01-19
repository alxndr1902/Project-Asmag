package com.projectasmag.asmag.dto;

public class ErrorResponseDTO<T> {
    private final T message;

    public ErrorResponseDTO(T message) {
        this.message = message;
    }

    public T getMessage() {
        return message;
    }
}