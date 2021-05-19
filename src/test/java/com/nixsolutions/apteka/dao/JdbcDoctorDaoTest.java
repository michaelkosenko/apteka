package com.nixsolutions.apteka.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.dbunit.DBTestCase;
import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.DatabaseTestCase;
import org.dbunit.JdbcBasedDBTestCase;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.h2.jdbcx.JdbcDataSource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionImpl;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.github.database.rider.core.DBUnitRule;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.util.EntityManagerProvider;
import com.nixsolutions.apteka.model.Doctor;

import lombok.SneakyThrows;


@RunWith(JUnit4.class)
public class JdbcDoctorDaoTest {


    @Rule
    public DatasourceProvider datasourceProvider = DatasourceProvider.instance();

    @Rule
    public DBUnitRule dbUnitRule = DBUnitRule.instance(datasourceProvider.getConnection());

    public void testFindById() {
    }

    @Test
    @DataSet(value = "dataset/yml/doctors.yml")
    public void testFindAll() throws Exception {
        System.out.println("Yo!");
        DataSource dataSource = datasourceProvider.getDatasource();
        DoctorDao dao = new JdbcDoctorDao(dataSource);


        List<Doctor> list = dao.findAll();
        assertThat(list).isNotNull().size().isEqualTo(4);
    }
}
