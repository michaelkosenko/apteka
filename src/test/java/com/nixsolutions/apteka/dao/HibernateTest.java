package com.nixsolutions.apteka.dao;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Test;

import com.nixsolutions.apteka.model.Doctor;
import com.nixsolutions.apteka.model.Medicine;
import com.nixsolutions.apteka.model.Prescription;
import com.nixsolutions.apteka.model.PrescriptionLine;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HibernateTest {
    @Test
    public void testBootstrap() throws Exception {
        log.debug("Yo!");

        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().build();
        MetadataSources metadataSources = new MetadataSources(serviceRegistry);
        metadataSources.addAnnotatedClass(Doctor.class);
        metadataSources.addAnnotatedClass(Medicine.class);
        metadataSources.addAnnotatedClass(Prescription.class);
        metadataSources.addAnnotatedClass(PrescriptionLine.class);

        Metadata metadata = metadataSources.buildMetadata();

        SessionFactory sessionFactory = metadata.buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        Doctor doctor = Doctor.builder().name("mak").build();

        session.beginTransaction();
        session.save(doctor);
        session.getTransaction().commit();

    }
}
