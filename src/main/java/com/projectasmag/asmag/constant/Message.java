package com.projectasmag.asmag.constant;

public enum Message {
    CREATED("Created"), UPDATED("Updated"), DELETED("Updated");

    private String name;

    Message(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
