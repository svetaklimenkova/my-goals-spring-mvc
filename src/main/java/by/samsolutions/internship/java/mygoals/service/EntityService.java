package by.samsolutions.internship.java.mygoals.service;

import by.samsolutions.internship.java.mygoals.domain.Entity;

import java.util.List;

public interface EntityService<T extends Entity> {
    T findById(int id);
    int create(T goal);
    boolean update(T goal);
    boolean remove(int id);
    List <T> findAllByOwnerID(int idOwner);
}
