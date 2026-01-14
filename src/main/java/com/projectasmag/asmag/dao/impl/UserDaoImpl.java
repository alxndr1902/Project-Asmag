package com.projectasmag.asmag.dao.impl;

import com.projectasmag.asmag.dao.UserDao;
import com.projectasmag.asmag.model.company.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public User insert(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public User update(User user) {
        return em.merge(user);
    }

    @Override
    public List<User> findAll() {
        String hql = "SELECT u FROM User u";
        List<User> users = em.createQuery(hql, User.class).getResultList();
        return users;
    }

    @Override
    public Optional<User> findById(UUID id) {
        User user = em.find(User.class, id);
        return Optional.ofNullable(user);
    }

    @Override
    public void deleteById(UUID id) {
        User user = em.find(User.class, id);
        em.remove(user);
    }
}
