package com.example.worldlinetest.model;

import lombok.Data;


public record User(String userName, int taskId, String taskName, String taskDescription, int taskListId, String taskListName) {}