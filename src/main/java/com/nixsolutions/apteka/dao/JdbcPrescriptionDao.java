package com.nixsolutions.apteka.dao;

import java.util.List;

import javax.sql.DataSource;

import com.nixsolutions.apteka.model.Prescription;

public class JdbcPrescriptionDao extends JdbcGenericDao<Prescription> implements PrescriptionDao {

    public JdbcPrescriptionDao(DataSource dataSource) {
        super(dataSource);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected String getCreateSql() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected String getFindByIdSql() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected EntityMapper<Prescription> getEntityMapper() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getFindAllSql() {
        // TODO Auto-generated method stub
        return null;
    }

}
