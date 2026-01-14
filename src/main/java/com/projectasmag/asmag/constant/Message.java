package com.projectasmag.asmag.constant;

public enum Message {
    CREATED("Created"), UPDATED("Updated"), DELETED("Updated");

    private final String name;

    Message(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
