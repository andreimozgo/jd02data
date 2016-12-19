package by.academy.it.services;

import by.academy.it.entity.AbstractEntity;

public interface Service<T extends AbstractEntity> {

    void createOrUpdate(T t);

    T findEntityById(Integer id);

    void delete(Integer id);
}
