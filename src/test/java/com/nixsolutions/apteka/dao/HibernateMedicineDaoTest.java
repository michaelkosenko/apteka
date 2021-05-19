package com.nixsolutions.apteka.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.io.File;

import javax.sql.DataSource;

import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.github.database.rider.core.DBUnitRule;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.dataset.DataSet;
import com.nixsolutions.apteka.dao.hibernate.HibernateMedicineDao;
import com.nixsolutions.apteka.model.Medicine;

import lombok.SneakyThrows;

@RunWith(JUnit4.class)
@DBUnit(url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", user = "sa")
public class HibernateMedicineDaoTest {
    @Rule
    public SessionProvider sessionProvider = SessionProvider.getInstance();

    @Rule
    public DBUnitRule dbUnitRule = DBUnitRule.instance();

    private MedicineDao dao = new HibernateMedicineDao(sessionProvider.getSessionFactory());

    @Test
    @DataSet(value = "dataset/yml/medicines.yml", cleanAfter = true)
    public void testFindById() {
        Medicine medicine = dao.findById(Long.valueOf(-1L));
        assertThat(medicine).isNotNull();
        assertThat(medicine.getId()).isEqualTo(-1L);
        assertThat(medicine.getSku()).isEqualTo("SKU001");
    }

    @Test
    public void testFindByIdNull() {
        Medicine medicine = dao.findById(Long.valueOf(2L));
        assertThat(medicine).isNull();
    }

    @SneakyThrows
    @Test
    public void testCreate() {

        Medicine medicine = Medicine.builder().sku("SKU002").build();

        dao.create(medicine);

        assertThat(medicine).isNotNull();
        assertThat(medicine.getId()).isNotNull();

    }

}
