package com.projectasmag.asmag.dao.impl;

import com.projectasmag.asmag.dao.LocationDao;
import com.projectasmag.asmag.model.company.Location;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class LocationDaoImpl implements LocationDao {
    @PersistenceContext
    private EntityManager em;
    @Override
    public Location save(Location location) {
        em.persist(location);
        return location;
    }

    @Override
    public Location update(Location location) {
        return em.merge(location);
    }

    @Override
    public List<Location> findAll() {
        return em.createQuery("from Location", Location.class).getResultList();
    }

    @Override
    public Optional<Location> findById(UUID id) {
        return Optional.ofNullable(em.find(Location.class, id));
    }

    @Override
    public void deleteById(UUID id) {
        em.remove(em.find(Location.class, id));
    }
}
