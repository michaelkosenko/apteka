package com.nixsolutions.apteka.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.sql.DataSource;

import com.nixsolutions.apteka.model.Doctor;

import lombok.SneakyThrows;

public class JdbcDoctorDao extends JdbcGenericDao<Doctor> implements DoctorDao {

    public JdbcDoctorDao(DataSource dataSource) {
        super(dataSource);
    }

    private static final String SQL_FIND_BY_ID = "SELECT id, name FROM doctor WHERE id = ?";

    private static final String SQL_CREATE = "INSERT INTO doctor (name) VALUES (?)";

    private static final String SQL_FIND_ALL = "SELECT id, name FROM doctor";



    @Override
    protected String getFindByIdSql() {
        return SQL_FIND_BY_ID;
    }

    @Override
    protected EntityMapper<Doctor> getEntityMapper() {
        return new EntityMapper<Doctor>() {

            @Override
            @SneakyThrows
            public Doctor fromResultSet(ResultSet resultSet) {
                return Doctor.builder().id(resultSet.getLong("id"))
                        .name(resultSet.getString("name")).build();
            }

            @Override
            @SneakyThrows
            public void fillStatement(PreparedStatement statement,
                    Doctor entity) {
                statement.setString(1, entity.getName());

            }
        };
    }

    @Override
    protected String getCreateSql() {
        return SQL_CREATE;
    }

    @Override
    public String getFindAllSql() {
        // TODO Auto-generated method stub
        return SQL_FIND_ALL;
    }

}
