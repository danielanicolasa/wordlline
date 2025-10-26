package com.example.worldlinetest.repository;

import com.example.worldlinetest.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User(
                resultSet.getString("user_name"),
                resultSet.getInt("task_id"),
                resultSet.getString("task_name"),
                resultSet.getString("task_description"),
                resultSet.getInt("task_list_id"),
                resultSet.getString("task_list_name")

        );
        return user;
    }
}
