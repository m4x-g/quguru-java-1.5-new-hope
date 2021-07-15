package com.app.dao;

import com.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //1st method: requesting data from database
    public List<User> getUsers(){
        RowMapper<User> rowMapper = (resultSet, rowNumber) -> mapUser(resultSet);
        return jdbcTemplate.query("SELECT * FROM users", rowMapper);
    }

    //2nd method: mapping response from database to objects
    private User mapUser(ResultSet resultSet) throws SQLException {
        //return user here
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setEmail(resultSet.getString("email"));
        user.setPhone(resultSet.getString("phone"));
        return user;
    }

    public void storeUser(User user){
        jdbcTemplate.update("INSERT INTO USERS (first_name, last_name, email, phone, password) VALUES (?, ?, ?, ?, '1234')", user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhone());
    }
}
