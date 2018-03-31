package by.samsolutions.internship.java.mygoals.dao.jdbc;

import by.samsolutions.internship.java.mygoals.domain.Entity;

import java.util.List;

public interface DAO <T extends Entity> {
    List<T> findAll();
    T findById(int id);
    void insert(T goal);
    void update(T goal);
    void delete(int id);
    List <T> findAllByOwnerID(int idOwner);
}
