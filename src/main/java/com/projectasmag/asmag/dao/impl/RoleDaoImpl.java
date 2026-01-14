package com.projectasmag.asmag.dao.impl;

import com.projectasmag.asmag.dao.RoleDao;
import com.projectasmag.asmag.model.company.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class RoleDaoImpl implements RoleDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Role insert(Role role) {
        em.persist(role);
        return role;
    }

    @Override
    public Role update(Role role) {
        return em.merge(role);
    }

    @Override
    public List<Role> findAll() {
        String hql = "SELECT r FROM Role r";
        List<Role> roles = em.createQuery(hql, Role.class).getResultList();
        return roles;
    }

    @Override
    public Optional<Role> findById(UUID id) {
//        Role role = em.find(Role.class, id);
        String hql = "SELECT r FROM Role r WHERE r.id = :id";
        Role role = em.createQuery(hql, Role.class).setParameter("id", id).getSingleResult();
        return Optional.ofNullable(role);
    }

    @Override
    public void deleteById(UUID id) {
        Role role = em.find(Role.class, id);
        em.remove(role);
    }
}
