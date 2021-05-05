package com.nixsolutions.apteka.dao;

import static org.junit.Assert.*;

import java.io.File;

import javax.sql.DataSource;

import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.Test;

import com.nixsolutions.apteka.model.Medicine;

import lombok.SneakyThrows;


public class MedicineDaoTest extends DataSourceBasedDBTestCase {
    private JdbcDataSource ds;

    private MedicineDao dao = new JdbcMedicineDao(getDataSource());

    public void testFindById() {
        Medicine medicine = dao.findById(Long.valueOf(1L));
        assertNotNull(medicine);
        assertEquals(Long.valueOf(1L), medicine.getId());
        assertEquals("SKU001", medicine.getSku());
    }

    public void testFindByIdNull() {
        Medicine medicine = dao.findById(Long.valueOf(2L));
        assertNull(medicine);
    }

    @SneakyThrows
    public void testCreate() {

        Medicine medicine = Medicine.builder().sku("SKU002").build();

        medicine = dao.create(medicine);
        assertNotNull(medicine);
        assertNotNull(medicine.getId());

        assertEquals(2, getConnection().createDataSet().getTable("medicine").getRowCount());
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
                .getClassLoader().getResourceAsStream("dataset/medicine.xml"));
    }

}
