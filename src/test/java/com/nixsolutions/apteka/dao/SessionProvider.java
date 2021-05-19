package com.nixsolutions.apteka.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SessionProvider implements TestRule {

    private static SessionProvider instance;

    @Getter
    private SessionFactory sessionFactory;

    @Override
    public Statement apply(Statement base, Description description) {

        return new Statement() {

            @Override
            public void evaluate() throws Throwable {
                Transaction transaction = null;
                try (Session session = instance.getSessionFactory()
                        .getCurrentSession()) {
                    transaction = session.beginTransaction();
                    base.evaluate();
                    session.getTransaction().commit();
                } catch (Exception e) {
                    transaction = instance.getSessionFactory()
                            .getCurrentSession().getTransaction();
                    if (transaction != null && transaction.isActive()) {
                        transaction.rollback();
                    }
                    throw e;
                }
            }
        };
    }

    private void init() {
        if (sessionFactory == null) {
            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .configure().build();
            MetadataSources metadataSources = new MetadataSources(
                    serviceRegistry);
            Metadata metadata = metadataSources.buildMetadata();
            sessionFactory = metadata.buildSessionFactory();

        }

        sessionFactory.getCache().evictAll();
    }

    public static SessionProvider getInstance() {
        if (instance == null) {
            instance = new SessionProvider();
        }
        instance.init();
        return instance;
    }


}
