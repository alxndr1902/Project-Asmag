package com.projectasmag.asmag.dto.loan;

public class CreateLoanResponse {
    private String id;
    private String code;
    private String message;

    public CreateLoanResponse(String id, String code, String message) {
        this.id = id;
        this.code = code;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
