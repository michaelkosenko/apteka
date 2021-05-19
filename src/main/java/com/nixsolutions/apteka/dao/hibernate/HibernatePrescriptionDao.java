package com.nixsolutions.apteka.dao.hibernate;

import org.hibernate.SessionFactory;

import com.nixsolutions.apteka.dao.PrescriptionDao;
import com.nixsolutions.apteka.model.Prescription;

public class HibernatePrescriptionDao extends HibernateGenericDao<Prescription> implements PrescriptionDao {

    public HibernatePrescriptionDao(SessionFactory sessionFactory) {
        super(sessionFactory);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected Class<Prescription> getEntityClass() {
        return Prescription.class;
    }

}
