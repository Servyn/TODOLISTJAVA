package com.example;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Tasks {
    private Integer taskId;
    private String title;
    private String description;
    private Boolean status;
    private Timestamp createAt;

    public Tasks(Integer taskId, String title, String description, Boolean status) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.status = status;
        this.createAt = Timestamp.valueOf(LocalDateTime.now());
    }

}
