package com.nixsolutions.apteka.dao.hibernate;

import org.hibernate.SessionFactory;

import com.nixsolutions.apteka.dao.MedicineDao;
import com.nixsolutions.apteka.model.Medicine;

public class HibernateMedicineDao extends HibernateGenericDao<Medicine> implements MedicineDao {

    public HibernateMedicineDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    protected Class<Medicine> getEntityClass() {
        return Medicine.class;
    }

}
