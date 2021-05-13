package com.nixsolutions.apteka.dao.hibernate;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.nixsolutions.apteka.dao.DoctorDao;
import com.nixsolutions.apteka.model.Doctor;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class HibernateDoctorDao extends HibernateGenericDao<Doctor>
        implements DoctorDao {

    public HibernateDoctorDao(SessionFactory sessionFactory) {
        super(sessionFactory);
        log.debug("!!!! SESSION FACTORY - {}", sessionFactory);
    }

    @Override
    protected Class<Doctor> getEntityClass() {
        return Doctor.class;
    }

    @Override
    @Transactional
    public Doctor findByUsername(String username) {
        try {
            Doctor doctor = getCurrentSession()
                    .createQuery("from Doctor d where d.username = :username",
                            Doctor.class)
                    .setParameter("username", username).getSingleResult();

            return doctor;
        } catch (NoResultException e) {
            return null;
        }
    }

}
