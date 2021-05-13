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

    private static final String SQL_CREATE = "INSERT INTO doctor (username, password, fullName) VALUES (?, ?, ?)";

    private static final String SQL_UPDARTE = "UPDATE doctor username = ?, password = ?, fullName =? WHERE id = ?";

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
                        .username(resultSet.getString("username"))
                        .password(resultSet.getString("password"))
                        .fullName(resultSet.getString("fullName"))
                        .build();
            }

            @Override
            @SneakyThrows
            public void fillStatement(PreparedStatement statement,
                    Doctor entity) {
                statement.setString(1, entity.getUsername());
                statement.setString(2, entity.getPassword());
                statement.setString(3, entity.getFullName());
                if (entity.getId() != null) {
                    statement.setLong(4, entity.getId());
                }

            }
        };
    }

    @Override
    protected String getCreateSql() {
        return SQL_CREATE;
    }

    @Override
    public String getFindAllSql() {
        return SQL_FIND_ALL;
    }

    @Override
    public Doctor findByUsername(String username) {
        throw new UnsupportedOperationException();
    }

}
