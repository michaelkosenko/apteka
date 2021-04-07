package com.nixsolutions.apteka.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import com.nixsolutions.apteka.model.Medicine;

import lombok.SneakyThrows;

public class MedicineDao extends JdbcGenericDao<Medicine> {

    public MedicineDao(DataSource dataSource) {
        super(dataSource);
    }

    private static final String SQL_FIND_BY_ID = "SELECT id, sku FROM medicine WHERE id = ?";

    private static final String SQL_CREATE = "INSERT INTO medicine (sku) VALUES (?)";

    @Override
    protected String getFindByIdSql() {
        return SQL_FIND_BY_ID;
    }

    @Override
    protected String getCreateSql() {
        return SQL_CREATE;
    }

    @Override
    protected EntityMapper<Medicine> getEntityMapper() {
        
        return new EntityMapper<Medicine>() {
            
            @Override
            @SneakyThrows
            public Medicine fromResultSet(ResultSet resultSet) {
                Medicine medicine = Medicine.builder().id(resultSet.getLong("id"))
                      .sku(resultSet.getString("sku")).build();
                return medicine;
            }

            @Override
            @SneakyThrows
            public void fillStatement(PreparedStatement statement,
                    Medicine entity) {
                statement.setString(1, entity.getSku());
            }
        };
    }

}
