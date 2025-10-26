package com.example.worldlinetest.controller;

import com.example.worldlinetest.model.Task;
import com.example.worldlinetest.model.User;
import com.example.worldlinetest.repository.TaskDao;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class TaskController {

    @Autowired
    private TaskDao taskDao;

    @PostMapping("/create-empty-task-list")
    public Integer createTaskList(@RequestParam int userId,@RequestParam String name) {
        return taskDao.createTaskList(userId, name, null);
    }

    @PostMapping("/create-task")
    public Integer createTask(@RequestParam int taskListId,@RequestParam String description,@RequestParam  String name) {
        return taskDao.createTask(taskListId, description, name);
    }

    @PostMapping("/update-task")
    public Integer createTask(Task task) {
        return taskDao.updateTask(task);
    }

    @DeleteMapping("/delete-task")
    public Integer deleteTask(@RequestParam int taskId) {
        return taskDao.deleteTask(taskId);
    }

    @DeleteMapping("/delete-task-list")
    public Integer deleteTaskList(@RequestParam int taskListId) {
        return taskDao.deleteTaskList(taskListId);
    }

    @PostMapping("/move-task-to-task-list")
    public Integer moveTaskToTaskList(@RequestParam int newTaskListId, @RequestParam int oldTaskListId) {
        return taskDao.moveTaskToTaskList(newTaskListId, oldTaskListId);
    }

    @GetMapping("/all-lists-tasks")
    public List<User> getAllListsOfTasks(@RequestParam int userId) {
        return taskDao.getAllListsOfTasks(userId);
    }
}

