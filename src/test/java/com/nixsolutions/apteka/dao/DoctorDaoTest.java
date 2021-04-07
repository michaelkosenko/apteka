package com.nixsolutions.apteka.dao;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.Test;

import com.nixsolutions.apteka.model.Doctor;

import lombok.SneakyThrows;

public class DoctorDaoTest extends DataSourceBasedDBTestCase {
    private JdbcDataSource ds;

    public void testFindById() {
        JdbcGenericDao<Doctor> dao = new DoctorDao(getDataSource());
        Doctor doctor = dao.findById(Long.valueOf(1L));
        assertNotNull(doctor);
        assertEquals(Long.valueOf(1L), doctor.getId());
        assertEquals("Айболит", doctor.getName());
    }
    public void testFindByIdNull() {
        JdbcGenericDao<Doctor> dao = new DoctorDao(getDataSource());
        Doctor doctor = dao.findById(Long.valueOf(2L));
        assertNull(doctor);
    }

    @SneakyThrows
    public void testCreate() {
        JdbcGenericDao<Doctor> dao = new DoctorDao(getDataSource());
        
        Doctor doctor = Doctor.builder().name("Strange").build();
        
        doctor = dao.create(doctor);
        assertNotNull(doctor);
        assertNotNull(doctor.getId());
        
        assertEquals(2, getConnection().createDataSet().getTable("doctor").getRowCount());
    }

    @Override
    protected DataSource getDataSource() {
        if (ds == null) {
            String file = getClass().getClassLoader().getResource("schema.sql").getFile();
            
            ds = new JdbcDataSource();
            ds.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;INIT=RUNSCRIPT FROM '" + file + "'");
            ds.setUser("sa");
            ds.setPassword("");
        }
        return ds;
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(getClass()
                .getClassLoader().getResourceAsStream("dataset/doctor.xml"));
    }

}
