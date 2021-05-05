package com.nixsolutions.apteka.dao;

import java.util.List;

import com.nixsolutions.apteka.model.Entity;

public interface Dao<E> {

    E create(E entity);

    void update(E entity);

    void delete(E entity);

    E findById(Long id);

    List<E> findAll();
}
