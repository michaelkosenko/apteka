package com.nixsolutions.apteka.dao.hibernate;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.nixsolutions.apteka.dao.Dao;

public abstract class HibernateGenericDao<E> implements Dao<E> {

    private final SessionFactory sessionFactory;

    public HibernateGenericDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    abstract protected Class<E> getEntityClass();

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    @Override
    public E create(E entity) {
        getCurrentSession().save(entity);
        return entity;
    }

    @Override
    public void update(E entity) {
        getCurrentSession().update(entity);

    }

    @Override
    public void delete(E entity) {
        getCurrentSession().remove(entity);
    }

    @Override
    public E findById(Long id) {
        return (E) getCurrentSession().get(getEntityClass(), id);

    }

    @Override
    public List<E> findAll() {
        CriteriaBuilder criteriaBuilder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<E> query = criteriaBuilder.createQuery(getEntityClass());
        return getCurrentSession().createQuery(query).getResultList();
    }

}