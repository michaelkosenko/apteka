package com.nixsolutions.apteka.dao;

import com.nixsolutions.apteka.model.Entity;

public interface Dao<E extends Entity> {
    
    E findById(Long id);
    
    E create(E entity);

}
