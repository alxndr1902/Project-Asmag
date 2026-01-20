package com.projectasmag.asmag.service;

import com.projectasmag.asmag.exceptiohandler.exception.InvalidUUIDException;
import com.projectasmag.asmag.model.BaseModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public abstract class BaseService {
    @PersistenceContext
    protected EntityManager em;

    protected <T extends BaseModel> T prepareCreate(T model) {
        model.setId(UUID.randomUUID());
        model.setCreatedAt(LocalDateTime.now());
        model.setCreatedBy(UUID.randomUUID());
        return model;
    }

    protected <T extends BaseModel> T prepareUpdate(T model) {
        model.setUpdatedAt(LocalDateTime.now());
        model.setUpdatedBy(UUID.randomUUID());
        return model;
    }

    protected UUID getId(String request) {
        try {
            return UUID.fromString(request);
        } catch (IllegalArgumentException e) {
            throw new InvalidUUIDException("Invalid UUID");
        }
    }

    protected LocalDateTime getDate(String request) {
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            LocalDateTime result = LocalDateTime.parse(request, format);
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Invalid Format");
        }
    }
}
