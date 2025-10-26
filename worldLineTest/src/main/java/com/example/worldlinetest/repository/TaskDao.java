package com.example.worldlinetest.repository;

import com.example.worldlinetest.model.Task;
import com.example.worldlinetest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.util.*;

@Component
public class TaskDao {

    JdbcTemplate jdbcTemplate;

    private final String SQL_ALL_LIST_TASK = "select user.name as user_name,\n" +
            " task.task_list_id,\n" +
            " task_list.name as task_list_name,\n" +
            " task.task_id,\n" +
            " task.name as task_name,\n" +
            " task.description as task_description\n" +
            "from  user\n" +
            "left join task_list on fk_user_id = user.user_id\n" +
            "left join task on task.task_list_id = task_list.task_list_id = task.task_id\n" +
            "where user_id = ?\n" +
            "order by task_list_name";
    private final String SQL_DELETE_TASK = "delete from task where task_id=?";
    private final String SQL_DELETE_TASK_LIST = "delete from task_list\n" +
            "where task_list_id=?";

    private final String SQL_DELETE_TASK_FROM_TASK_LIST = "delete from task\n" +
            "where task_list_id=?";
    private final String SQL_UPDATE_TASK = "update task\n" +
            "set name=?, description=? \n" +
            "where task_id=?";

    private final String SQL_UPDATE_TASK_LIST  = "update task\n" +
            "set task_list_id = ?\n" +
            "where task_list_id = ?";

    private final String SQL_INSERT_TASK = "insert into task (name, description, task_list_id)\n" +
            "values (?, ?, ?)";

    private final String SQL_INSERT_TASK_LIST = "insert into task_list " +
            " (task_list_id, fk_user_id, name) values (?, ?, ?)";
    @Autowired
    public TaskDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    public TaskDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> getAllListsOfTasks(int userId) {
        List<User> users = jdbcTemplate.queryForStream(SQL_ALL_LIST_TASK, new UserMapper(), userId).toList();
        return users;
    }

    public Integer createTaskList(int userId, String name, Integer taskListId) {
        return jdbcTemplate.update(SQL_INSERT_TASK_LIST, taskListId, userId, name);
    }

    public Integer createTask(int taskListId, String description, String name) {
        return jdbcTemplate.update(SQL_INSERT_TASK, name, description, taskListId);
    }

    public Integer updateTask(Task task) {
        return jdbcTemplate.update(SQL_UPDATE_TASK, task.name(), task.description(), task.id());
    }

    public Integer deleteTask(Integer taskId) {
        return jdbcTemplate.update(SQL_DELETE_TASK, taskId);
    }

    public Integer deleteTaskList(int taskListId) {
        jdbcTemplate.update(SQL_DELETE_TASK_FROM_TASK_LIST, taskListId);
        return jdbcTemplate.update(SQL_DELETE_TASK_LIST, taskListId);
    }

    public Integer moveTaskToTaskList(int newTaskListId, int oldTaskListId) {
        int deletedTask = jdbcTemplate.update(SQL_UPDATE_TASK_LIST, newTaskListId, oldTaskListId);
        return deletedTask;
    }
}