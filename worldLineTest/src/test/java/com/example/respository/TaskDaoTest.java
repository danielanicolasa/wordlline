package com.example.respository;

import com.example.worldlinetest.controller.TaskController;
import com.example.worldlinetest.model.Task;
import com.example.worldlinetest.model.User;
import com.example.worldlinetest.repository.TaskDao;
import com.example.worldlinetest.repository.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TaskDaoTest {

    private JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
    private TaskDao taskDao = new TaskDao(jdbcTemplate);;

    @Test
    void testGetAllListsOfTasks() {
        List<User> users = new ArrayList<>();
        Stream<User> stream = users.stream();

        when(jdbcTemplate.queryForStream(
                anyString(),
                any(RowMapper.class),
                anyInt()))
                .thenReturn(stream);
        List<User> ret = taskDao.getAllListsOfTasks(1);
        assertEquals(ret, users);
    }

    @Test
    void testCreateTaskList() {
        when(jdbcTemplate.update(
                anyString(),
                anyInt(),
                anyInt(),
                anyString()))
                .thenReturn(1);
        int ret = taskDao.createTaskList(1, "name", 1);
        assertEquals(ret, 1);
    }

    @Test
    void testCreateTask() {
        when(jdbcTemplate.update(
                anyString(),
                anyString(),
                anyString(),
                anyInt()))
                .thenReturn(1);
        int ret = taskDao.createTask(1, "description", "name");
        assertEquals(ret, 1);
    }

    @Test
    void testUpdateTask() {
        when(jdbcTemplate.update(
                anyString(),
                anyString(),
                anyString(),
                anyInt()))
                .thenReturn(1);
        int ret = taskDao.updateTask(new Task(1, "", ""));
        assertEquals(ret, 1);
    }

    @Test
    void testDeleteTask() {
        when(jdbcTemplate.update(
                anyString(),
                anyInt()))
                .thenReturn(1);
        int ret = taskDao.deleteTask(1);
        assertEquals(ret, 1);
    }

    @Test
    void testDeleteTaskList() {
        when(jdbcTemplate.update(
                anyString(),
                anyInt()))
                .thenReturn(1);

        when(jdbcTemplate.update(
                anyString(),
                anyInt()))
                .thenReturn(1);
        int ret = taskDao.deleteTaskList(1);
        assertEquals(ret, 1);
    }

    @Test
    void testMoveTaskToTaskList() {
        when(jdbcTemplate.update(
                anyString(),
                anyInt(),
                anyInt()))
                .thenReturn(1);
        int ret = taskDao.moveTaskToTaskList(1, 1);
        assertEquals(ret, 1);
    }
}
