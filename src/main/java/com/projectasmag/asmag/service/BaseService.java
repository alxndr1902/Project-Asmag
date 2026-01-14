package com.projectasmag.asmag.service;

import com.projectasmag.asmag.model.BaseModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class BaseService {
    @PersistenceContext
    protected EntityManager em;

    public <T extends BaseModel> void createBaseModel(T model) {
        model.setId(UUID.randomUUID());
        model.setCreatedAt(LocalDateTime.now());
        model.setCreatedBy(UUID.randomUUID());
    }

    public <T extends BaseModel> void update(T model) {
        model.setUpdatedAt(LocalDateTime.now());
        model.setUpdatedBy(UUID.randomUUID());
    }
}
