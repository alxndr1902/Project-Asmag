package com.projectasmag.asmag.dao.impl;

import com.projectasmag.asmag.dao.EmployeeDao;
import com.projectasmag.asmag.model.company.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {
    @PersistenceContext
    private EntityManager em;
    @Override
    public Employee save(Employee employee) {
        em.persist(employee);
        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        return em.merge(employee);
    }

    @Override
    public List<Employee> findAll() {
        return em.createQuery("select e from Employee e", Employee.class).getResultList();
    }

    @Override
    public Optional<Employee> findById(UUID id) {
        return Optional.ofNullable(em.find(Employee.class, id));
    }

    @Override
    public void deleteById(UUID id) {
        em.remove(em.find(Employee.class, id));
    }
}
